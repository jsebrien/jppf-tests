package blog.hashmade.jppf.listener;

import org.jppf.client.event.JobEvent;
import org.jppf.client.event.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComputingJobListener implements JobListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputingJobListener.class);
	
	@Override
	public void jobDispatched(JobEvent event) {
		LOGGER.info("jobDispatched:"+event.getSource());
	}
	@Override
	public void jobEnded(JobEvent event) {
		LOGGER.info("jobEnded:"+event.getSource());
	}

	@Override
	public void jobReturned(JobEvent event) {
		LOGGER.info("jobReturned:"+event.getSource());
	}

	@Override
	public void jobStarted(JobEvent event) {
		LOGGER.info("jobStarted:"+event.getSource());
	}

}
