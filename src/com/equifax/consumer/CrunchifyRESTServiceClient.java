package com.equifax.consumer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

public class CrunchifyRESTServiceClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 String string = "";
	        try {
	 
	            // Step1: Let's 1st read file from fileSystem
	            InputStream crunchifyInputStream = new FileInputStream(
	                    "C:/Users/ashimishra/Documents/JSONFile.txt");
                InputStreamReader crunchifyReader = new InputStreamReader(crunchifyInputStream);
	            BufferedReader br = new BufferedReader(crunchifyReader);
	            String line;
	            while ((line = br.readLine()) != null) {
	                string += line + "\n";
	            }
	 
	         //   JSONObject jsonObject = new JSONObject(string);
	            System.out.println(string);
	 
	            // Step2: Now pass JSON File Data to REST Service
	            try {
	                URL url = new URL("http://localhost:8080/EquiFax_Jersey/api/equiFaxService");
	                URLConnection connection = url.openConnection();
	                connection.setDoOutput(true);
	                connection.setRequestProperty("Content-Type", "application/json");
	                connection.setConnectTimeout(50000);
	                connection.setReadTimeout(50000);
	                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
	                out.write(string);
	                out.close();
	 
	                BufferedReader in = new BufferedReader(new InputStreamReader(
	                        connection.getInputStream()));
	 
	                while (in.readLine() != null) {
	                	string += line + "\n";
	                }
	                System.out.println("\nREST Service Invoked Successfully..Response"+string);
	                in.close();
	            } catch (Exception e) {
	                System.out.println("\nError while calling REST Service");
	                System.out.println(e);
	                e.printStackTrace();
	            }
	 
	            br.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	}

}
