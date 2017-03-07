package test.com.dubizzle.aut.testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

import test.com.dubizzle.aut.constants.ExcelCellsConstant;
import test.com.dubizzle.framework.commonutils.ExcelUtils;
import test.com.dubizzle.framework.commonutils.RestWebServiceUtil;
import test.com.dubizzle.framework.main.AutomationBase;

public class DubizzleUserAPITest extends AutomationBase {

	String path = propRead.readPropertyFile("project.properties", "apiTestData");
	String sheetName = "users";
	String baseURL = propRead.readPropertyFile("project.properties", "uri");
	int excelRowNum = 1;
	
	Response response;

	@BeforeTest(groups = { "", "" })
	public void setUp() {
		try {
			logger.info("Starting setup to launch dubizzle USER API testing");
			super.setUp(path, sheetName, baseURL, "api");
			logger.info("@BeforeTest: Setup done");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("@BeforeTest: Setup failed");
		}
	}

	@Test(groups = { "", "" })
	public void userAPIResponseTest() throws Exception {
		String restURL="";
		String execute, resourceURL, expectedStatusCode;
		int totalExcelRowNum;
		ExcelUtils.setExcelFile(path, sheetName);
		totalExcelRowNum = ExcelUtils.getNumberOfRows();
		logger.info("Total No. of Rows: " + totalExcelRowNum);
		try {
			for (int i = excelRowNum; i < totalExcelRowNum; i++) {

				execute = ExcelUtils.getCellData(i,	ExcelCellsConstant.UserAPISheet.COL_EXECUTE);

				if (execute.equalsIgnoreCase("Y")) {
					restURL = "";
					resourceURL = ExcelUtils.getCellData(i, ExcelCellsConstant.UserAPISheet.COL_URI);
					expectedStatusCode = ExcelUtils.getCellData(i, ExcelCellsConstant.UserAPISheet.COL_STATUS_CODE);
					restURL = baseURL + resourceURL;
					logger.info("Rest WebService API: "+restURL);
					
					response = RestWebServiceUtil.GET(restURL);
					
					int actualStatusCode = RestWebServiceUtil.getStatusCode(response);
					
					Assert.assertEquals(actualStatusCode, Integer.parseInt(expectedStatusCode), "[FAILED] Status code mismatch. API not working: "+restURL);

					logger.info("[PASSED] Status Code: " +actualStatusCode);
					String jsonAsString = response.asString();
					System.out.println(jsonAsString);
					
					// if status code 200 i.e Success reponse validate json
					if(200 == actualStatusCode)	{
						
						// validate login key
						String exLogin = ExcelUtils.getCellData(i, ExcelCellsConstant.UserAPISheet.COL_LOGIN);
						String acLogin = response.path("login");
						Assert.assertEquals(acLogin, exLogin,"[FAILED] 'login' key value mismatched");
						logger.info("[PASSED] 'login' key value matched "+acLogin);
						
						// validate id key
						String exID = ExcelUtils.getCellData(i, ExcelCellsConstant.UserAPISheet.COL_ID);
						int acID = response.path("id");
						Assert.assertEquals(acID, Integer.parseInt(exID),"[FAILED] 'id' key value mismatched");
						logger.info("[PASSED] 'id' key value matched "+acID);				
						
						//LinkedHashMap<String, LinkedHashMap<String, Object>> jsonResponseFormater = new LinkedHashMap<String, LinkedHashMap<String, Object>>();
						//jsonResponseFormater = RestWebServiceUtil.formatJSONToMapString(response, "login");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
