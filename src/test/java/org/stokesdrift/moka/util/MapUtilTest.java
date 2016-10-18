package org.stokesdrift.moka.util;

import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;
import org.junit.Assert;

public class MapUtilTest {

	
	@Test
	public void testCoversion() {
		
		Map<Object, Object> testMap = new Hashtable<Object,Object>();
		testMap.put("Test Key", "Test Value");
		
		Set<Map.Entry<String,Object>> set = MapUtil.<Map.Entry<String,Object>>toSet(testMap.entrySet());
		Assert.assertNotNull(set);
	}
	
	@Test
	public void testPropertiesConversion() {
		Properties properties = new Properties();
		properties.setProperty("Test Key", "Test value");
		
		Set<Map.Entry<String,Object>> set = MapUtil.<Map.Entry<String,Object>>toSet(properties.entrySet());
		Assert.assertNotNull(set);
	}
		
	
	
}
