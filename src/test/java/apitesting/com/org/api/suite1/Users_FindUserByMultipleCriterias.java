package apitesting.com.org.api.suite1;

import static com.jayway.restassured.RestAssured.given;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.response.Response;

import apitesting.com.org.jsonprocessor.JsonProcessor;
import apitesting.com.org.utils.Utils;

public class Users_FindUserByMultipleCriterias {
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
		List<String> att_path1, att_path2, att_path3;

		@BeforeClass
		public void setUp() throws Exception {
			util = new Utils();
			parser = new JSONParser();
			jsonPrint = new JsonProcessor();
			urlServer = util.URLComposition();
			csvDataForTestCase = util.readData("config\\data.properties", "api.findUserByMultipleCriterias.data.filepath", cvsSplitBy);
		}

		// Simple GET request to find an users by name
		// Status code : 200
		// Parameters : user id
		@Test
		public void Users_FindUserByTwoExistedParameters() {
			att_path1 = csvDataForTestCase.get(1);
			
			resp = given().
					param(att_path1.get(1), att_path1.get(2)).
					param(att_path1.get(3), att_path1.get(4)).
					when().get(urlServer);
			statusCode = resp.getStatusCode();
			body = resp.andReturn().body().asString();
			String userName = JsonPath.read(body, "$.[0].name");
			Assert.assertEquals(userName, att_path1.get(5));
			Assert.assertEquals(statusCode, 200);
			Assert.assertNotEquals(body, "{}");
		}

		@Test
		public void Test_FindUserByOneExistedAndOneNotExistedParameter() {
			att_path2 = csvDataForTestCase.get(2);
			resp = given().
					param(att_path2.get(1), att_path2.get(2)).
					param(att_path2.get(3), att_path2.get(4)).
					when().get(urlServer);
			statusCode = resp.getStatusCode();
			body = resp.andReturn().body().asString();
			//jsonPrint.JsonPrintUserName(parser, body);
			Assert.assertEquals(statusCode, 200);
			Assert.assertEquals(body, "[]");

		}

		@Test
		public void Users_FindUserByWithTwoBlankParameters() {
			resp = given().when().get(urlServer + "?name="+"&address.city=");
			statusCode = resp.getStatusCode();
			body = resp.andReturn().body().asString();
			Assert.assertEquals(statusCode, 200);
			Assert.assertEquals(body, "[]");
		}

		@Test
		public void Test_FindUserByNameWithInvalidURL() {
			att_path3 = csvDataForTestCase.get(3);
			resp = given().
					when().get(urlServer+"@#="+att_path3.get(2)+"$="+att_path3.get(4));
			statusCode = resp.getStatusCode();
			body = resp.andReturn().body().asString();
			//jsonPrint.JsonPrintUserName(parser, body);
			Assert.assertNotEquals(statusCode, 200);
			Assert.assertNotEquals(body, "[]");

		}

}
