package info.rlira.bm.services.bo;

import info.rlira.bm.services.dao.BandDAO;
import info.rlira.bm.services.dao.UserDAO;
import info.rlira.bm.services.entity.Band;
import info.rlira.bm.services.entity.User;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
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
	
	private static final Logger logger = Logger.getLogger(RestServiceBO.class.getName());

	public List<Band> getParticipatingBands(){
		logger.info("In method RestServiceBO.getParticipatingBands searching for list.");
		List<Band> result = bandDAO.findAll();
		return result;
	}
	
	public Boolean saveVote(String email, String name, String bands) {
		User user = saveUser(email, name);
		//TODO colocar para salvar os votos
		return true;
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
	
}
