package info.rlira.bm.services.client;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsRequest;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsResult;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;

/**
 * {@link }
 * 
 * @author Raquel Lira (raquel.lira@gmail.com) - 27.06.2015
 * 
 */
@Stateless
public class AWSClient {

	private Logger logger = Logger.getLogger(AWSClient.class.getName());
	
	public Boolean verificaStatusInstancia(){
		Boolean status = false;
		logger.finer("[AWS Client] - Calling AWS to verify if instance is alive");
		/**
		 * Autenticar
		 */
		String accessKey = "";
		String secretKey = "";
		String roleArn = "";
		AWSSessionCredentials credentials = getTemporaryCredentials(accessKey, secretKey, roleArn);
		
		/**
		 * Extrair métrica de uso
		 */
		logger.finer("[AWS Client] - AccessKey: " + accessKey);
		logger.finer("[AWS Client] - secretKey: " + secretKey);
		logger.finer("[AWS Client] - roleArn: " + roleArn);
		Region region = Region.getRegion(Regions.fromName("us-east-1"));
		String instanceId = "";
		Integer datapoints = totalDatapoints(credentials, region, instanceId);
		
		/**
		 * Validar retorno da métrica
		 */
		if(datapoints > 0) {
			status = true;
		}
		
		logger.finer("Is there an Instance? " + status);
		return status;
	}
	
	private AWSSessionCredentials getTemporaryCredentials(String accessKey, String secretKey, String roleArn) {
		
		String roleSessionName = "session" + Math.random();
		Integer durationSeconds = 1200;

		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		AWSSecurityTokenServiceClient stsClient = new AWSSecurityTokenServiceClient(credentials);
		AssumeRoleRequest assumeRequest = new AssumeRoleRequest()
			.withRoleArn(roleArn)
			.withRoleSessionName(roleSessionName)
			.withDurationSeconds(durationSeconds);
		AssumeRoleResult assumeResult = stsClient.assumeRole(assumeRequest);
		AWSSessionCredentials temporaryCredentials = new BasicSessionCredentials(
				assumeResult.getCredentials().getAccessKeyId(),
				assumeResult.getCredentials().getSecretAccessKey(),
				assumeResult.getCredentials().getSessionToken());
		return temporaryCredentials;
	}
	
	private Integer totalDatapoints(AWSSessionCredentials credentials, Region region, String instanceId) {
		Long twentyFourHrs = new Long(1000 * 60 * 60 * 24);
		Integer oneHour = new Integer(60 * 60);

		AmazonCloudWatchClient client = new AmazonCloudWatchClient(credentials);
		client.setRegion(region);
		
		GetMetricStatisticsRequest request = new GetMetricStatisticsRequest()
	        .withStartTime(new Date(new Date().getTime() - twentyFourHrs))
	        .withNamespace("AWS/EC2")
	        .withPeriod(oneHour)
	        .withDimensions(new Dimension().withName("InstanceId").withValue(instanceId))
	        .withMetricName("CPUUtilization")
	        .withStatistics("Average", "Maximum")
	        .withEndTime(new Date());
		GetMetricStatisticsResult result = client.getMetricStatistics(request);
		System.out.println(result.getDatapoints().size());
		return result.getDatapoints().size();
	}
	
}
