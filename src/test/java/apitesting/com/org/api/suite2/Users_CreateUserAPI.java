package apitesting.com.org.api.suite2;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import apitesting.com.org.jsonprocessor.JsonProcessor;
import apitesting.com.org.utils.Utils;

import static com.jayway.restassured.RestAssured.*;

import java.util.List;

public class Users_CreateUserAPI {

	Response resp;
	int statusCode;
	String body;

	Utils util;
	static String cvsSplitBy = ",";
	List<List<String>> csvDataForTestCase;
	JSONParser parser;
	JSONArray jsonArray;
	String urlServer;
	String wrongUrlServer;
	JsonProcessor jsonPrint;
	List<String> att_path1, att_path2, att_path3, att_path4, att_path5;

	@BeforeClass
	public void setUp() throws Exception {
		util = new Utils();
		parser = new JSONParser();
		jsonPrint = new JsonProcessor();
		urlServer = util.URLComposition();
		wrongUrlServer = util.InvalidURLComposition();
		csvDataForTestCase = util.readData("config\\data.properties", "api.createUser.data.filepath", cvsSplitBy);
	}

	@Test
	public void Test_CreateNewUser() {
		String jsonbody = "";
		att_path1 = csvDataForTestCase.get(1);

		try {
			jsonbody = jsonPrint.JsonObjectProcessor(Integer.parseInt(att_path1.get(2)), att_path1.get(3),
					att_path1.get(4), att_path1.get(5), att_path1.get(6), att_path1.get(7), att_path1.get(8),
					att_path1.get(9), att_path1.get(10), att_path1.get(11), att_path1.get(12), att_path1.get(13),
					att_path1.get(14), att_path1.get(15), att_path1.get(16));
		} catch (JsonProcessingException e) {
			System.out.println("Error when processing json file : " + e.getMessage());
		}

		resp = given().when().contentType(ContentType.JSON).body(jsonbody).post(urlServer);
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		Assert.assertEquals(statusCode, 201);
		Assert.assertNotEquals(body, "{}");
	}

	@Test(dependsOnMethods = { "Test_CreateNewUser" })
	public void Test_CreateAlreadyExistedUser() {
		String jsonbody = "";
		att_path2 = csvDataForTestCase.get(2);

		try {
			jsonbody = jsonPrint.JsonObjectProcessor(Integer.parseInt(att_path2.get(2)), att_path2.get(3),
					att_path2.get(4), att_path2.get(5), att_path2.get(6), att_path2.get(7), att_path2.get(8),
					att_path2.get(9), att_path2.get(10), att_path2.get(11), att_path2.get(12), att_path2.get(13),
					att_path2.get(14), att_path2.get(15), att_path2.get(16));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		resp = given().when().contentType(ContentType.JSON).body(jsonbody).post(urlServer);
		Assert.assertNotEquals(statusCode, 200);
		Assert.assertEquals(body, "{}");

	}

	@Test
	public void Test_CreateUserWithDifferentFormat() {
		String jsonbody = "";
		att_path3 = csvDataForTestCase.get(3);
		try {
			jsonbody = jsonPrint.JsonObjectProcessor(Integer.parseInt(att_path3.get(2)), att_path3.get(3),
					att_path3.get(4), att_path3.get(5), att_path3.get(6), att_path3.get(7), att_path3.get(8),
					att_path3.get(9), att_path3.get(10), att_path3.get(11), att_path3.get(12), att_path3.get(13),
					att_path3.get(14), att_path3.get(15), att_path3.get(16));
		} catch (JsonProcessingException e) {
			System.out.println("Error when processing json file : " + e.getMessage());
		}

		resp = given().when().contentType(ContentType.JSON).body(jsonbody).post(urlServer);
		// Assert.assertEquals(statusCode,201);
		Assert.assertNotEquals(body, "[]");

	}

	@Test
	public void Test_CreateUserWithInvalidURL() {
		String jsonbody = "";

		att_path4 = csvDataForTestCase.get(4);

		try {
			jsonbody = jsonPrint.JsonObjectProcessor(att_path4.get(2), att_path4.get(3), att_path4.get(4),
					att_path4.get(5), att_path4.get(6), att_path4.get(7), att_path4.get(8), att_path4.get(9),
					att_path4.get(10), att_path4.get(11), att_path4.get(12), att_path4.get(13), att_path4.get(14),
					att_path4.get(15), att_path4.get(16));
		} catch (JsonProcessingException e) {
			System.out.println("Error when processing json file : " + e.getMessage());
		}

		resp = given().when().contentType(ContentType.JSON).body(jsonbody).post(urlServer + att_path4.get(1));
		body = resp.andReturn().body().asString();
		statusCode = resp.getStatusCode();
		Assert.assertNotEquals(statusCode, 200);
		Assert.assertEquals(body, "{}");
	}

	@Test
	public void Test_CreateUserWithBlankParameter() {
		// Get the list before deletion
		String jsonbody = "";
		resp = given().when().contentType(ContentType.JSON).body(jsonbody).post(urlServer);
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();

		Assert.assertEquals(statusCode, 201);
		Assert.assertNotEquals(body, "{}");

	}

}
