package com.selenium.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsersPage {

	WebDriver ldriver;
	WebDriverWait lwait;
	static Logger logger = LogManager.getLogger(LoginPage.class.getName());
	
	public UsersPage(WebDriver rdriver, WebDriverWait rwait) {
		ldriver = rdriver;
		lwait = rwait;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//*[@class='oxd-main-menu']/li[1]")
	WebElement sideMenuAdminButton;
	
	@FindBy(xpath="//*[@class='oxd-form-row']/div/div[4]//*[@class='oxd-select-wrapper']/div")
	WebElement statusDropdown;
	
	@FindBy(xpath="//*[text()='Enabled']")
	WebElement statusEnabledText;
	
	@FindBy(xpath="//*[@class='orangehrm-container']//*[@class='oxd-icon-button oxd-table-cell-action-space'][1]")
	WebElement userStatusResultDeleteIcon;
	
	public boolean searchUsers() {
		boolean isUserSearchSuccess = false;
		lwait.until(ExpectedConditions.visibilityOf(sideMenuAdminButton));
		sideMenuAdminButton.click();
		statusDropdown.click();
		statusEnabledText.click();
		isUserSearchSuccess = userStatusResultDeleteIcon.isDisplayed();
		return isUserSearchSuccess;
	}
}
