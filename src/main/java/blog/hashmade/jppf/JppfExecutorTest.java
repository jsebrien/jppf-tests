package blog.hashmade.jppf;

import java.util.List;

import org.jppf.client.JPPFClient;
import org.jppf.client.JPPFJob;
import org.jppf.node.protocol.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import blog.hashmade.jppf.listener.ComputingJobListener;
import blog.hashmade.jppf.task.ComputingTask;
import blog.hashmade.jppf.task.ComputingTaskParams;
import blog.hashmade.jppf.util.JobUtil;

public final class JppfExecutorTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JppfExecutorTest.class);

	public static void main(String[] args) {
		try (JPPFClient jppfClient = JobUtil.buildClient()){
			JPPFJob job = new JPPFJob(String.valueOf(System.currentTimeMillis()));
			job.setBlocking(true);
			Task<?> task = job.add(new ComputingTask(new ComputingTaskParams(10)));
			task.setId(String.valueOf(System.currentTimeMillis()));
			job.addJobListener(new ComputingJobListener());
			List<Task<?>> results = jppfClient.submitJob(job);
			processExecutionResults(job.getUuid(), results);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} 
	}
	
	private static void processExecutionResults(String name, List<Task<?>> results) {
		if (results != null) {
			for (Task<?> task : results) {
				Boolean status = (Boolean) task.getResult();
				LOGGER.info("Task finished with status: "+status);
			}
		}
	}
	

}
