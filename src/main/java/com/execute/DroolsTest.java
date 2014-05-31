package com.execute;

import org.goodoldai.jeff.explanation.Explanation;
import org.goodoldai.jeff.explanation.ExplanationChunk;
import org.goodoldai.jeff.wizard.JEFFWizard;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.execute.obj.Message;


/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
    	
    	JEFFWizard wizard;
    	
        try {
        	// ovim ces dohvatiti poruke iz baze
//        	NetClient rest = new NetClient("/api/model/driving-behaviour/method/get-all-event/date_from/2014-03-01/date_to/2014-03-30");
        	
        	// load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	
            Message message = new Message();
            message.setRestData();
            
            wizard = getJEFFWizardWithInternationalization();
            wizard.createExplanation();
            
            kSession.setGlobal("wizard", wizard);            
            kSession.insert(message);
            kSession.fireAllRules();
            
            Explanation expl = wizard.getExplanation();
            System.out.println(expl.getLanguage() + "\n" + expl.getCountry());
            for (ExplanationChunk string : expl.getChunks()) {
				System.out.println(string.getRule() +" " + string.getContent());
			}
//            wizard.generateTXTReport("textReport.txt",false);
            
//            System.out.println("limit:\t" + message.getLimit() + "\t\ntotal:\t" + message.getTotal());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        
    }
    
    private static JEFFWizard getJEFFWizardWithInternationalization() {
		return new JEFFWizard("mm", "srb", "RS", "DS", true);
//				("Marko Muric", "Driving behaviour");
	}
    
    


}
