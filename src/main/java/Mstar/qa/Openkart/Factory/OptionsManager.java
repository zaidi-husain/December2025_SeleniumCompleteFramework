package Mstar.qa.Openkart.Factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
		
	}
	
	public ChromeOptions getOptions() {
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognite"))) {
			co.addArguments("--incognito");
		}
		return co;

}}
