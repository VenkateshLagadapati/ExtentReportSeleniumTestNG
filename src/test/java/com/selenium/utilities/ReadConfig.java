package com.selenium.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ReadConfig {

	Properties prop;
	static Logger logger = LogManager.getLogger(ReadConfig.class.getName());
	
	public ReadConfig() {
		File src = new File("src/test/resources/config.properties");
		try {
			logger.info("Reading the property file");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
		}
		catch (Exception e) {
			System.out.println("Exceptions " + e.getMessage());
		}
	}
	
	public String getUrl() {
		String url = prop.getProperty("url");
		return url;
	}
	public String getBrowserName() {
		String browsername = prop.getProperty("browsername");
		return browsername;
	}
	public String getUsername() {
		String username = prop.getProperty("username");
		return username;
	}
	public String getPassword() {
		String password = prop.getProperty("password");
		return password;
	}
	
	
}
