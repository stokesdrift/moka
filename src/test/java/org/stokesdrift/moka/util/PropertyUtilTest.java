package org.stokesdrift.moka.util;

import java.util.Properties;

import org.junit.Test;

import org.junit.Assert;

public class PropertyUtilTest {

	 @Test
	 public void testPropertyFileLoad() throws Exception {
		 Properties defaults = new Properties();
		 defaults.setProperty("foo" , "bar");
		 Properties properties = PropertyUtil.loadPropertyFile("app", "/config", defaults);
		 Assert.assertEquals("bar", properties.getProperty("foo"));
		 
		 Assert.assertEquals("testvalue", properties.getProperty("testproperty"));
	 }
	
}
