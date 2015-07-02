package info.rlira.bm.services.rest;

import info.rlira.bm.services.bo.RestServiceBO;
import info.rlira.bm.services.entity.Band;
import info.rlira.bm.services.to.UserTO;

import java.util.List;
import java.util.logging.Logger;

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
	
	private static final Logger logger = Logger.getLogger(RestServiceResource.class.getName());
	
	@Inject
	private RestServiceBO bo;

	@Override
	public Response getBands() {
		List<Band> res = bo.getParticipatingBands();
		return Response.status(Response.Status.OK).entity(res).build();
	}

	@Override
	public Response saveUserVote(UserTO userTO) {
		logger.info("New Object" + userTO.toString());
		Response res;
		if(bo.saveVote(userTO)) {
			UserTO ranking = bo.getUserRanking(userTO);
			res = Response.status(Response.Status.CREATED).entity(ranking).build();
		} else
			res = Response.status(Response.Status.FORBIDDEN).build();
		return res;
	}

}
