package apitesting.com.org.api.suite2;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.*;
import com.jayway.restassured.response.Response;

import apitesting.com.org.jsonprocessor.JsonProcessor;
import apitesting.com.org.utils.Utils;

import static com.jayway.restassured.RestAssured.*;

import java.util.List;

public class Users_DeleteUserAPI {

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
	String att_path1, att_path2, att_path3, att_path4, att_path5;

	@BeforeClass
	public void setUp() throws Exception {
		util = new Utils();
		parser = new JSONParser();
		jsonPrint = new JsonProcessor();
		urlServer = util.URLComposition();
		csvDataForTestCase = util.readData("config\\data.properties", "api.deleteUser.data.filepath", cvsSplitBy);
	}

	@Test
	public void Test_DeleteUser() {

		att_path1 = csvDataForTestCase.get(1).get(1);
		resp = given().when().delete(urlServer + att_path1);
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(body, "{}");

	}

	@Test
	public void Test_DeleteNonExistedUser() {
		// Get the list before deletion

		att_path2 = csvDataForTestCase.get(2).get(1);

		resp = given().when().delete(urlServer + att_path2);
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();

		Assert.assertNotEquals(statusCode, 200);
		Assert.assertEquals(body, "{}");

	}

	@Test(dependsOnMethods={"Test_DeleteUser"})
	public void Test_DeleteUserAlreadyDeleted() {
		// Get the list before deletion
		
		att_path3 = csvDataForTestCase.get(3).get(1);

		resp = given().when().delete(urlServer + att_path3);
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();

		Assert.assertNotEquals(statusCode, 200);
		Assert.assertEquals(body, "{}");

	}

	@Test
	public void Test_DeleteUserWithInvalidURL() {
		// Get the list before deletion
		
		att_path4 = csvDataForTestCase.get(4).get(1);

		resp = given().when().delete(urlServer + att_path4);
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();

		Assert.assertNotEquals(statusCode, 200);
		Assert.assertEquals(body, "{}");
	}

	@Test
	public void Test_DeleteUserWithDifferentParameter() {
		// Get the list before deletion

		att_path5 = csvDataForTestCase.get(5).get(1);

		resp = given().when().delete(urlServer + att_path5);
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();

		Assert.assertNotEquals(statusCode, 200);
		Assert.assertEquals(body, "{}");

	}
	
	@Test
	public void Test_DeleteUserWithBlankParameter() {
		// Get the list before deletion

		resp = given().when().delete(urlServer+"/");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();

		Assert.assertNotEquals(statusCode, 200);
		Assert.assertEquals(body, "{}");

	}
}
