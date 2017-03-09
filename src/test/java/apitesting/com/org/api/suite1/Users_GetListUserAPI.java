package apitesting.com.org.api.suite1;

import org.testng.Assert;
import org.testng.annotations.*;
import com.jayway.restassured.response.Response;
import apitesting.com.org.utils.Utils;
import static com.jayway.restassured.RestAssured.*;

/**
 * 
 * @author Hien HOANG
 *
 */
public class Users_GetListUserAPI {

	// Local variable
	Response resp;
	int statusCode;
	String body;

	Utils util;
	String urlServer;
	String invalidUrlServer;

	/**
	 * Method setUp() to prepare : URL invalid and valid Return 2 URLs for using
	 * later.
	 */
	@BeforeClass
	public void setUp() throws Exception {
		util = new Utils();
		urlServer = util.URLComposition();
		invalidUrlServer = util.InvalidURLComposition();
	}

	/**
	 * Method Test_ListUsers() to get list of users Return response code = 200
	 * so expect to have 200 as response code
	 */
	@Test
	public void Test_ListUsers() {

		Response resp = when().get(urlServer);

		int statusCode = resp.getStatusCode();
		String body = resp.andReturn().body().asString();
		System.out.println("Test case no 1 " + body);

		Assert.assertEquals(statusCode, 200);
		Assert.assertNotEquals(body, "{}");

	}

	/**
	 * Method Test_ListUsers_UnhappyCase() Return response code != 200 so expect
	 * this test case to be failed
	 */
	@Test
	public void Test_ListUsers_UnhappyCase() {
		Response resp = when().get(invalidUrlServer);

		int statusCode = resp.getStatusCode();
		String body = resp.andReturn().body().asString();
		System.out.println("Test case no 2 " + body);

		Assert.assertNotEquals(statusCode, 200);
		Assert.assertEquals(body, "{}");

	}

}
