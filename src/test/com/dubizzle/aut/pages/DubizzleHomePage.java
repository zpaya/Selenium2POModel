package test.com.dubizzle.aut.pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import test.com.dubizzle.aut.locators.Locator;
import test.com.dubizzle.framework.commonutils.PageActions;
import test.com.dubizzle.framework.commonutils.UIActions;

public class DubizzleHomePage extends PageActions	{

	public DubizzleHomePage(WebDriver driver) {
		action = new UIActions();
	}
	
	public boolean clickSubMenuUsedCars(WebDriver driver)	{
		try {
			logger.info("Perform click on Used cars sub-menu");
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(Locator.DubizzleHome.LINK_MENU_MOTORS);
			action.moveToElement(we)
					.moveToElement(
							driver.findElement(Locator.DubizzleHome.LINK_SUBMENU_USEDCARS))
					.click().build().perform();
			logger.info("Clicked on Used cars sub-menu");
			return true;
		} catch (Exception e) {
			logger.error("Exception occured while clicking Used cars Motors \n"+e.getMessage());
			return false;
		}
	}
	
	public boolean clickSubMenuAutoAccessories(WebDriver driver)	{
		try {
			logger.info("Perform click on Auto Accessories sub-menu");
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(Locator.DubizzleHome.LINK_MENU_MOTORS);
			action.moveToElement(we)
					.moveToElement(
							driver.findElement(Locator.DubizzleHome.LINK_SUBMENU_AUTO_ACCESSORIES))
					.click().build().perform();
			logger.info("Clicked on Auto Accessories sub-menu");
			return true;
		} catch (Exception e) {
			logger.error("Exception occured while clicking Auto Accessories Motors \n"+e.getMessage());
			return false;
		}
	}
	
	public boolean clickSubMenuMotorsBoats(WebDriver driver)	{
		try {
			logger.info("Perform click on Boats sub-menu");
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(Locator.DubizzleHome.LINK_MENU_MOTORS);
			action.moveToElement(we)
					.moveToElement(
							driver.findElement(Locator.DubizzleHome.LINK_SUBMENU_BOATS))
					.click().build().perform();
			logger.info("Clicked on Boats sub-menu");
			return true;
		} catch (Exception e) {
			logger.error("Exception occured while clicking Boats Motors \n"+e.getMessage());
			return false;
		}
	}
	
	public boolean clickSubMenuHeavyVehicle(WebDriver driver)	{
		try {
			logger.info("Perform click on Heavy Vehicle sub-menu");
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(Locator.DubizzleHome.LINK_MENU_MOTORS);
			action.moveToElement(we)
					.moveToElement(
							driver.findElement(Locator.DubizzleHome.LINK_SUBMENU_HEAVY_VEHICLE))
					.click().build().perform();
			logger.info("Clicked on Heavy Vehicle sub-menu");
			return true;
		} catch (Exception e) {
			logger.error("Exception occured while clicking Heavy Vehicle Motors \n"+e.getMessage());
			return false;
		}
	}
	
	public boolean clickSubMenuMotorsMotorCycle(WebDriver driver)	{
		try {
			logger.info("Perform click on Motor Cycle sub-menu");
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(Locator.DubizzleHome.LINK_MENU_MOTORS);
			action.moveToElement(we)
					.moveToElement(
							driver.findElement(Locator.DubizzleHome.LINK_SUBMENU_MOTORCYCLE))
					.click().build().perform();
			logger.info("Clicked on Motor Cycle sub-menu");
			return true;
		} catch (Exception e) {
			logger.error("Exception occured while clicking Motor Cycle Motors \n"+e.getMessage());
			return false;
		}
	}
	
	public boolean clickSubMenuMotorsNumberPlates(WebDriver driver)	{
		try {
			logger.info("Perform click on Number Plates sub-menu");
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(Locator.DubizzleHome.LINK_MENU_MOTORS);
			action.moveToElement(we)
					.moveToElement(
							driver.findElement(Locator.DubizzleHome.LINK_SUBMENU_NUMBER_PLATES))
					.click().build().perform();
			logger.info("Clicked on Number Plates sub-menu");
			return true;
		} catch (Exception e) {
			logger.error("Exception occured while clicking Number Plates Motors \n"+e.getMessage());
			return false;
		}
	}

	public boolean clickSubMenuOnlineMedia(WebDriver driver) {
		try {
			logger.info("Perform click on Job-Online Media sub-menu");
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(Locator.DubizzleHome.LINK_MENU_JOBS);
			action.moveToElement(we)
					.moveToElement(
							driver.findElement(Locator.DubizzleHome.LINK_SUBMENU_JOB_ONLINE_MEDIA))
					.click().build().perform();
			logger.info("Clicked on Job-Online Media sub-menu");
			return true;
		} catch (Exception e) {
			logger.error("Exception occured while clicking Job-Online Media Motors \n"+e.getMessage());
			return false;
		}
	}
}
