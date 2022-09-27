package com.selenium.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
	
	WebDriver ldriver;
	WebDriverWait lwait;
	static Logger logger = LogManager.getLogger(LoginPage.class.getName());
	
	public LoginPage(WebDriver rdriver, WebDriverWait rwait) {
		ldriver = rdriver;
		lwait = rwait;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(name="username")
	WebElement usernameBox;
	
	@FindBy(name="password")
	WebElement passwordBox;
	
	@FindBy(xpath="//*[@type='submit']")
	WebElement loginButton;
	
	@FindBy(xpath = "//*[@class='oxd-userdropdown-tab']")
	WebElement userProfileIcon;
	
	@FindBy(xpath = "//a[normalize-space()='Logout']")
	WebElement logoutButton;
	
	public void setUsername(String username) {
		logger.info("**entering the username");
		lwait.until(ExpectedConditions.visibilityOf(usernameBox)).sendKeys(username);
	}
	
	public void setPassword(String password) {
		logger.info("**entering the password");
		lwait.until(ExpectedConditions.visibilityOf(passwordBox)).sendKeys(password);
	}
	
	public void clickLoginButton() {
		loginButton.click();
		lwait.until(ExpectedConditions.visibilityOf(userProfileIcon));
	}
	
	public boolean validLogin() {
		boolean isLoginSuccess = userProfileIcon.isDisplayed();
		return isLoginSuccess;
	}
	
	public void logout() {
		userProfileIcon.click();
		logoutButton.click();
	}
	
	public boolean validLogout() {
		lwait.until(ExpectedConditions.visibilityOf(usernameBox));
		boolean isLogoutSuccess = usernameBox.isDisplayed();
		return isLogoutSuccess;
	}
}
