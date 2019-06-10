package bean;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ServerContext {
	static {
		PropertyResourceBundle bundle = (PropertyResourceBundle) ResourceBundle
				.getBundle("ServerContext");
		HDFS_ROOT = bundle.getString("HdfsRoot");
		REDIS_SERVER = bundle.getString("RedisServer");
		REDIS_PORT=bundle.getString("RedisPort");
		REDIS_PASS=bundle.getString("RedisPassword");
				
		
	}
	public static final String HDFS_ROOT;
	public static final String REDIS_SERVER;

	public static final String REDIS_PORT;
	public static final String REDIS_PASS;
	
}
