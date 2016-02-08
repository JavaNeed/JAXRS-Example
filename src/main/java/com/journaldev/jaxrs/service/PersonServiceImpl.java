package com.journaldev.jaxrs.service;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.journaldev.jaxrs.model.Person;
import com.journaldev.jaxrs.model.Response;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponses;
import com.wordnik.swagger.annotations.ApiResponse;

@Path("/person")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
@Api(value = "/Person", description = "Manage Person")
public class PersonServiceImpl implements PersonService {

	private static Map<Integer,Person> persons = new HashMap<Integer,Person>();

	@Override
	@POST
	@Path("/add")
	@ApiOperation(value = "Add Person", notes = "Add Person", responseContainer = "array", response = Person.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Add Person", response = Person.class), 
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response addPerson(Person p) {
		Response response = new Response();
		if(persons.get(p.getId()) != null){
			response.setStatus(false);
			response.setMessage("Person Already Exists");
			return response;
		}
		persons.put(p.getId(), p);
		response.setStatus(true);
		response.setMessage("Person created successfully");
		return response;
	}

	@Override
	@GET
	@Path("/{id}/delete")
	@ApiOperation(value = "Returns all Person", notes = "Delete Person", responseContainer = "array", response = Person.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Delete Person", response = Person.class), 
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response deletePerson(@PathParam("id") int id) {
		Response response = new Response();
		if(persons.get(id) == null){
			response.setStatus(false);
			response.setMessage("Person Doesn't Exists");
			return response;
		}
		persons.remove(id);
		response.setStatus(true);
		response.setMessage("Person deleted successfully");
		return response;
	}

	@Override
	@GET
	@Path("/{id}/get")
	@ApiOperation(value = "Returns Person by Id", notes = "Get Person By Id", responseContainer = "array", response = Person.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get Person By Id", response = Person.class), 
			@ApiResponse(code = 500, message = "Internal server error") })
	public Person getPerson(@PathParam("id") int id) {
		return persons.get(id);
	}

	@GET
	@Path("/{id}/getDummy")
	@ApiOperation(value = "Returns Dummy Person by Id", notes = "Get Dummy Person By Id", responseContainer = "array", response = Person.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get Dummy Person By Id", response = Person.class), 
			@ApiResponse(code = 500, message = "Internal server error") })
	public Person getDummyPerson(@PathParam("id") int id) {
		Person p = new Person();
		p.setAge(99);
		p.setName("Dummy");
		p.setId(id);
		return p;
	}

	@Override
	@GET
	@Path("/getAll")
	@ApiOperation(value = "Returns All Person", notes = "Returns All Person", responseContainer = "array", response = Person.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get All Person", response = Person.class), 
			@ApiResponse(code = 500, message = "Internal server error") })
	public Person[] getAllPersons() {
		Set<Integer> ids = persons.keySet();
		Person[] p = new Person[ids.size()];
		int i=0;
		for(Integer id : ids){
			p[i] = persons.get(id);
			i++;
		}
		return p;
	}
}