package com.selenium.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.selenium.pages.BasePage;
import com.selenium.pages.LoginPage;
import com.selenium.pages.UsersPage;

public class TC_002_SearchUsers extends BaseClass{

	BasePage basepage;
	LoginPage loginpage;
	UsersPage userpage;
	
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
	public void m_checkUsersLists() {
		userpage = new UsersPage(driver, wait);
		Assert.assertEquals(userpage.searchUsers(), true);
		extentTest.warning("**Check users list");
	}
	
	@Test(priority = 3, dependsOnMethods = "m_checkUsersLists")
	public void m_logout() {
		loginpage.logout();
		Assert.assertEquals(loginpage.validLogout(), true);
		extentTest.info("**Logout is Successful");
	}
}
