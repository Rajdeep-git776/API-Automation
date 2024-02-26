package userManagement;

import core.BaseTest;
import core.StatusCode;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.CityRequest;
import pojo.PostRequestBody;
import utils.ExtentReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostUsers extends BaseTest {
    private static FileInputStream FileInputStreamMethod(String requestBodyFileName) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(System.getProperty("user.dir") + "//resources//TestData//" + requestBodyFileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return fileInputStream;

    }

    @Test
    public void validatePostWithString() {
        Response res = given().header("Content-Type", "application/json").
                body("{\"name\":\"morpheus\",\"job\":\"leader\"}").
                when().
                post("https://reqres.in/api/users");

        Assert.assertEquals(res.getStatusCode(), 201);
        System.out.println(res.getBody().asString());
    }


    @Test
    public void validatePutWithString() {
        Response res = given().header("Content-Type", "application/json").
                body("{\"name\":\"morpheus\",\"job\":\"zionresident\"}").
                when().
                put("https://reqres.in/api/users/2");

        Assert.assertEquals(res.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(res.getBody().asString());
    }

    @Test
    public void validatePatchWithString() {
        Response res = given().header("Content-Type", "application/json").
                body("{\"name\":\"morpheus\"}").
                when().
                patch("https://reqres.in/api/users/2");

        Assert.assertEquals(res.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(res.getBody().asString());
    }

    @Test
    public void validatePostWithJsonFile() throws IOException {
        Response res = given().header("Content-Type", "application/json").
                body(IOUtils.toString(FileInputStreamMethod("PostRequestBody.json"))).
                when().
                post("https://reqres.in/api/users");

        Assert.assertEquals(res.getStatusCode(), 201);
        System.out.println(res.getBody().asString());
    }

    @Test
    public void validatePatchWithJsonFile() throws IOException {
        Response res = given().header("Content-Type", "application/json").
                body(IOUtils.toString(FileInputStreamMethod("PatchRequestBody.json"))).
                when().
                patch("https://reqres.in/api/users/2");

        Assert.assertEquals(res.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(res.getBody().asString());
    }

    @Test
    public void validatePutWithJsonFile() throws IOException {
        Response res = given().header("Content-Type", "application/json").
                body(IOUtils.toString(FileInputStreamMethod("PutRequestBody.json"))).
                when().
                put("https://reqres.in/api/users/2");

        Assert.assertEquals(res.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(res.getBody().asString());
    }

    @Test
    public void validatePostWithPOJO() {
        PostRequestBody postReqBody = new PostRequestBody();
        postReqBody.setJob("leader");
        postReqBody.setName("morpheus");
        Response res = given().header("Content-Type", "application/json").
                body(postReqBody).
                when().
                post("https://reqres.in/api/users");

        Assert.assertEquals(res.getStatusCode(), 201);
        System.out.println(res.getBody().asString());
    }

    @Test(description = "validatePostWithPOJOList")
    public void validatePostWithPOJOList() {
        ExtentReport.extentLog = ExtentReport.extentReports.startTest("validatePostWithPOJOLists", "To Test the Post functionality with POJO List");
        List<String> listLanguage = new ArrayList<>();
        listLanguage.add("java");
        listLanguage.add("python");
        PostRequestBody postReqBody = new PostRequestBody();
        postReqBody.setJob("leader");
        postReqBody.setName("morpheus");
        postReqBody.setLanguages(listLanguage);
        Response res = given().header("Content-Type", "application/json").
                body(postReqBody).
                when().
                post("https://reqres.in/api/users");

        Assert.assertEquals(res.getStatusCode(), 201);
        System.out.println(res.getBody().asString());
    }

    @Test
    public void validatePutWithPOJO() {
        ExtentReport.extentLog = ExtentReport.extentReports.startTest("validatePutWithPOJO", " To validate POJO with Put");
        PostRequestBody putReqBody = new PostRequestBody();
        putReqBody.setJob("zion resident");
        putReqBody.setName("morpheus");

        Response res = given().header("Content-Type", "application/json").
                body(putReqBody).
                when().
                put("https://reqres.in/api/users/2");

        Assert.assertEquals(res.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(res.getBody().asString());
    }

    @Test
    public void validatePatchWithPOJO() {
        ExtentReport.extentLog = ExtentReport.extentReports.startTest("validatePatchWithPOJO", " To validate POJO with Patch");
        PostRequestBody patchReqBody = new PostRequestBody();
        patchReqBody.setName("zion resident");


        Response res = given().header("Content-Type", "application/json").
                body(patchReqBody).
                when().
                patch("https://reqres.in/api/users/2");

        Assert.assertEquals(res.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(res.getBody().asString());
    }

    @Test
    public void validatePostWithPOJOListObject() {
        ExtentReport.extentLog = ExtentReport.extentReports.startTest("validatePostWithPOJOListOject", "To Test the Post functionality with POJO Objects");
        List<String> listLanguage = new ArrayList<>();
        List<CityRequest> crList = new ArrayList<>();


        listLanguage.add("java");
        listLanguage.add("python");

        PostRequestBody postReqBody = new PostRequestBody();
        postReqBody.setJob("leader");
        postReqBody.setName("morpheus");
        postReqBody.setLanguages(listLanguage);

        CityRequest cr1 = new CityRequest();
        cr1.setName("Bangalore");
        cr1.setTemperature("30");
        CityRequest cr2 = new CityRequest();
        cr2.setName("Guwahati");
        cr2.setTemperature("35");
        crList.add(cr1);
        crList.add(cr2);
        postReqBody.setCityList(crList);


        Response res = given().header("Content-Type", "application/json").
                body(postReqBody).
                when().
                post("https://reqres.in/api/users");

        Assert.assertEquals(res.getStatusCode(), 201);
        System.out.println(res.getBody().asString());
    }

    @Test(description = "validate post with pojo list object response")
    public void validatePostWithPOJOListObjectResponse() {
        String name= "Bangalore";
        String temperature ="30";
        ExtentReport.extentLog = ExtentReport.extentReports.startTest("validatePostWithPOJOListOject", "To Test the Post functionality with POJO Objects");
        List<String> listLanguage = new ArrayList<>();
        List<CityRequest> crList = new ArrayList<>();


        listLanguage.add("java");
        listLanguage.add("python");

        PostRequestBody postReqBody = new PostRequestBody();
        postReqBody.setJob("leader");
        postReqBody.setName("morpheus");
        postReqBody.setLanguages(listLanguage);

        CityRequest cr1 = new CityRequest();
        cr1.setName(name);
        cr1.setTemperature(temperature);
        CityRequest cr2 = new CityRequest();
        cr2.setName("Guwahati");
        cr2.setTemperature("35");
        crList.add(cr1);
        crList.add(cr2);
        postReqBody.setCityList(crList);


        Response res = given().header("Content-Type", "application/json").
                body(postReqBody).
                when().
                post("https://reqres.in/api/users");
        PostRequestBody respBody = res.as(PostRequestBody.class);
        System.out.println(respBody.getCityList().get(0).getName());
        System.out.println(respBody.getCityList().get(0).getTemperature());
        Assert.assertEquals(name, respBody.getCityList().get(0).getName());
        Assert.assertEquals(temperature, respBody.getCityList().get(0).getTemperature());
        System.out.println(respBody.getLanguages());


        Assert.assertEquals(res.getStatusCode(), 201);
        System.out.println(res.getBody().asString());


    }
}
