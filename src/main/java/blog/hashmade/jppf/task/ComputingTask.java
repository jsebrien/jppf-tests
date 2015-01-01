package blog.hashmade.jppf.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

import org.jppf.client.JPPFClient;
import org.jppf.client.concurrent.ExecutorServiceConfiguration;
import org.jppf.client.concurrent.JPPFExecutorService;
import org.jppf.node.NodeRunner;
import org.jppf.node.policy.Equal;
import org.jppf.node.policy.ExecutionPolicy;
import org.jppf.node.protocol.AbstractTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import blog.hashmade.jppf.generator.PrimeNumberGenerator;
import blog.hashmade.jppf.util.JobUtil;

public class ComputingTask extends AbstractTask<Boolean>{

	private static final long serialVersionUID = 1L;

	
	private final ComputingTaskParams params;
	
	private final Random random;
	
	public ComputingTask(ComputingTaskParams params) {
		super();
		this.params = params;
		this.random = new Random();
	}

	@Override
	public void run() {
		System.out.println("Starting Computing task...");
		try(JPPFClient jppfClient = JobUtil.buildClient();){
			JPPFExecutorService executorService = new JPPFExecutorService(jppfClient);
			ExecutorServiceConfiguration executorConfig = ((JPPFExecutorService)executorService).getConfiguration();
			ExecutionPolicy uuidExclusion = ExecutionPolicy.Not(new Equal("jppf.uuid", false, NodeRunner.getUuid()));
			executorConfig.getJobConfiguration().getSLA().setExecutionPolicy(uuidExclusion);
		
			int parallelism = params.getParallelism();
			List<Future<Boolean>> futures = new ArrayList<Future<Boolean>>(parallelism);
			for(int i=0 ; i < parallelism ; i++){
				futures.add(executorService.submit(new PrimeNumberGenerator(i, random.nextInt(100))));
			}
			for(int i=0 ; i < parallelism ; i++){
				System.out.println("PrimeNumberGenerator("+i+") terminated with: "+ futures.get(i).get());
			}
			executorService.shutdownNow();
			setResult(Boolean.TRUE);
		}catch(Exception e){
			System.err.println(e.getMessage());
			e.printStackTrace();
			setResult(Boolean.FALSE);
		}
		System.out.println("Computing task ended!");
		
	}
	
}
