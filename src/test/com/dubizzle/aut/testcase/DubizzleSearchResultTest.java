package test.com.dubizzle.aut.testcase;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

import com.beust.jcommander.Parameter;

import test.com.dubizzle.aut.constants.ExcelCellsConstant;
import test.com.dubizzle.aut.pages.Pages;
import test.com.dubizzle.framework.commonutils.ExcelUtils;
import test.com.dubizzle.framework.commonutils.UIActions;
import test.com.dubizzle.framework.main.AutomationBase;
import test.com.dubizzle.framework.main.FW_AnyType;


public class DubizzleSearchResultTest extends AutomationBase {
	Pages pages = PageFactory.initElements(driver, Pages.class);
	String path = propRead.readPropertyFile("project.properties", "testData");;
	String sheetName = "search";
	String url = "url";
	String browser="";
	int excelRowNum = 1;

	FW_AnyType<Boolean> expected = new FW_AnyType<Boolean>();
	FW_AnyType<Boolean> actual = new FW_AnyType<Boolean>();
	
	//@Parameters({"browser"})
	@BeforeTest(groups = { "", "" })
	public void setUp() {
		
		try {
			logger.info("Starting setup to launch dubizzle website");
			super.setUp(path, sheetName, url, browser);
			logger.info("@BeforeTest: Setup done");
			PageFactory.initElements(driver, this.getClass().getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("@BeforeTest: Setup failed");
		}
	}

	@AfterTest(groups = { "" })
	public void teardown() {
		// super.teardown();
	}

	@Test(groups = { "", "" })
	public void searchResultSorting() {
		boolean check = false;
		String pageText = null;
		String execute, category, item, title, sortOrder = null;
		int totalExcelRowNum;
		/***** Reporting *****/
		setAuthorInfoForReports();

		ATUReports.setTestCaseReqCoverage("dubizzle search sort test case");
		
		ATUReports.add("INfo Step", LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		
		try {
			totalExcelRowNum = ExcelUtils.getNumberOfRows();
			logger.info("Total No of Test Cases: "+totalExcelRowNum);
			
			for (int i = excelRowNum; i<totalExcelRowNum; i++)	{
				execute = ExcelUtils.getCellData(i, ExcelCellsConstant.SearchSheet.COL_EXECUTE);
				
				if(execute.equalsIgnoreCase("Y"))	{
					category = ExcelUtils.getCellData(i, ExcelCellsConstant.SearchSheet.COL_CATEGORY);
					
					switch (category) {
					case "Motors":
						item = ExcelUtils.getCellData(i, ExcelCellsConstant.SearchSheet.COL_ITEMS);
						if(item.contains("Used Car"))	{
							expected.setT(true);
							check = pages.dubizzleHomePage().clickSubMenuUsedCars(driver);
							actual.setT(check);
							Assert.assertTrue(check, "[FAILED] Unable to click on 'Motors-Used Cars for sale' sub-menu");
							logger.info("[PASSED] CLicked on 'Motors-Used Cars for sale' sub-menu");
							UIActions.VerifySafely("CLicked on 'Motors-Used Cars for sale' sub-menu", expected, actual);
							pageText = pages.usedCarsPage().getPageTitle();
							Assert.assertEquals(pageText, ExcelUtils.getCellData(i, ExcelCellsConstant.SearchSheet.COL_TITLE), "[FAILED] 'Motors-Used Cars for sale' Page Not Displayed");
							logger.info("[PASSED] 'Motors-Used Cars for sale' page is displayed");
							sortOrder = ExcelUtils.getCellData(i, ExcelCellsConstant.SearchSheet.COL_SORTBY);
							pages.usedCarsPage().selectSortByOrder(sortOrder);
							boolean isSorted = pages.usedCarsPage().checkSorting(sortOrder);
							Assert.assertTrue(isSorted, "[FAILED] Used Cars result lists are not correctly sorted by "+sortOrder);
							logger.info("[PASSED] Used cars result lists are correctly sorted by "+sortOrder);
						} 
						else if (item.contains("Heavy Vehicle"))	{
							check = pages.dubizzleHomePage().clickSubMenuHeavyVehicle(driver);
							Assert.assertTrue(check, "[FAILED] Unable to click on Motors-Heavy Vehicle sub-menu");
							logger.info("[PASSED] CLicked on Motors-Heavy Vehicle sub-menu");
							pageText = pages.heavyVehiclePage().getSearchPanelText();
							Assert.assertEquals(pageText, ExcelUtils.getCellData(i, ExcelCellsConstant.SearchSheet.COL_TITLE), "[FAILED] Heavy Vehicle Page Not Displayed");
							logger.info("[PASSED] Heavy Vehicle page is displayed");
							sortOrder = ExcelUtils.getCellData(i, ExcelCellsConstant.SearchSheet.COL_SORTBY);
							pages.heavyVehiclePage().selectSortByOrder(sortOrder);
						}
						break;
					
					case "Jobs":
						item = ExcelUtils.getCellData(i, ExcelCellsConstant.SearchSheet.COL_ITEMS);
						if (item.contains("Online Media"))	{
							check = pages.dubizzleHomePage().clickSubMenuOnlineMedia(driver);
							Assert.assertTrue(check, "[FAILED] Unable to click on 'Jobs-Online Media' sub-menu");
							logger.info("[PASSED] CLicked on 'Jobs-Online Media' sub-menu");
							pageText = pages.jobOnlineMediaPage().getPageTitle();
							Assert.assertEquals(pageText, ExcelUtils.getCellData(i, ExcelCellsConstant.SearchSheet.COL_TITLE), "[FAILED] 'Jobs-Online Media' Page Not Displayed");
							logger.info("[PASSED] 'Jobs-Online Media' page is displayed");
							sortOrder = ExcelUtils.getCellData(i, ExcelCellsConstant.SearchSheet.COL_SORTBY);
							pages.jobOnlineMediaPage().selectSortByOrder(sortOrder);
							boolean isSorted = pages.jobOnlineMediaPage().checkDateSorting(sortOrder);
							Assert.assertTrue(isSorted, "[FAILED] Jobs-Online Media result lists are not correctly sorted by "+sortOrder);
							logger.info("[PASSED] Jobs-Online Media result lists are correctly sorted by "+sortOrder);
						}
						break;
					
					default:
						break;
					}
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}