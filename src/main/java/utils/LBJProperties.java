package utils;

import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LBJProperties {

	private static final Logger LOGGER = Logger.getLogger(LBJProperties.class.getSimpleName());

	private Properties properties;

	public LBJProperties() {
		properties = new Properties();
		try (InputStream inputStream = LBJProperties.class.getResourceAsStream("/LBJ.properties");) {
			properties.load(inputStream);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Unable to open LBJ.properties ", e);
			throw new IllegalStateException();
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public String getVersion() {
		return properties.getProperty("project.version");
	}

	public String getBuildDate() {
		return properties.getProperty("build.date");
	}

}
