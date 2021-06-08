import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EmployeeListTests {

    @BeforeTest
    public void setUp() {
        //Specify base URI
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
    }

    @Test
    public void getEmployeeList() {
        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //Response object
        Response response = httpRequest.request(Method.GET, "/employees");

        //Put the response to the variable and print it in console window
        String responseBody = response.getBody().asPrettyString();
        System.out.println("Response body is: " + responseBody);

        //Status code verification
        int statusCode = response.getStatusCode();
        System.out.println("Status code is " + statusCode);
        Assert.assertEquals(statusCode, 200);

        //Status line verification
        String statusLine = response.getStatusLine();
        System.out.println("Status line is: " + statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

    }
}
