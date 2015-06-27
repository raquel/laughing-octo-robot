package info.rlira.bm.services.rest;

import info.rlira.bm.services.bo.RestServiceBO;
import info.rlira.bm.services.job.ServiceJob;

import java.lang.reflect.Method;

import javax.ejb.Schedule;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * {@link }
 * 
 * @author Raquel Lira (raquel.lira@gmail.com) - 27.06.2015
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceJobTest {

	@Mock(name="restServiceBO")
	private RestServiceBO restServiceBOMock;

	@InjectMocks
	private ServiceJob job;

	@Test
	public void testAnnotation() throws Exception {
		Method executeMethod = ServiceJob.class.getMethod("execute");
		Schedule scheduleAnnotation = executeMethod.getAnnotation(Schedule.class);
		Assert.assertEquals("0", scheduleAnnotation.minute());
		Assert.assertEquals("*", scheduleAnnotation.hour());
	}

	@Test
	public void testExecute() throws Exception {
		createUsageInformationBOMock();
		job.execute();
		Mockito.verify(restServiceBOMock).collectInstancesInformations();
	}
	
	private void createUsageInformationBOMock() {
		Mockito.doNothing().when(restServiceBOMock).collectInstancesInformations();
	}
}
