package blog.hashmade.jppf.generator;

import java.io.Serializable;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimeNumberGenerator implements Callable<Boolean>, Serializable {

	private static final long serialVersionUID = 1L;
	
	private final int index;
	private final int limit;

	public PrimeNumberGenerator(final int index, final int limit) {
		this.index = index;
		this.limit = limit;
	}

	@Override
	public Boolean call() throws Exception {
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i < limit; i++) {
			boolean isPrime = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime){
				builder.append(i).append(" ");
			}
		}
		System.out.println("PrimeNumberGenerator("+index+") - Prime numbers between 1 and " + limit + ": "+builder.toString());
		return Boolean.TRUE;
	}

}
