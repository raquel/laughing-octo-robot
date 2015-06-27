package info.rlira.bm.services.rest;

import info.rlira.bm.services.bo.RestServiceBO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * {@link RestServiceResource}
 * 
 * Implementation of {@link RestService}
 * 
 * @author Raquel Lira (raquel.lira@gmail.com) - 27.06.2015
 * 
 */
@Stateless(name = "restResource", mappedName = "ejb/restResource")
public class RestServiceResource implements RestService {

	private static final long serialVersionUID = -2282361129232650700L;
	
	@Inject
	private RestServiceBO bean;
	
	@Override
	public Response getTeste() {
		String res = "Rest works!";
		return Response.status(Response.Status.OK).entity(res).build();
	}

	/**
	 * FIXME Deletar
	 */
	@Override
	public Response getValor(String datacenterId, String tenant, String serviceId, String serverId) {
		String res = "Rest works!";
		return Response.status(Response.Status.OK).entity(res).build();
	}


}
