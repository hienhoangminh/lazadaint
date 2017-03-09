package apitesting.com.org.api.suite1;

import static com.jayway.restassured.RestAssured.given;


import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

import apitesting.com.org.jsonprocessor.JsonProcessor;
import apitesting.com.org.utils.Utils;

/**
 * 
 * @author Hien HOANG
 * Method FindUserByLngAPI
 */


public class Users_FindUsersByLngAPI {
	Response resp;
	int statusCode;
	String body;

	Utils util;
	static String cvsSplitBy = ",";
	List<List<String>> csvDataForTestCase;
	JSONParser parser;
	JSONArray jsonArray;
	String urlServer;
	JsonProcessor jsonPrint;
	String att_path1, att_path2, att_path3, att_path4, att_path5;

	@BeforeClass
	public void setUp() throws Exception {
		util = new Utils();
		parser = new JSONParser();
		jsonPrint = new JsonProcessor();
		urlServer = util.URLComposition();
		csvDataForTestCase = util.readData("config\\data.properties", "api.findUserByLong.data.filepath",cvsSplitBy);
	}

  
	/**
	 * Method to search users by longtitude ans display on the console all users matched.
	 */
	@Test
	public void Test_FindUsersByLongtitude() {
		att_path1 = csvDataForTestCase.get(1).get(1);
		resp = given().when().get(urlServer + "?address.geo.lng="+att_path1+"");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		jsonPrint.JsonPrintUserName(parser, body);
		Assert.assertEquals(statusCode, 200);
		Assert.assertNotEquals(body,"[]");
	}
	
	
	@Test
	public void Test_FindUsersByLongtitudeNotExisted() {
		att_path2 = csvDataForTestCase.get(2).get(1);
		resp = given().when().get(urlServer + "?address.geo.lng="+att_path2+"");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		jsonPrint.JsonPrintUserName(parser, body);
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(body,"[]");
	}
	
	@Test
	public void Test_FindUsersByBlankLongtitude() {
		resp = given().when().get(urlServer + "?address.geo.lng=");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		jsonPrint.JsonPrintUserName(parser, body);
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(body,"[]");
	}
	
	
	@Test
	public void Test_FindUsersByStringLongtitude() {
		att_path3 = csvDataForTestCase.get(3).get(1);
		resp = given().when().get(urlServer + "?address.geo.lng="+att_path3+"");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		jsonPrint.JsonPrintUserName(parser, body);
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(body,"[]");
	}
	
	@Test
	public void Test_FindUsersByLngWithInvalidURL() {
		att_path4 = csvDataForTestCase.get(4).get(1);
		resp = given().when().get(urlServer + att_path4);
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		//jsonPrint.JsonPrintUserName(parser, body);
		Assert.assertNotEquals(statusCode, 200);
		Assert.assertNotEquals(body,"[]");
	}
	
	@Test
	public void Test_FindUsersByLngWithDifferentParameter() {
		att_path5 = csvDataForTestCase.get(5).get(1);
		resp = given().when().get(urlServer + att_path5);
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		//jsonPrint.JsonPrintUserName(parser, body);
		Assert.assertNotEquals(statusCode, 200);
		Assert.assertNotEquals(body,"[]");
	}
}
