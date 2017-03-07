package test.com.dubizzle.framework.main;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;

import test.com.dubizzle.aut.constants.ProjectConstant;
import test.com.dubizzle.framework.commonutils.ExcelUtils;
import test.com.dubizzle.framework.commonutils.PropertyFileRead;
import atu.testng.reports.ATUReports;
import atu.testng.reports.utils.Utils;


public class AutomationBase {

	//static SelectBrowser selectBrowser = new SelectBrowser();
	public static PropertyFileRead propRead = new PropertyFileRead();
	//public static final Logger logger = Logger.getLogger(Logger.class.getName());

	public final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	public static WebDriver driver;
	String baseURL = "";

	public void setUp(String path, String sheetName, String url, String browser) {
		try {
			/***** START: Setup Excel file *****/
			if (!path.equalsIgnoreCase("") & !sheetName.equalsIgnoreCase("")) {
				ExcelUtils.setExcelFile(path, sheetName);
			}
			/* --> END: Setup Excel file */

			/***** START: Setup and config log4j property file *****/
			PropertyConfigurator.configure(ProjectConstant.LOG4J_PROPERTY_PATH);
			/* --> END: Setup and config log4j property file */

			/***** START: Browser Settings *****/
			if(browser.equalsIgnoreCase("") | browser.equals(null))	{
				browser = propRead.readPropertyFile("project.properties", "browser");
			}
			
			switch (browser) {
			case "Chrome":
				System.setProperty("webdriver.chrome.driver",ProjectConstant.CHROME_DRIVER);
						//"src/com/resources/inputDrivers/chromedriver.exe");
				driver = new ChromeDriver();
				ATUReports.setWebDriver(driver);
				setIndexPageDescription();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				logger.info("Chrome driver invoked");
				setUpURL(url);
				break;

			case "IE":
				System.setProperty("webdriver.ie.driver",ProjectConstant.IE_DRIVER);
						//"src/com/resources/inputDrivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				ATUReports.setWebDriver(driver);
				setIndexPageDescription();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				logger.info("IE driver invoked");
				setUpURL(url);
				break;

			case "Firefox":
				ProfilesIni profile = new ProfilesIni();
				FirefoxProfile myprofile = profile.getProfile("default");
				driver = new FirefoxDriver(myprofile);
				/*profile = new FirefoxProfile();
				driver = new FirefoxDriver(profile);
				profile.setPreference("browser.startup.homepage",browser);*/

				ATUReports.setWebDriver(driver);
				setIndexPageDescription();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				logger.info("Firefox driver invoked");
				setUpURL(url);
				break;

			case "api":
				logger.info("WebService API testing URI - "+url);
				break;
				
			default:
				profile = new ProfilesIni();
				myprofile = profile.getProfile("default");
				driver = new FirefoxDriver(myprofile);
				ATUReports.setWebDriver(driver);
				setIndexPageDescription();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				logger.info("Firefox driver invoked");
				setUpURL(url);
				break;
			}
			/***** --> END: Browser Settings *****/

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Function Description: This function will setup Application URL from setup.properties file
	 */

	public void setUpURL(String url) throws Exception {
		baseURL = propRead.readPropertyFile("project.properties", "URL");
		logger.info("Driver instantiated. Opening WebPage: " + baseURL);
		driver.get(baseURL);
		
	}

	protected static void setAuthorInfoForReports() {
		ATUReports
				.setAuthorInfo("md. zishan paya", Utils.getCurrentTime(), "1.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Project Execution Description <br/>";
	}

	public void teardown() {
		driver.quit();
	}
}