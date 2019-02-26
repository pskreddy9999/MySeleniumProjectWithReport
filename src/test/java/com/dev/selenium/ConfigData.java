package com.dev.selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigData {
	
	public static String GetData(String propName){
		
		String propertyFilePath= "Config\\Qa_Config.properties";
		String propval=" ";
		FileInputStream fis ;
		 
		try {
			fis= new FileInputStream(propertyFilePath);
			Properties prop= new Properties();
			prop.load(fis);
			
			propval = prop.getProperty(propName);
			fis.close();
			
		} catch ( IOException e) {
			
			e.printStackTrace();
		}
		
		return propval;
	}

}
