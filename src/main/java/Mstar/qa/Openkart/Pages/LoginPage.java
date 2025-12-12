package Mstar.qa.Openkart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Mstar.qa.Openkart.Utils.ElementUtil;

public class LoginPage {
	
	public WebDriver driver;
	private ElementUtil eu;
	
	//By locators - We will use by locator strategy where we provide Private by locators to Public methods
	// this is a use case for encapsulations
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	
	//we will create the public constructor of the class
	//if we create private constructor we will not be able to create the object of the class
	public LoginPage(WebDriver driver) {
		this.driver = driver;	
		eu = new ElementUtil(driver);
	}
	
	// We will create the page actions
	
	//Each method in page class should return something
	
	public String doGetTitle(){
		return driver.getTitle();
	}
	
	public String doGetLoginUrl() {
		return driver.getCurrentUrl();
	}
	
	public AccountsPage dologin(String un, String pwd) {
		driver.findElement(emailId).sendKeys(un);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginBtn).click();
		 return new AccountsPage();
		
	}
	
	

}
