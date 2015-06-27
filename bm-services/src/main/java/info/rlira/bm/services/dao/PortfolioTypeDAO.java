package info.rlira.bm.services.dao;

import info.rlira.bm.services.entity.PortfolioType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ejb.Stateless;

/**
 * {@link }
 * 
 * @author Raquel Lira (raquel.lira@gmail.com) - 27.06.2015
 * 
 */
@Stateless
public class PortfolioTypeDAO extends GenericDAO<PortfolioType, UUID> {
	
	public PortfolioTypeDAO() {}
	
	public PortfolioType findByPortfolioTypeId() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		findSingleByQuery("", parametros);
		return null;
	}
}
