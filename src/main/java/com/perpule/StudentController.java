package com.perpule;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
 
@Path("/student")
public class StudentController {
	
	@GET
	@Path("getInfo/{id}")
	public Response getMsg(@PathParam("id") String id) {  
		return Response.status(200).entity("hey got "+id).build(); 
	}

}