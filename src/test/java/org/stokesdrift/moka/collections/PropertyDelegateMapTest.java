package org.stokesdrift.moka.collections;

import java.util.Properties;

import org.junit.Test;

import org.junit.Assert;

public class PropertyDelegateMapTest {

	@Test
	public void testDelegatePropertyMap() {
		Properties properties = new Properties();
		properties.setProperty("testkey", "testvalue");
		
		PropertyDelegateMap delegateMap = new PropertyDelegateMap();
		Assert.assertNull(delegateMap.get("testkey"));
		
		delegateMap.setDelegateMap(properties);
		Assert.assertEquals("testvalue", delegateMap.get("testkey"));
	}
	
}
