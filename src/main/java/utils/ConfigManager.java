package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigManager {

	
	private static Properties prop = new Properties();
	
	static {
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
			prop.load(fis);
		} catch (Exception e) {
			throw new RuntimeException("Failed to load config.properties", e);
		}
	}
	
	public static String getBaseUrl() {
		return prop.getProperty("base.url");
	}
	
}
