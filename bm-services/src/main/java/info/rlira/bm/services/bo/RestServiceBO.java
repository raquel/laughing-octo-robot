package info.rlira.bm.services.bo;

import info.rlira.bm.services.dao.PortfolioTypeDAO;
import info.rlira.bm.services.to.ResultTO;

import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * {@link }
 * 
 * @author Raquel Lira (raquel.lira@gmail.com) - 27.06.2015
 * 
 */
@Stateless
public class RestServiceBO {

	private static final Logger logger = Logger.getLogger(RestServiceBO.class.getName());

	@PersistenceContext(unitName = "PostgreSQLDS")
	private EntityManager em;
	
	@Inject
	private PortfolioTypeDAO serviceTypeDAO;

	public ResultTO recuperaValor(String datacenterId, String tenant, String serviceId, String serverId){
		logger.info("Entrou no metodo para recuperar dados");
		ResultTO result = new ResultTO();
		//TODO putting the DAO doing something
		result.setCalculatedValue(BigDecimal.TEN);
		result.setUnitValue(BigDecimal.TEN);
		logger.fine("Service value calculated with success. Resutl: " + result);
		return result;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void collectInstancesInformations() {
		logger.info("Verifying the status of all instances are on.");
	}
}
