package info.rlira.bm.services.rest;

import info.rlira.bm.services.to.UserTO;

import java.io.Serializable;

import javax.ejb.Local;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    @RequestScoped
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBands();

    @POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA})
	@Produces(MediaType.APPLICATION_JSON)
    @Path("/save")
    @RequestScoped
	public Response saveUserVote(UserTO userTO);
    
}