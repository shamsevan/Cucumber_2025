package eal.utilities;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationReader {
	
	private static Properties configFile;
	public static final Logger logger = LogManager.getLogger(ConfigurationReader.class);
	
	
	
	static {

		try {
//			String path = "Configuration.properties";
			String path; //----
			 String profile = System.getProperty("profile"); // Get active profile 
				logger.info(LogColor.Magenta+"****************** Current Profile: "+profile+"******************"+LogColor.RESET);
	
			 
			 
	            if (profile == null) {
	                // Default to configuration.properties if no profile is specified
	                path = "Configuration.properties";
	                logger.info("Current configFile ="+path);
	            }
	            else {
	                // Load profile-specific configuration
	                path = profile + ".properties";
	                logger.info("Current configFile ="+path);
	            }
			 
			FileInputStream input = new FileInputStream(path);

			configFile = new Properties();
			configFile.load(input);

			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}

	public static String getProperty(String keyName) {
		return configFile.getProperty(keyName);
	}
	

}
