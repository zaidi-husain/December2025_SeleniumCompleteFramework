package Mstar.qa.Openkart.BaseTest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import Mstar.qa.Openkart.Factory.DriverFactory;
import Mstar.qa.Openkart.Pages.LoginPage;
import Mstar.qa.Openkart.Utils.Constants;

public class BaseTest {
	
	public WebDriver driver;
	public DriverFactory df;
	public LoginPage loginPage;
	public Constants Constants;
	public Properties prop;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		
		loginPage = new LoginPage(driver);
		
		
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
