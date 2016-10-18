package org.stokesdrift.moka.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class PropertyUtil {

	/**
	 * Loads up a property file from class path or absolute directory if it exists
	 * 
	 * @param directory
	 * @param defaults
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static Properties loadPropertyFile(String prefix, String directory, Properties defaults) throws URISyntaxException, IOException  {
		File dir = new File(directory);
		StringBuilder fileName = new StringBuilder(directory);
		fileName.append(File.separatorChar);
		if (prefix != null) {
			fileName.append(prefix);
		}
		fileName.append(".properties");
		
		URI fileUrl = null;
		if (dir.isDirectory()) {
			fileUrl = new File(fileName.toString()).toURI();
		} else {
			URL url = null;
			if ((url = PropertyUtil.class.getResource(fileName.toString())) != null) { 
				fileUrl = url.toURI();
			}
		}
		Properties properties = (defaults != null) ? new Properties(defaults) : new Properties();
		properties.load(new FileInputStream(new File(fileUrl)));
		return properties;
	}
	
}
