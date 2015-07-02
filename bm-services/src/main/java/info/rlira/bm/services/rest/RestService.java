package info.rlira.bm.services.rest;

import info.rlira.bm.services.to.UserTO;

import java.io.Serializable;

import javax.ejb.Local;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * {@link RestService}
 * 
 * Interface responsible for aggregate all REST calls
 * @author Raquel Lira (raquel.lira@gmail.com) - 27.06.2015
 * 
 */

@Local
@Path("/")
public interface RestService extends Serializable {

    @GET
    @Path("/bands")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBands();

    @POST
    @Path("/save/{email}/{name}/{bands}")
    public Response save(@FormParam("email") String email, @FormParam("name") String name, @FormParam("bands") String bands);
    
    @POST
    @Path("/save")
    public Response save();
    
    @POST
	@Consumes("application/json")
	@Produces("application/json")
    @Path("/saves")
    @RequestScoped
	public Response save(UserTO userTO);
    
	@GET
	@Path("/ranking-user")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ranking();
	
}