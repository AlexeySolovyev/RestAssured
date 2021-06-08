import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EmployeeUpdateTests {

    @BeforeTest
    public void setUp() {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
    }

    @Test
    public void UpdateEmployeeTest() {
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject requestParameters = new JSONObject();

        requestParameters.put("name", "test2");
        requestParameters.put("salary", "1000");
        requestParameters.put("age", "37");

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParameters.toJSONString());
        Response response = httpRequest.request(Method.PUT, "/update/2");

        String responseBody = response.getBody().asPrettyString();
        System.out.println("Response body is: " + responseBody);

        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, 200);

        String message = response.jsonPath().get("message");
        Assert.assertEquals(message, "Successfully! Record has been updated.");
    }
}
