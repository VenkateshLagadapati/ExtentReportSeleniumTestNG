package com.selenium.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.tests.BaseClass;

public class BasePage {

	WebDriver ldriver;
	WebDriverWait lwait;
	static Logger logger = LogManager.getLogger(BasePage.class.getName());
	BaseClass baseclass;
	
	public BasePage(WebDriver rdriver, WebDriverWait rwait) {
		ldriver = rdriver;
		lwait = rwait;
		PageFactory.initElements(rdriver, this);
	}
	
	public void launchApplication() {
		baseclass = new BaseClass();
		ldriver.get(baseclass.url);
		logger.info("**Launching application");
	}
}
