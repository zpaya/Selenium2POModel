package test.com.dubizzle.aut.testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

import test.com.dubizzle.aut.constants.ExcelCellsConstant;
import test.com.dubizzle.aut.constants.ProjectConstant;
import test.com.dubizzle.framework.commonutils.ExcelUtils;
import test.com.dubizzle.framework.commonutils.RestWebServiceUtil;
import test.com.dubizzle.framework.main.AutomationBase;

public class DubizzleRepoAPITest extends AutomationBase {

	String path = propRead.readPropertyFile("project.properties", "apiTestData");
	String sheetName = "repo";
	String baseURL = propRead.readPropertyFile("project.properties", "uri");
	int excelRowNum = 1;
	
	Response response;

	/*@BeforeTest(groups = { "", "" })
	public void setUp() {
		try {
			logger.info("Starting setup to launch dubizzle REPO API testing");
			super.setUp(path, sheetName, baseURL,"api");
			logger.info("@BeforeTest: Setup done");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("@BeforeTest: Setup failed");
		}
	}*/

	@Test(groups = { "", "" })
	public void repoAPIResponseTest() throws Exception {
		String restURL = "";
		String execute, resourceURL, expectedStatusCode;
		int totalExcelRowNum;
		ExcelUtils.setExcelFile(path, sheetName);
		totalExcelRowNum = ExcelUtils.getNumberOfRows();
		logger.info("Total No. of Rows: " + totalExcelRowNum);
		try {
			for (int i = excelRowNum; i < totalExcelRowNum; i++) {

				execute = ExcelUtils.getCellData(i,	ExcelCellsConstant.RepoAPISheet.COL_EXECUTE);

				if (execute.equalsIgnoreCase("Y")) {
					restURL = "";
					resourceURL = ExcelUtils.getCellData(i, ExcelCellsConstant.RepoAPISheet.COL_URI);
					expectedStatusCode = ExcelUtils.getCellData(i, ExcelCellsConstant.RepoAPISheet.COL_STATUS_CODE);
					restURL = baseURL + resourceURL;
					logger.info("Rest WebService API: "+restURL);
					
					response = RestWebServiceUtil.GET(restURL);
					
					int actualStatusCode = RestWebServiceUtil.getStatusCode(response);
					
					Assert.assertEquals(actualStatusCode, Integer.parseInt(expectedStatusCode), "[FAILED] Status code mismatch. API not working: "+restURL);

					logger.info("[PASSED] Status Code: " +actualStatusCode);
					
					// if status code 200 i.e Success reponse validate json
					if(200 == actualStatusCode)	{						
						// validate id key
						String exID = ExcelUtils.getCellData(i, ExcelCellsConstant.RepoAPISheet.COL_ID);
						
						String exName = ExcelUtils.getCellData(i, ExcelCellsConstant.RepoAPISheet.COL_NAME);
						String acName = RestWebServiceUtil.parseJson(response.asString(), ProjectConstant.API_KEY_ID, exID, ProjectConstant.API_KEY_NAME);
						Assert.assertEquals(acName, exName, "[FAILED] 'name' value mismatched for 'id' "+exID);
						logger.info("[PASSED] 'id': "+exID+" 'name': "+acName);
						
						String exLanguage = ExcelUtils.getCellData(i, ExcelCellsConstant.RepoAPISheet.COL_LANGUAGE);
						String acLanguage = RestWebServiceUtil.parseJson(response.asString(), ProjectConstant.API_KEY_ID, exID, ProjectConstant.API_KEY_LANGUAGE);
						Assert.assertEquals(acLanguage, exLanguage, "[FAILED] 'language' value mismatched for 'id' "+exID);
						logger.info("[PASSED] 'id': "+exID+" 'language': "+acLanguage);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
