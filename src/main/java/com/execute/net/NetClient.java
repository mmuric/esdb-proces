package com.execute.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class NetClient {
	
	private String host;
	private String route;
	private String public_key;
	private String private_key;
	

	public NetClient(String route) {
		this.route = route;
		
		//TODO napuniti iz konfiguracije
		this.host = "http://bitgear.local";
		this.public_key = "master.636";
		this.private_key = "4f672c423696a";
	}
	
	public String triggeredRest() {
		String json_str = "";
		String url_str = this.route + "/key/" + this.public_key + "/ts/" + (System.currentTimeMillis()/1000);
		url_str = url_str + "/signature/" + this.encodeMessage(url_str);
	
		
		
		try {
			URL url = new URL(this.host + url_str);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode()); 
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		 
			String output;
			while ((output = br.readLine()) != null) {
				json_str +=	output;
			}
	 
			conn.disconnect();
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 	
		
		return json_str;
	}
	
	
	
	private String encodeMessage(String message) {
		
		String hmac = "";
		try {
			SecretKeySpec keySpec = new SecretKeySpec(this.private_key.getBytes(), "HmacMD5");
			
			Mac mac = Mac.getInstance("HmacMD5");
			mac.init(keySpec);
			byte[] rawHmac = mac.doFinal(message.getBytes());
			
			BigInteger hash = new BigInteger(1, rawHmac);
		    hmac = hash.toString(16);

		    if (hmac.length() % 2 != 0) {
		        hmac = "0" + hmac;
		    }
			
		} catch (Throwable t) {
            t.printStackTrace();
        }		
		return hmac;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}
	

}
