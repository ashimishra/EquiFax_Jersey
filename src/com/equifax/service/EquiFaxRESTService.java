package com.equifax.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class EquiFaxRESTService {
	
		@GET
		@Path("/add/{a}/{b}")
		@Produces(MediaType.TEXT_XML)
		public String add(@PathParam("a") double a, @PathParam("b") double b) {
			return "<?xml version=\"1.0\"?>" + "<result>" +  (a + b) + "</result>";
		}
		
		
		@GET
		@Path("/add")
		@Produces(MediaType.TEXT_XML)
		public String add() {
			return "<?xml version=\"1.0\"?>" + "<result>test Successfull</result>";
		}

	
	    @POST
	    @Path("/equiFaxService")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response crunchifyREST(InputStream incomingData) {
	        StringBuilder crunchifyBuilder = new StringBuilder();
	        StringBuilder responseBuilder = new StringBuilder();
	        try {
	            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
	            String line = null;
	            while ((line = in.readLine()) != null) {
	                crunchifyBuilder.append(line);
	            }
	            System.out.println("Data Received: " + crunchifyBuilder.toString());
	            
	            
	         // Step2: Let's 1st read file from fileSystem
	            InputStream crunchifyInputStream = new FileInputStream(
	                    "C:/Users/ashimishra/Documents/JSONResponse.txt");
                InputStreamReader crunchifyReader = new InputStreamReader(crunchifyInputStream);
	            BufferedReader br = new BufferedReader(crunchifyReader);
	            line = "";
	            while ((line = br.readLine()) != null) {
	            	responseBuilder.append(line);
	            }
	            System.out.println("Data Response: " + responseBuilder.toString());
	            
	        } catch (Exception e) {
	            System.out.println("Error Parsing: - ");
	        }
	        
	 
	        // return HTTP response 200 in case of success
	        return Response.status(200).entity(responseBuilder.toString()).build();
	    }
}
