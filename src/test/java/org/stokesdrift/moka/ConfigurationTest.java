package org.stokesdrift.moka;

import org.junit.Test;
import org.junit.Assert;

public class ConfigurationTest {

	@Test
	public void testConfigurationSetup() throws Exception {
		
		System.setProperty("testproperty", "testvalue2");
		
		Configuration config = new Configuration("app");
		config.addLoadPath("/config");
		config.init();
		
		Assert.assertEquals("testvalue2", config.getString("testproperty"));
	}
	
}
