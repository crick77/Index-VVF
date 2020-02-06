package it.dipvvf.abr.app.index.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Interfaccia dei servizi REST per l'indicizzazione
 * 
 * @author riccardo.iovenitti
 *
 */
@Path("index")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN})
public interface Index {
	@POST
	@Path("{id: \\d+}")
	@Operation(summary = "getIndex",description = "Allowed for all users")
	@Tag(name = "Index",description = "Index research")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "OK")
	})
	public Response index(
			@Parameter(description = "Id",required=true,example = "1")
			@PathParam("id") int id,
			@Parameter(description = "Text to be indexed",required = true,example="Index me please !")
			String body);
	
	@GET
	@Path("search")
	@Operation(summary = "searchIndex",description = "Allowed for all users",tags = {"Index"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "OK"),
			@ApiResponse(responseCode = "404",description = "Not found")
	})
	public Response search(
			@Parameter(description = "Filter for search",required = false,in = ParameterIn.QUERY,example = "Chiedere Riccardo")
			@QueryParam("q") String query);
	
	@DELETE
	@Path("{id: \\d+}")
	@Operation(summary = "Delete",description = "Delete specific entry",tags = {"Index"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "OK")
	})
	public Response delete(
			@Parameter(description = "Id of the index",required=true,example = "1")
			@PathParam("id") int id);
	
	@GET
	@Path("stats")
	@Operation(summary = "Statistics",description = "get Statistics",tags= {"Index"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "OK")
	})
	public Response statistics();
}
