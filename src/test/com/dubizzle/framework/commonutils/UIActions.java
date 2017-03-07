package test.com.dubizzle.framework.commonutils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import test.com.dubizzle.framework.main.AutomationBase;
import test.com.dubizzle.framework.main.FW_AnyType;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;


public class UIActions {
	// final WebDriver driver;
	public final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	WebDriver driver;

	/*
	 * public ElementActions(WebDriver driver) { this.driver=driver; }
	 */

	public UIActions() {
		this.driver = AutomationBase.driver;
	}

	public WebElement getElement(By by) throws NoSuchElementException {
		WebElement element = null;
		element = driver.findElement(by);
		return element;
	}

	public boolean elementIsVisible(By by) {
		try {
			WebElement webEleObj = driver.findElement(by);
			boolean isDisplayed = webEleObj.isDisplayed();
			return isDisplayed;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean elementIsEnabled(By by) {
		try {
			WebElement webEleObj = driver.findElement(by);
			boolean isEnabled = webEleObj.isEnabled();
			return isEnabled;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean elementIsSelected(By by) {
		try {
			WebElement webEleObj = driver.findElement(by);
			boolean isSelected = webEleObj.isSelected();
			return isSelected;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean clearText(By field) throws NoSuchElementException {
		driver.findElement(field).clear();
		return true;
	}

	public boolean sendText(By field, String value) throws NoSuchElementException {
		driver.findElement(field).clear();
		driver.findElement(field).sendKeys(value);
		return true;
	}

	public boolean clickElement(By field) throws NoSuchElementException {

		if (waitForElementToBeClickable(20, field)) {
			WebElement webElemObj = driver.findElement(field);
			webElemObj.click();
			return true;
		} else
			return false;
	}

	public boolean clickElementByJavaScript(By field) throws NoSuchElementException {
		WebElement element = driver.findElement(field);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		return true;

	}

	public String getElementText(By by) throws NoSuchElementException {
		WebElement element = getElement(by);
		String elementText = element.getText();
		return elementText;
	}

	public String getPageTitle() {
		String pageTitle = driver.getTitle();
		return pageTitle;
	}

	public String getReadOnlyText(By by) throws NoSuchElementException {
		WebElement element = getElement(by);
		String elementText = element.getAttribute("value");
		return elementText;
	}

	/*
	 * This method return attribute values of Web element by class, value, css etc
	 */
	public String getElementAttribute(String attribute, By by) throws NoSuchElementException {
		WebElement element = getElement(by);
		String elementText = "";
		if (attribute.equalsIgnoreCase("class")) {
			elementText = element.getAttribute("class");
		} else if (attribute.equalsIgnoreCase("value")) {
			elementText = element.getAttribute("value");
		}
		return elementText;
	}

	public boolean selectDropDownByVisibleText(By by, String text) {
		try {
			Select dropDownList = new Select(getElement(by));
			dropDownList.selectByVisibleText(text.trim());
			return true;
		} catch (NoSuchElementException e) {
			logger.info("Exception occured - \n"+e.getMessage());
			return false;
		}
	}

	public boolean selectDropDownByIndex(By by, int index) {
		try {
			Select dropDownList = new Select(getElement(by));
			dropDownList.selectByIndex(index);
			return true;
		} catch (NoSuchElementException e) {
			logger.info("Exception occured - \n"+e.getMessage());
			return false;
		}
	}

	// getSelectedOption() function is used precisely to return the selected ddl text in
	public String getSelectedOption(By by) throws NoSuchElementException {
		Select dropDownList = new Select(getElement(by));
		String strTextToReturn = null;
		List<WebElement> elementList = new ArrayList<WebElement>();
		elementList = dropDownList.getAllSelectedOptions();
		for (int i = 0; i < elementList.size(); i++) {
			WebElement elementObj = elementList.get(i);

			strTextToReturn = elementObj.getText();
			if (strTextToReturn != null & strTextToReturn != "") {
				return strTextToReturn;
			}
		}
		return strTextToReturn;
	}

	/*
	 * getSelectableOptions() function is used to fetch available options in drop down/select box
	 */
	public List<String> getSelectableOptions(By by) throws NoSuchElementException {
		Select dropDownList = new Select(getElement(by));
		List<WebElement> elementList = new ArrayList<WebElement>();
		List<String> selectOptionList = new ArrayList<String>();
		elementList = dropDownList.getOptions();
		for (int i = 0; i < elementList.size(); i++) {
			WebElement elementObj = elementList.get(i);
			if (elementObj.isEnabled()) {
				// adding options that are active
				String strOption = null;
				strOption = elementObj.getText();
				if (strOption != null & strOption != "" && !strOption.equalsIgnoreCase("select")) {
					selectOptionList.add(strOption);
				}
			}
		}
		return selectOptionList;
	}

	public boolean waitForElement(long seconds, By by) {
		try {
			new WebDriverWait(driver, seconds).until(ExpectedConditions
					.visibilityOfElementLocated(by));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean waitForElementToDisapper(long seconds, By by) {
		try {
			new WebDriverWait(driver, seconds).until(ExpectedConditions
					.invisibilityOfElementLocated(by));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean waitForElementToDisapperWithText(long seconds, By by, String text) {
		try {
			new WebDriverWait(driver, seconds).until(ExpectedConditions
					.invisibilityOfElementWithText(by, text));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean waitForElementToBeClickable(long seconds, By by) {
		try {
			new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(by));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean waitForPresenceOfElement(long seconds, By by) {
		try {
			new WebDriverWait(driver, seconds).until(ExpectedConditions
					.presenceOfElementLocated(by));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public List<WebElement> getListofElement(By by) throws NoSuchElementException {
		List<WebElement> elementList = null;
		elementList = driver.findElements(by);
		return elementList;
	}

	public List<String> getListofElementText(By by) throws NoSuchElementException {
		List<WebElement> elementList = null;
		List<String> listValue = new ArrayList<String>();
		elementList = getListofElement(by);
		for (WebElement e : elementList) {
			listValue.add(e.getText().toUpperCase());
		}
		return listValue;
	}

	public boolean deleteCookie() {
		driver.manage().deleteAllCookies();
		return false;
	}

	public boolean takeScreenShot(String type) throws NoSuchElementException, IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("src/test/resources/screenshots/" + type + ".jpg"));
		return true;
	}

	public boolean pressActionsKey(Keys keyToPress) throws NoSuchElementException, IOException {
		Actions action = new Actions(driver);
		action.sendKeys(keyToPress).build().perform();
		return true;
	}

	/*
	 * public boolean alertPopUp(WebDriver driver) throws NoSuchElementException, IOException { Alert alert
	 * = driver.switchTo().alert(); alert.dismiss(); return true; }
	 */

	public boolean acceptPopUpAlert() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.switchTo().defaultContent();
		return false;
	}

	public boolean dismissPopUpAlert() throws Exception {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		driver.switchTo().defaultContent();
		return false;
	}

	public boolean isAlertPresent() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		return true;
	}

	public String getPopUpAlertText() throws Exception {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		return alertText;
	}

	/* *
	 * Store the current window handle
	 */
	public String getCurrentWindowHandle() {
		String currentWindowHandle = driver.getWindowHandle();
		return currentWindowHandle;
	}

	public void switchToWindow(String windowHandle) {
		for (String winHandle : driver.getWindowHandles()) {
			if (winHandle.equalsIgnoreCase(windowHandle)) {
				driver.switchTo().window(windowHandle);
			}
		}
	}

	public void swipe(int startX, int endX, int startY, int endY, int milliSecondDelay,
			WebDriver driver) throws NoSuchElementException {
		// TODO Auto-generated method stub
		// driver.swipe(195, 650, 195, 450, 200);
	}

	public void swipeTillElement() throws NoSuchElementException {
		// TODO Auto-generated method stub

	}

	public static boolean VerifySafely(String description, FW_AnyType<?> expected, FW_AnyType<?> actual) {
		boolean result = true;

		try {
			Assert.assertEquals(actual.getT(), expected.getT(), description);
		} catch (AssertionError e) {
			result = false;

			ATUReports.add("ERROR - " + description, expected.getT().toString(), actual.getT()
					.toString(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			System.out.println("[ERROR] " + e.getMessage());
		}
		if (result) {
			ATUReports.add("VERIFIED  - " + description, expected.getT().toString(), actual.getT()
					.toString(), LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
			System.out.println("[SUCCESS] [VERIFIED '" + description + "']    [Expected value] '"
					+ expected.getT() + "'    [Actual value] '" + actual.getT() + "'.");
		}
		return result;
	}

	public static boolean VerifyPartial(String description, FW_AnyType<?> expected, FW_AnyType<?> actual) {
		boolean result = true;
		try {
			Assert.assertEquals(actual.getT().toString().contains(expected.getT().toString()),
					true, description);
		} catch (AssertionError e) {
			result = false;
			ATUReports.add("ERROR - " + description, expected.getT().toString(), actual.getT()
					.toString(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			System.out.println("[ERROR] " + e.getMessage());
		}
		if (result) {
			ATUReports.add("VERIFIED  - " + description, expected.getT().toString(), actual.getT()
					.toString(), LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
			System.out.println("[SUCCESS] [VERIFIED '" + description + "']    [Expected value] '"
					+ expected.getT() + "'    [Actual value] '" + actual.getT() + "'.");
		}
		return result;
	}
}