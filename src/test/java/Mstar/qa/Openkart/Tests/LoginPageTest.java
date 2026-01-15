package Mstar.qa.Openkart.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Mstar.qa.Openkart.BaseTest.BaseTest;

public class LoginPageTest extends BaseTest{

	
	@Test
	public void LoginPageTitleTest() {
		String title = loginPage.doGetTitle();
		System.out.println("Actual Page Title is " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
		
	}
	
	@Test
	public void loginPageURL() {
		String url = loginPage.doGetLoginUrl();
		Assert.assertEquals(url, Constants.LOGIN_PAGE_URL);
	}
	
	@Test (priority = 1)
	public void dologinTest() {
		loginPage.dologin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
}
