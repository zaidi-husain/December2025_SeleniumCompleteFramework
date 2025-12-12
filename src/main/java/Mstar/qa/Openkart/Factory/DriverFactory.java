package Mstar.qa.Openkart.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
	
	//This class will have single responsiblity pattern
	// its only task is to initialize the driver
	

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	
	public WebDriver initDriver(Properties prop) {
		String browser = prop.getProperty("browser").trim();
		System.out.println("Browser name is "+browser);
		
		if(browser.equalsIgnoreCase("chrome")) {
			//driver = new ChromeDriver(optionsManager.getOptions());
			tldriver.set(new ChromeDriver(optionsManager.getOptions()));
			
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			
		}
		else if(browser.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
			
		}
		else {
			System.out.println("please pass the correct browser name" + browser);
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	public synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	
	public Properties initProp() {
		prop = new Properties();
		String env = System.getProperty("env");
		FileInputStream ip = null;
		if(env ==null) {
		
		try {
			 ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		else {
			System.out.println("running on env"+env);
			try {
				
			switch (env.toLowerCase()){
			case "qa": {
				
				ip = new FileInputStream("./src/test/resources/config/QA.config.properties");
			break;
			}
case "UAT": {
				
				ip = new FileInputStream("./src/test/resources/config/UAT.config.properties");
			break;
			}
			default:
				break;
			}
			}
			catch(Exception e) {
				
			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	/*
	 * Take Screenshot
	 */
	
	public void takeScreenshot() {
		File screenshot =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		
	}
	
	
	
	
	
}
