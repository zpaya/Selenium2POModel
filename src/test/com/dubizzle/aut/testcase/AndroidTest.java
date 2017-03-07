package test.com.dubizzle.aut.testcase;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import test.com.dubizzle.aut.constants.ProjectConstant;
import test.com.dubizzle.framework.commonutils.MobileAction;
import test.com.dubizzle.framework.commonutils.PropertyFileRead;

public class AndroidTest {
	public final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	public static AndroidDriver driver;
	MobileAction action = new MobileAction();
	public static PropertyFileRead propRead = new PropertyFileRead();
	public By CATEGORY_MOTOR = By.name("Motors");
	public By LABEL_JOB_CATEGORY = By.id("categoriesList_item_tvName");
	public By LABEL_JOB_AIRLINE = By.name("Airlines & Aviation");
	public By ICON_JOB_FILTER = By.id("listingActions_lytChooseFilters");
	public By BUTTON_JOB_FILTER_BACK = By.id("search_btnClose");
	public By ICON_JOB_CHOOSE_VIEW = By.id("listingActions_lytChooseView");
	public By LIST_JOB_CHOOSE_VIEW_LIST = By.id("alert_viewTypeList");
	public By LIST_JOB_CHOOSE_VIEW_MAP = By.id("alert_viewTypeMap");
	public By ICON_JOB_CHOOSE_VIEW_SWITCH_LIST = By.id("listing_actions_iv_choose_view");
	
	@BeforeTest
	public void setUp() throws MalformedURLException	{
		logger.info("Starting device configuration");
		PropertyConfigurator.configure(ProjectConstant.LOG4J_PROPERTY_PATH);
		File projectPath =  new File(System.getProperty("user.dir"));
		File appDir = new File(projectPath, "/app/");
		File app = new File(appDir, "dubizzle.apk");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", propRead.readPropertyFile("project.properties", "deviceName"));
		capabilities.setCapability("platformVersion", propRead.readPropertyFile("project.properties", "platformVersion"));
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.dubizzle.horizontal");
		capabilities.setCapability("appActivity", "com.dubizzle.horizontal.activities.MainActivity");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		logger.info("Device configuration completed");
	}
	
	@Test
	public void testAndroidFunction() throws Exception	{
		logger.info("Starting test case execution");
		//driver.findElement(By.id("place_ad_button")).click();
		action.clickElement(driver, CATEGORY_MOTOR);
		action.swipingHorizontalRightToLeft(driver);
		action.swipingHorizontalRightToLeft(driver);
		action.swipingHorizontalRightToLeft(driver);
		action.swipingHorizontalRightToLeft(driver);
		String searchTextPlaceHolder = action.getText(driver, LABEL_JOB_CATEGORY);
		Assert.assertEquals(searchTextPlaceHolder, "All Jobs", "[FAILED] Search placehoder mismatch");
		action.clickElement(driver, LABEL_JOB_AIRLINE);
		action.clickElement(driver, ICON_JOB_FILTER);
		action.clickElement(driver, BUTTON_JOB_FILTER_BACK);
		action.clickElement(driver, ICON_JOB_CHOOSE_VIEW);
		action.clickElement(driver, LIST_JOB_CHOOSE_VIEW_MAP);
		action.clickElement(driver, ICON_JOB_CHOOSE_VIEW_SWITCH_LIST);
		action.clickElement(driver, LIST_JOB_CHOOSE_VIEW_LIST);
		
		driver.quit();
		driver.close();
	}
}
