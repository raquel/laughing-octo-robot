package info.rlira.bm.services.rest;

import info.rlira.bm.services.bo.RestServiceBO;
import info.rlira.bm.services.entity.Band;
import info.rlira.bm.services.to.UserTO;

import java.util.List;

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
	private RestServiceBO bo;

	@Override
	public Response getBands() {
		List<Band> res = bo.getParticipatingBands();
		return Response.status(Response.Status.OK).entity(res).build();
	}

	@Override
	public Response save(String email, String name, String bands) {
		Response res;
		if(bo.saveVote(email, name, bands))
			res = Response.status(Response.Status.CREATED).build();
		else
			res = Response.status(Response.Status.FORBIDDEN).build();
		return res;
	}

	@Override
	public Response ranking() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response save() {
		System.out.println("Entrou no bagulho");
		return null;
	}

	@Override
	public Response save(UserTO userTO) {
		System.out.println(userTO.toString());
		return null;
	}

}
