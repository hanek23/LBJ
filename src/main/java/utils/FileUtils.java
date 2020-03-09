package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FileUtils {

	private static final Logger LOGGER = Logger.getLogger(FileUtils.class.getSimpleName());

	private FileUtils() {
		// only static methods
	}

	/**
	 * @param clazz of which you want to access resources of
	 * @param path  to resource. For example "/generator/shared/ChangelogStart.txt"
	 */
	public static String getStringFromFileResource(Class<?> clazz, String path) {
		try (InputStream inputStream = clazz.getResourceAsStream(path);
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			return reader.lines().collect(Collectors.joining(System.lineSeparator()));
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Unable to open resource: " + path, e);
			throw new IllegalArgumentException();
		}
	}

}
