package test.com.dubizzle.framework.commonutils;

import java.util.Map;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

public class RestWebServiceUtil {

	public Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	public static Response response;	
	
	public static Response GET(String url)	{
		response = RestAssured.given().get(url);
		return response;
	}
	
	public static Response POST(String url, Map<String, String> headers, String body)	{
		RestAssured.baseURI = url;
		response = RestAssured.given().headers(headers).body(body).post(url).andReturn();
		return response;
	}
	
	public static int getStatusCode(Response response)	{
		int statusCode = response.getStatusCode();
		return statusCode;
	}
	
	public static String getStatusMessage(Response response) throws JSONException	{
		String statusMessage;
		JSONObject jsonObj = new JSONObject(response.toString());
		statusMessage = jsonObj.get("Status").toString();
		return statusMessage;
	}
	
	public static String parseJson(String responseString, String refKey, String idVaule, String key) throws JSONException	{
		String value = null;
		JSONArray jsonArray = new JSONArray(responseString);
		int arrayLenght = jsonArray.length();
		for (int i = 0; i < arrayLenght; i++) {
		    JSONObject jsonObject = jsonArray.getJSONObject(i);
		    String text = jsonObject.getString(refKey);
		    if(text.equalsIgnoreCase(idVaule))	{
		    	value = jsonObject.getString(key);
		    	break;
		    }
		}		
		return value;
	}
	
}
