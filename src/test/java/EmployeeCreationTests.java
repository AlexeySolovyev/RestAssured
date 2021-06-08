import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EmployeeCreationTests {

    @BeforeTest
    public void setUp() {
        // Specify base URI
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
    }

    @Test
    public void CreateEmployeeTest() {
        // Request object
        RequestSpecification httpRequest = RestAssured.given();

        //Request payload sending along POST request

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "test");
        requestParams.put("salary", "123");
        requestParams.put("age", "24");

        // Header of POST request
        httpRequest.header("Content-Type", "application/json");

        // Put the JSON payload to httpRequest
        httpRequest.body(requestParams.toJSONString());

        // Response object
        Response response = httpRequest.request(Method.POST, "/create");

        // Print response in console window
        String responseBody = response.getBody().asPrettyString();
        System.out.println("Response body is: " + responseBody);

        // Status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, 200);

        // Find a word "success" in the JSON response
        String message = response.jsonPath().get("message");
        Assert.assertEquals(message, "Successfully! Record has been added.");
    }

}
