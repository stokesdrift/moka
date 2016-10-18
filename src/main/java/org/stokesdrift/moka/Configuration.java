package org.stokesdrift.moka;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.stokesdrift.moka.collections.PropertyDelegateMap;
import org.stokesdrift.moka.util.MapUtil;
import org.stokesdrift.moka.util.PropertyUtil;

public class Configuration extends PropertyDelegateMap {
	
	private static final Logger logger = Logger.getLogger(Configuration.class.getName());

	private String prefix;
	private List<String> loadPathDirectories;
	
	private Properties combinedProperties;
	
	public Configuration() {		
	}
	
	public Configuration(String prefix) {
		this.prefix = prefix;
	}
	
	/**
	 * Add a configuration search directory.
	 * Add in order of default to specific, 
	 * example application directory config, home directory configuration, etc...
	 * 
	 * @param directory
	 */
	public void addLoadPath(String directory) {
		if (loadPathDirectories == null) {
			loadPathDirectories = new ArrayList<String>();
		}
		loadPathDirectories.add(directory);
	}
	
	/**
	 * Loads up properties from the search paths. 
	 * 
	 * Search paths can be loaded from absolute directories or relative classpath 
	 *  
	 */
	public void loadProperties() {		
		combinedProperties = null;
		for(String dir: loadPathDirectories) {
			try {
				combinedProperties = PropertyUtil.loadPropertyFile(prefix, dir, combinedProperties);
			} catch(Exception e) {
				StringBuilder logMsg = new StringBuilder("Not able to load ");
				if (prefix != null) {
				  logMsg.append(prefix);
				}
				logMsg.append(" from directory ").append(dir);
				logger.log(Level.WARNING, logMsg.toString(), e);
			}
		}				
	}
	
	
	/**
	 * Initialize configurations
	 */
	public void init() {
		loadProperties();
		
		if (combinedProperties == null) {
			combinedProperties = new Properties();
		}
		
		Properties systemProperties = System.getProperties();
		Set<String> propertyNames = combinedProperties.stringPropertyNames();
		Iterator<String> iter = propertyNames.iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			String value = systemProperties.getProperty(key);
			if(System.getenv(convertToEnvKey(key)) != null) {
				value = System.getenv(convertToEnvKey(key));
			}
			if(value != null) {
				combinedProperties.setProperty(key, value);
			}
		}			
		setDelegateMap(combinedProperties);
	}
	
	protected String convertToEnvKey(String key)  {
		String name = key;
		name = name.replaceAll("\\.", "_");
		return name.toUpperCase();
	}

	/**
	 * Gets a value as an integer, returns -1 if not found
	 * 
	 * @param key
	 * @return
	 */
	public Integer getInteger(String key) {
		Integer i = -1;
		Object obj = get(key);
		if (obj != null) {
			if (obj instanceof Integer) {
				i = (Integer)obj;
			} else {
				i = Integer.valueOf(obj.toString());
			}
		}			
		return i;		
	}
	
	/**
	 * Gets a value as a string, returns null if not found 
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		String value = null;
		Object obj = get(key);
		if (obj != null) {
			value = obj.toString();
		}
		return value;
	}
	
	
	/**
	 * Gets a value as a string array, returns null if not found
	 * Array format is based on a comma delimited values 
	 * 
	 * @param key
	 * @return
	 */
	public String[] getStringArray(String key) {
		String value = null;
		Object obj = get(key);
		if (obj != null) {
			value = obj.toString();
		}
		if (value != null) {
			return value.split(",");
		}
		return null;
	}
	
	
}