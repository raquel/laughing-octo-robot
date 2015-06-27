package info.rlira.bm.services.job;

import info.rlira.bm.services.bo.RestServiceBO;

import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * {@link }
 * 
 * @author Raquel Lira (raquel.lira@gmail.com) - 27.06.2015
 * 
 */
@Startup
@Singleton
public class ServiceJob {

	private static Logger logger = Logger.getLogger(ServiceJob.class.getName());
	
	@Inject
	private RestServiceBO bo;
	
	
	@Schedule(minute="0", hour="*")
	public void execute() {
		logger.info("Job Instance Status Verifier started.");
		bo.collectInstancesInformations();
		logger.info("Job Instance Status Verifier ended.");
	}
}