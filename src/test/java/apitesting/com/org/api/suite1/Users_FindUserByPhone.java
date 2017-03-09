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


public class Users_FindUserByPhone {
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
		csvDataForTestCase = util.readData("config\\data.properties", "api.findUserByPhone.data.filepath",cvsSplitBy);
	}
	
	@Test
	public void Test_FindUsersByPhone() {
		att_path1 = csvDataForTestCase.get(1).get(1);
		resp = given().when().get(urlServer + "?phone="+att_path1+"");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		jsonPrint.JsonPrintUserName(parser, body);
		Assert.assertEquals(statusCode, 200);
		Assert.assertNotEquals(body,"[]");
	}
	
	
	@Test
	public void Test_FindUsersByPhoneNotExisted() {
		att_path2 = csvDataForTestCase.get(2).get(1);
		resp = given().when().get(urlServer + "?phone="+att_path2+"");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(body,"[]");
	}
	
	@Test
	public void Test_FindUsersByBlankPhone() {
		resp = given().when().get(urlServer + "?phone=");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(body,"[]");
	}
	
	
	@Test
	public void Test_FindUsersByStringPhone() {
		att_path3 = csvDataForTestCase.get(3).get(1);
		resp = given().when().get(urlServer + "?phone="+att_path3+"");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(body,"[]");
	}
	
	@Test
	public void Test_FindUsersByPhoneWithInvalidURL() {
		att_path4 = csvDataForTestCase.get(4).get(1);
		resp = given().when().get(urlServer + att_path4);
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		Assert.assertNotEquals(statusCode, 200);
		Assert.assertEquals(body,"{}");
	}
	
	@Test
	public void Test_FindUsersByPhoneWithDifferentParameter() {
		att_path5 = csvDataForTestCase.get(5).get(1);
		resp = given().when().get(urlServer + att_path5);
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		Assert.assertNotEquals(statusCode, 200);
		Assert.assertEquals(body,"{}");
	}
	
}
