package apitesting.com.org.api.suite2;

import static com.jayway.restassured.RestAssured.given;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import apitesting.com.org.jsonprocessor.JsonProcessor;
import apitesting.com.org.utils.Utils;

public class Users_UpdateUserAPI {

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
	List<String> att_path1, att_path2, att_path3, att_path4, att_path5;

	@BeforeClass
	public void setUp() throws Exception {
		util = new Utils();
		parser = new JSONParser();
		jsonPrint = new JsonProcessor();
		urlServer = util.URLComposition();
		csvDataForTestCase = util.readData("config\\data.properties", "api.updateUser.data.filepath", cvsSplitBy);
	}
 

	
	@Test
	public void Test_UpdateUser() {
		// Get the list before deletion
		String jsonbody = "";

		att_path1 = csvDataForTestCase.get(1);
      
		try {
			jsonbody = jsonPrint.JsonObjectProcessor(att_path1.get(2), att_path1.get(3), att_path1.get(4), att_path1.get(5),
					att_path1.get(6), att_path1.get(7), att_path1.get(8), att_path1.get(9), att_path1.get(10), att_path1.get(11), att_path1.get(12),
					att_path1.get(13),att_path1.get(14), att_path1.get(15),att_path1.get(16));
		} catch (JsonProcessingException e) {
			System.out.println("Error when processing json file : " + e.getMessage());
		}

		resp = given().when().contentType(ContentType.JSON).body(jsonbody).put(urlServer + att_path1.get(1));
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();

		Assert.assertEquals(statusCode, 200);
		Assert.assertNotEquals(body, "{}");
	    
	}

	@Test
	public void Test_UpdateUserNotExisted() {
		
		String jsonbody = "";

		att_path2 = csvDataForTestCase.get(2);

		try {
			jsonbody = jsonPrint.JsonObjectProcessor(att_path2.get(2), att_path2.get(3), att_path2.get(4), att_path2.get(5),
					att_path2.get(6), att_path2.get(7), att_path2.get(8), att_path2.get(9), att_path2.get(10), att_path2.get(11), att_path2.get(12),
					att_path2.get(13),att_path2.get(14), att_path2.get(15),att_path2.get(16));
		} catch (JsonProcessingException e) {
			System.out.println("Error when processing json file : " + e.getMessage());
		}

		resp = given().when().contentType(ContentType.JSON).body(jsonbody).put(urlServer + att_path2.get(1));
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();

		Assert.assertEquals(statusCode, 404);
		Assert.assertEquals(body, "{}");
	}

	@Test
	public void Test_UpdateUserWithInvalidURL() {
		
		String jsonbody = "";

		att_path3 = csvDataForTestCase.get(3);

		try {
			jsonbody = jsonPrint.JsonObjectProcessor(att_path3.get(2), att_path3.get(3), att_path3.get(4), att_path3.get(5),
					att_path3.get(6), att_path3.get(7), att_path3.get(8), att_path3.get(9), att_path3.get(10), att_path3.get(11), att_path3.get(12),
					att_path3.get(13),att_path3.get(14), att_path3.get(15),att_path3.get(16));
		} catch (JsonProcessingException e) {
			System.out.println("Error when processing json file : " + e.getMessage());
		}

		resp = given().when().contentType(ContentType.JSON).body(jsonbody).put(urlServer + att_path3.get(1));
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();

		Assert.assertNotEquals(statusCode, 200);
		Assert.assertEquals(body, "{}");
	}
	
	@Test
	public void Test_UpdateUserWithDifferentParameter() {
		
		String jsonbody = "";

		att_path4 = csvDataForTestCase.get(4);

		try {
			jsonbody = jsonPrint.JsonObjectProcessor(att_path4.get(2), att_path4.get(3), att_path4.get(4), att_path4.get(5),
					att_path4.get(6), att_path4.get(7), att_path4.get(8), att_path4.get(9), att_path4.get(10), att_path4.get(11), att_path4.get(12),
					att_path4.get(13),att_path4.get(14), att_path4.get(15),att_path4.get(16));
		} catch (JsonProcessingException e) {
			System.out.println("Error when processing json file : " + e.getMessage());
		}

		resp = given().when().contentType(ContentType.JSON).body(jsonbody).put(urlServer + att_path4.get(1));
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();

		Assert.assertEquals(statusCode, 200);
		Assert.assertNotEquals(body, "{}");
	}
	
	@Test
	public void Test_UpdateUserWithBlankBodyAndParameter() {
		
		String jsonbody = "";

		resp = given().when().contentType(ContentType.JSON).body(jsonbody).put(urlServer);
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();

		Assert.assertNotEquals(statusCode, 200);
		Assert.assertEquals(body, "{}");
	}
}
