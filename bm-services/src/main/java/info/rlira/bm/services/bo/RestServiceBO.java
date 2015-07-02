package info.rlira.bm.services.bo;

import info.rlira.bm.services.dao.BandDAO;
import info.rlira.bm.services.dao.UserDAO;
import info.rlira.bm.services.entity.Band;
import info.rlira.bm.services.entity.User;
import info.rlira.bm.services.to.BandTO;
import info.rlira.bm.services.to.GeneralRankingTO;
import info.rlira.bm.services.to.UserTO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * {@link }
 * 
 * @author Raquel Lira (raquel.lira@gmail.com) - 27.06.2015
 * 
 */
@Stateless
public class RestServiceBO {
	
	@Inject
	private BandDAO bandDAO;
	
	@Inject
	private UserDAO userDAO;
	
    @Inject
    private Event<GeneralRankingTO> event;
    
	private static final Logger logger = Logger.getLogger(RestServiceBO.class.getName());

	public List<Band> getParticipatingBands(){
		logger.info("In method RestServiceBO.getParticipatingBands searching for list.");
		List<Band> result = bandDAO.findAll();
		return result;
	}
	
	public Boolean saveVote(UserTO user) {
		Boolean result = false;
		User userResult = saveUser(user.getEmail(), user.getName());
		//TODO Save the Votes
		//Before return, send notification to websocket
		startWebSocket(user);
		return result;
	}

	private User saveUser(String email, String name) {
		User result = new User();
		User verify = userDAO.findByEmail(email);
		if(verify.equals(null)) {
			User user = new User();
			user.setEmail(email);
			user.setName(name);
			result = userDAO.save(user);
		} else
			result = verify;
		return result;
	}
	
	private void startWebSocket(UserTO user) {
		List<BandTO> bands = new ArrayList<BandTO>();
		//TODO montar rank das bandas com dados do banco de dados
		GeneralRankingTO generalRankingTO = new GeneralRankingTO(user, bands);
		event.fire(generalRankingTO);
	}

	public UserTO getUserRanking(UserTO userTO) {
		// TODO Get all votes from on user and sumarize
		return null;
	}
}
