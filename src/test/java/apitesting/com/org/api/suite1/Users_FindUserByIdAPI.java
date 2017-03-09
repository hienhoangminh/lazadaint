package apitesting.com.org.api.suite1;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.*;
import java.util.List;
import apitesting.com.org.utils.*;

/**
 * 
 * @author Hien HOANG
 *
 */

public class Users_FindUserByIdAPI {

	// Local variable
	Response resp;
	int statusCode;
	String body;

	Utils util;
	static String cvsSplitBy = ",";
	List<List<String>> csvDataForTestCase;
	String urlServer;
	String att_path1, att_path2, att_path3, att_path4;

	@BeforeClass
	public void setUp() throws Exception {
		util = new Utils();
		urlServer = util.URLComposition();
		csvDataForTestCase = util.readData("config\\data.properties", "api.findUserById.data.filepath", cvsSplitBy);
	}

	/**
	 * Method Test_FindUsersById to search user by ID. Expeceted to have
	 * response code = 200 so this test case would be passed
	 */
	@Test
	public void Test_FindUsersById() {
		att_path1 = csvDataForTestCase.get(1).get(1);
		String expected_name = csvDataForTestCase.get(1).get(2);
		resp = given().when().get(urlServer + "/" + att_path1 + "");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		String userName = JsonPath.read(body, "$.name");
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(userName, expected_name);
		Assert.assertNotEquals(body, "{}");

	}

	/**
	 * Method Test_FindUsersById to search user by ID not existed. Expected to
	 * have response code != 200 so this test case would be failed
	 */
	@Test
	public void Test_FindUsersByNotExistedId() {
		att_path2 = csvDataForTestCase.get(2).get(1);
		resp = given().when().get(urlServer + "/" + att_path2 + "");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		Assert.assertNotEquals(statusCode, 200);
		Assert.assertEquals(body, "{}");
	}

	/**
	 * Method Test_FindUsersByIdNotNumber to search user by ID which is not
	 * number. Expected to have response code != 200 so this test case would be
	 * failed
	 */
	@Test
	public void Test_FindUserByIdNotNumber() {
		att_path3 = csvDataForTestCase.get(3).get(1);
		resp = given().when().get(urlServer + "/" + att_path3 + "");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		Assert.assertNotEquals(statusCode, 200);
		Assert.assertEquals(body, "{}");

	}

	/**
	 * Method Test_FindUserByBlankId to search user by ID which is blank.
	 * Expected to have response code != 200 so this test case would be failed
	 */
	@Test
	public void Test_FindUserByBlankId() {
		resp = given().when().get(urlServer + "/ ");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		Assert.assertEquals(statusCode, 200);
		Assert.assertNotEquals(body, "{}");

	}

	/**
	 * Method Test_FindUserByIdInvalidURL to search user by ID but the link is
	 * not valid. Expected to have response code != 200 so this test case would
	 * be failed
	 */
	@Test
	public void Test_FindUserByIdInvalidURL() {
		att_path4 = csvDataForTestCase.get(4).get(1);
		resp = given().when().get(urlServer + "/"+att_path4+"");
		statusCode = resp.getStatusCode();
		body = resp.andReturn().body().asString();
		Assert.assertNotEquals(statusCode, 200);
		Assert.assertEquals(body, "{}");

	}

}
