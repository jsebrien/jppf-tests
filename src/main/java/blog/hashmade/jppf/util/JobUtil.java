package blog.hashmade.jppf.util;

import org.jppf.client.JPPFClient;
import org.jppf.utils.JPPFConfiguration;
import org.jppf.utils.TypedProperties;

public final class JobUtil {
	
	private JobUtil(){
	}

	public static JPPFClient buildClient(){
		TypedProperties props = JPPFConfiguration.getProperties();
		props.setProperty("jppf.discovery.enabled", "false");
		props.setProperty("jppf.drivers", "driver1");
		props.setProperty("driver1.jppf.server.host", "localhost");
		props.setProperty("driver1.jppf.server.port", "11111");
		return new JPPFClient();
	}

}
