package userManagement;

import core.BaseTest;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.*;
import org.testng.annotations.Test;
import utils.ExtentReport;

import static io.restassured.RestAssured.given;

public class getPostmanEcho extends BaseTest {
    @Test(groups = "smoketest")
    // Automating Digest Auth using rest Assured
    public void testBasicAuth(){
        Response resp = given().auth().digest("postman","password").when().get("https://postman-echo.com/digest-auth");
        int statsCode = resp.statusCode();
        Assert.assertEquals(200, statsCode);
        System.out.println(resp.body().asString());

    }

    @Test(groups = {"regressionTest"})
    // Automating Digest Auth using rest Assured
    public void testDigestAuth(){
        ExtentReport.extentLog = ExtentReport.extentReports.startTest("Test Digest Auth", "To test the functionality of Digest Auth");
        Response resp = given().auth().digest("postman","password").when().get("https://postman-echo.com/digest-auth");
        int statsCode = resp.statusCode();
        Assert.assertEquals(200, statsCode);
        System.out.println(resp.body().asString());

    }


        }


