package com.selenium.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeePage {
	
	WebDriver ldriver;
	WebDriverWait lwait;
	static Logger logger = LogManager.getLogger(EmployeePage.class.getName());

	public EmployeePage(WebDriver rdriver, WebDriverWait rwait) {
		ldriver = rdriver;
		lwait = rwait;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//*[@class='oxd-main-menu']/li[2]")
	WebElement sideMenuPimButton;
	
	@FindBy(xpath="//*[@class='oxd-topbar-body']//li[2]/a")
	WebElement employeeTabList;
	
	@FindBy(xpath="//*[@class='oxd-table-filter-area']//div/div/div[3]//*[@class='oxd-select-text-input']")
	WebElement employmentStatusDropdown;
	
	@FindBy(xpath="//*[@class='orangehrm-container']//*[@class='oxd-icon-button oxd-table-cell-action-space'][1]")
	WebElement employmentStatusResultDeleteIcon;
	
	
	public boolean searchEmploymentStatus() {
		boolean isEmpSearchSuccess = false;
		sideMenuPimButton.click();
		employeeTabList.click();
		employmentStatusDropdown.sendKeys("Full-Time Contract");
		lwait.until(ExpectedConditions.visibilityOf(employmentStatusResultDeleteIcon));
		isEmpSearchSuccess = employmentStatusResultDeleteIcon.isDisplayed();
		return isEmpSearchSuccess;
		
	}
	
}
