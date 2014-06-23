package com.execute;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.stream.FileImageOutputStream;

import org.goodoldai.jeff.wizard.JEFFWizard;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sun.misc.BASE64Encoder;
import sun.misc.IOUtils;

import com.execute.net.NetClient;
import com.execute.obj.Message;
import com.thoughtworks.xstream.core.util.Base64Encoder;


/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
    	
    	JEFFWizard wizard;
    	JSONParser parser = new JSONParser();
        try {        	
        	// ovim ces dohvatiti poruke iz baze
        	NetClient rest = new NetClient("/api/model/driving-behaviour/method/get-all-clients");
        	String str_rest = rest.triggeredRest();
        	
        	if(str_rest.isEmpty()) {
        		throw new Exception("Nema klijenata pa nema ni reporta");
        	}
        	
        	JSONObject jsonObject = (JSONObject) parser.parse(str_rest); 
        	
        	JSONArray msg = (JSONArray) jsonObject.get("result");
    		Iterator<String> iterator = msg.iterator();
    		while (iterator.hasNext()) {
    			String client_id = iterator.next();
    			String route = "/api/model/driving-behaviour/method/get-all-event/id_client/" + client_id;
    			
    			// postavi novu rutu
    			rest.setRoute(route);
    			String result = rest.triggeredRest();
    			if(result.isEmpty()) throw new Exception("no more messages");
    			JSONObject collection = (JSONObject) parser.parse(result);
    			JSONObject objs = (JSONObject) collection.get("result");
    			Iterator<JSONObject> iterator2 = objs.values().iterator();
    			
    			while (iterator2.hasNext()) {
    				KieServices ks = KieServices.Factory.get();
    	    	    KieContainer kContainer = ks.getKieClasspathContainer();
    	        	KieSession kSession = kContainer.newKieSession("ksession-rules");
    			
    				Message message = new Message();
    	            message.setRestData(iterator2.next());
    	            
    	            wizard = getJEFFWizardWithInternationalization();
    	            wizard.createExplanation();
    	            
    	            kSession.setGlobal("wizard", wizard);            
    	            kSession.insert(message);
    	            kSession.fireAllRules();
    	
    	            // String filename = message.getDriverId() + ".pdf";
    	            // wizard.generatePDFReport(filename, true);

    	            String filename = "xml-report-" + message.getDriverId() + ".xml";
    	            wizard.generateXMLReport(filename, false);
    	            
    	            
    	            route = "/api/model/driving-behaviour/method/save-results/id_client/" + client_id + "/driver/" + message.getDateFrom() + "/xml_file/" + executeCommand(filename); 

    	            rest.setRoute(route);
        			System.out.println(rest.triggeredRest());
        			
        			File file = new File(filename);
        			file.delete();
        			
//        			System.exit(0);
    			}    			
    			
    			
    			System.out.println("\n\n");
    			
    		}
    		
    		System.out.println("OK OVO JE GOTOVO");

        } catch (Throwable t) {
            t.printStackTrace();
        }
        
    }
    
    private static JEFFWizard getJEFFWizardWithInternationalization() {
		return new JEFFWizard("mm", "srb", "RS", "DS", true);
	}
    
    private static String executeCommand(String filename) {
    	 
		StringBuffer output = new StringBuffer();
 
		Process p;
		try {
			String command = "php encode.php " + filename;
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));
 
                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}			
 
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return output.toString().replaceAll("[^\\x20-\\x7e]", "");
 
	}
 
    


}
