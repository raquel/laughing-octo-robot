package info.rlira.bm.services.dao;

import info.rlira.bm.services.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 * {@link }
 * 
 * @author Raquel Lira (raquel.lira@gmail.com) - 27.06.2015
 * 
 */
@Stateless
public class UserDAO extends GenericDAO<User, Long> {

	public UserDAO() {}
	
	public UserDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
	public User findByEmail(String email) {
		User result = new User();
		String query = "from " + User.class.getSimpleName() + " where email = :userEmail";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userEmail", email);
		result = findSingleByQuery(query, params);
		return result;
	}
}
