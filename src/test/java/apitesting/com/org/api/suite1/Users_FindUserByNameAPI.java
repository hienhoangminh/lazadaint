package apitesting.com.org.api.suite1;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.*;
import com.jayway.restassured.response.Response;
import apitesting.com.org.jsonprocessor.JsonProcessor;
import apitesting.com.org.utils.Utils;
import static com.jayway.restassured.RestAssured.*;
import java.util.List;

public class Users_FindUserByNameAPI {

	// Local variable
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
	String att_path1, att_path2, att_path3, att_path4;

	@BeforeClass
	public void setUp() throws Exception {
		util = new Utils();
		parser = new JSONParser();
		jsonPrint = new JsonProcessor();
		urlServer = util.URLComposition();
		csvDataForTestCase = util.readData("config\\data.properties", "api.findUserByName.data.filepath", cvsSplitBy);
	}

	// Simple GET request to find an users by name
	// Status code : 200
	// Parameters : user id
	@Test
	public void Test_FindUsersByName() {
		att_path1 = csvDataForTestCase.get(1).get(1);
		resp = given().when().get(urlServer + "?name=" + att_path1 + "");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		jsonPrint.JsonPrintUserName(parser, body);
		Assert.assertEquals(statusCode, 200);
		Assert.assertNotEquals(body, "[]");
	}

	@Test
	public void Test_FindUserByNameNotExisted() {
		att_path2 = csvDataForTestCase.get(2).get(1);
		resp = given().when().get(urlServer + "?name=" + att_path2 + "");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		jsonPrint.JsonPrintUserName(parser, body);
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(body, "[]");

	}

	@Test
	public void Test_FindUsersByBlankName() {
		resp = given().when().get(urlServer + "?name=");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		jsonPrint.JsonPrintUserName(parser, body);
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(body, "[]");
	}

	@Test
	public void Test_FindUserByNameComposedOfNumber() {
		att_path3 = csvDataForTestCase.get(3).get(1);
		resp = given().when().get(urlServer + "?name=" + att_path3 + "");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		jsonPrint.JsonPrintUserName(parser, body);
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(body, "[]");

	}

	@Test
	public void Test_FindUserByNameWithInvalidURL() {
		att_path4 = csvDataForTestCase.get(4).get(1);
		resp = given().when().get(urlServer + att_path4);
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		jsonPrint.JsonPrintUserName(parser, body);
		Assert.assertEquals(statusCode, 200);
		Assert.assertNotEquals(body, "[]");

	}

}
