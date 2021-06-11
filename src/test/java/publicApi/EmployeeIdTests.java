package publicApi;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmployeeIdTests {

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
    }

    @Test
    public void getEmployeeById() {

        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/employee/1");
        String responseBody = response.getBody().asPrettyString();
        System.out.println("Response body is: " + responseBody);

        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, 200);

        String statusLine = response.getStatusLine();
        System.out.println("Status line is: " + statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

        String message = response.jsonPath().get("message");
        Assert.assertEquals(message, "Successfully! Record has been fetched.");
    }
}
