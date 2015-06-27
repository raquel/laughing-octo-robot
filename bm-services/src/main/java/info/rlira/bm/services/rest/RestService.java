package info.rlira.bm.services.rest;

import java.io.Serializable;

import javax.ejb.Local;
import javax.ws.rs.GET;
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

	/**
	 * Test method to verify if the service working
	 * 
	 * FIXME: Remove this before prod.
	 * 
	 * @return
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeste();
    

    /**
     * MODELO
     * FIXME: Deletar isso antes de commitar
     */
    @GET
    @Path("/{datacenter}/{tenant}/{service}/{server}")
    @Produces({ "application/json" })
    public Response getValor(@PathParam("datacenter") String datacenterId, @PathParam("tenant") String tenantId, @PathParam("service") String serviceId, @PathParam("server") String serverId);
    
    
}