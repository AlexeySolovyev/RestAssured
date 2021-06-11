package publicApi;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EmployeeDeleteionTests {

    @BeforeTest
    public void setUp() {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
    }

    @Test
    public void deleteEmployee() {
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.DELETE, "/delete/4");

        String responseBody = response.getBody().asPrettyString();
        System.out.println("Response is: " + responseBody);

        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, 200);

        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

        String message = response.jsonPath().get("message");
        Assert.assertEquals(message, "Successfully! Record has been deleted");

    }
}
