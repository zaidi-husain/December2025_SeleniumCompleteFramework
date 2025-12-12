package Mstar.qa.Openkart.Utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClear(By locator) {
		getElement(locator).clear();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By loccator) {
		return getElement(loccator).isDisplayed();

	}

	public boolean doIsEnabled(By locator) {
		return getElement(locator).isEnabled();

	}

	public boolean doIsSelected(By locator) {
		return getElement(locator).isSelected();

	}

	public String doGetAttribute(By locator, String attr) {
		return getElement(locator).getAttribute(attr);
	}

	public boolean checkElementDisabled(By locator, String attr) {
		String attrValue = doGetAttribute(locator, attr);
		if (attrValue.equalsIgnoreCase("True")) {
			return true;
		} else {
			return false;
		}
	}

	public void doLinkClick(By locator, String linktext) {
		List<WebElement> listOfLinks = getElements(locator);
		for (WebElement e : listOfLinks) {
			String text = e.getText();
			if (text.trim().equals(linktext)) {
				e.click();
				break;
			}

		}
	}

	public boolean checkElementDisplayed(By locator) {

		if (getElements(locator).size() == 1)
			return true;
		return false;

	}

	public boolean checkElementDisplayed(By locator, int noOfFeilds) {

		if (getElements(locator).size() == noOfFeilds)
			return true;
		return false;

	}

	// ********************************** Dropdown Utils
	// *********************************//

	public boolean doSelectByVisbleText(By locator, String visibleText) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
		return isDropDownValueSelected(select, visibleText);
	}

	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public boolean doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
		return isDropDownValueSelected(select, value);
	}

	public void selectDDwithoutSelect(By locator, String value) {
		List<WebElement> options = getElements(locator);
		for (WebElement e : options) {
			String text = e.getText();
			if (text.trim().equalsIgnoreCase(value)) {
				e.click();
				break;
			}
		}
	}

	public boolean isDropDownValueSelected(Select select, String expValue) {
		if (select.getFirstSelectedOption().getText().contains(expValue)) {
			System.out.println(expValue + ": expected value is selected");
			return true;
		}
		return false;
	}

	/*
	 * This method is used to handle multi select dropdown with three use cases 1.
	 * Can select one value 2. can select multiple value 3. can select all values
	 * just pass value as "All"
	 */
	public void multiSelectDropDown(By locator, String... value) {
		List<WebElement> choicelist = driver.findElements(locator);
		if (!value[0].equalsIgnoreCase("All")) {
			for (int i = 0; i < choicelist.size(); i++) {
				String text = choicelist.get(i).getText();
				System.out.println(text);

				for (int j = 0; j < value.length; j++) {
					if (text.equals(value[j])) {
						choicelist.get(i).click();
						break;
					}
				}
			}
		} else {
			// select All choices
			try {
				for (WebElement e : choicelist) {
					e.click();
				}
			} catch (Exception e) {
				System.out.println("All choices are over");
			}
		}
	}

	/*
	 * Actions class utilities
	 * 
	 */
	public void actionSendKeys(By locator, String value) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(locator), value).perform();
	}

	public void actionDoClick(By locator) {
		Actions action = new Actions(driver);
		action.click(getElement(locator)).perform();
	}
	
	//Alert Waits
	public Alert waitForAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
		
		
	}
	
	public void acceptAlert(int timeout) {
		 waitForAlert(timeout).accept();
		
		
	}
	
	public void dismissAlert(int timeout) {
		 waitForAlert(timeout).dismiss();
		
		
	}
	public void alertSendKeys(int timeout, String keys) {
		 waitForAlert(timeout).sendKeys(keys);
		
		
	}
	public String alertGetText(int timeout) {
		Alert alert=  waitForAlert(timeout);
		String txt = alert.getText();
		alert.accept();
		return txt;
		
		
	}

}

