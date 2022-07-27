package edu.sap.KeywordDrivenFramework.Base;

import java.util.Properties;

public class EnvinormentConfiguration extends Base {
	
	public String getEnvProperty(String propertyName) {
		Base baseObj = new Base();
		Properties propertyValue = baseObj.initProperties();
		return propertyValue.getProperty(propertyName);
	}

}
