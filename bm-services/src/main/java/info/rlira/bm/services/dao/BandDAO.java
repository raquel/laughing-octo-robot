package info.rlira.bm.services.dao;

import info.rlira.bm.services.entity.Band;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 * {@link }
 * 
 * @author Raquel Lira (raquel.lira@gmail.com) - 27.06.2015
 * 
 */
@Stateless
public class BandDAO extends GenericDAO<Band, Long> {

	public BandDAO() {}
	
	public BandDAO(EntityManager entityManager) {
		super(entityManager);
	}

}
