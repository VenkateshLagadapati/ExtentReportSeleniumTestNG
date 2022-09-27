package com.selenium.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.pages.BasePage;
import com.selenium.pages.EmployeePage;
import com.selenium.pages.LoginPage;

public class TC_001_SearchEmployees extends BaseClass{
	
	BasePage basepage;
	LoginPage loginpage;
	EmployeePage employeepage;
	
	@Test(priority = 1)
	public void m_login() {
		basepage = new BasePage(driver, wait);
		loginpage = new LoginPage(driver, wait);
		basepage.launchApplication();
		loginpage.setUsername(username);
		loginpage.setPassword(password);
		loginpage.clickLoginButton();
		Assert.assertEquals(loginpage.validLogin(), true);
		extentTest.info("**Login is Successful");
	}

	@Test(priority = 2, dependsOnMethods = "m_login")
	public void m_checkEmployeeLists() {
		employeepage = new EmployeePage(driver, wait);
		Assert.assertEquals(employeepage.searchEmploymentStatus(), true);
		extentTest.warning("**Check employee list");
	}
	
	@Test(priority = 3, dependsOnMethods = "m_checkEmployeeLists")
	public void m_logout() {
		loginpage.logout();
		Assert.assertEquals(loginpage.validLogout(), true);
		extentTest.info("**Logout is Successful");
	}
}
