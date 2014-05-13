package com.execute;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sun.org.mozilla.javascript.json.JsonParser;

import com.execute.net.NetClient;
import com.execute.obj.Message;


/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
        	// ovim ces dohvatiti poruke iz baze
        	NetClient rest = new NetClient("/api/model/driving-behaviour/method/get-all-event/date_from/2014-03-01/date_to/2014-03-30");
        	
        	// load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	
            Message message = new Message();
            message.setRestData(new JSONObject(rest.triggeredRest()));
            
            kSession.insert(message);
            kSession.fireAllRules();
            
            System.out.println("limit:\t" + message.getLimit() + "\t\ntotal:\t" + message.getTotal());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        
    }
/*
    public static class Message {

        public static final int HELLO = 0;
        public static final int GOODBYE = 1;

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }
    */

}
