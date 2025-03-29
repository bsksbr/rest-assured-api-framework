package utils;

import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {
	private static final Logger logger= LoggerFactory.getLogger(LoggerTest.class);
	
	public static void main(String[] args) {
		Configurator.initialize(null, "/src/test/resources/log4j2.xml");
		logger.info("Info Message: SLF4J & Log4j2 are working");
		logger.warn("Warning message");
		logger.error("Error Message");
	}
}
