package blog.hashmade.jppf.task;

import java.io.Serializable;

public class ComputingTaskParams implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final int parallelism;

	public ComputingTaskParams(int parallelism) {
		super();
		this.parallelism = parallelism;
	}

	public int getParallelism() {
		return parallelism;
	}

}
