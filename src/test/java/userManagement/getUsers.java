package userManagement;

import core.StatusCode;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import utils.JsonReader;
import utils.PropertyReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static utils.JsonReader.getJsonArray;


public class getUsers {
    // Automate Query Parameter with rest Assured.
    @Test
    public void validateStatusCodeGetUser() {
        Response response = given().
                queryParam("page", 2).
                when().get("https://reqres.in/api/users");
        int statusCode = response.getStatusCode(); // restAssured
        Assert.assertEquals(statusCode, 200); // testNG
    }

    @Test
    //Pass multiple Query with Rest Assured.
    public void passMultipleQueryParameter() {
        Response res = given().
                queryParam("page", 2).
                queryParam("per_page", 2).
                when().get("https://reqres.in/api/users").
                then().statusCode(200).extract().response();
    }

    @Test
    public void automatePathParameter() {
        // Automate Path Parameter in restAssured.
        String year = "2017";
        Response res = given().
                pathParam("raceSeason", year).
                when().get("http://ergast.com/api/f1/{raceSeason}/circuits.json");
        int actualStatusCode = res.statusCode();
        Assert.assertEquals(actualStatusCode, 200);
        System.out.println(res.body().asString());

    }

    @Test
    public void createUserUsingFormParameter() {
        //Automate form parameter using Rest Assured.
        Response res = given().
                contentType("application/x-www-form-urlencoded").
                formParam("name", "John Doe").
                formParam("job", "Developer").
                when().
                post("https://reqres.in/users").
                then().
                statusCode(201).extract().response();

        res.then().body("name", equalTo("John Doe"));
        res.then().body("job", equalTo("Developer"));
    }

    @Test
    public void testGetUserListWithHeader() {
        // Automate single header using Rest Assured.
        Response res = given().
                header("Content-Type", "application/json").queryParam("page", 2).
                when().get("https://reqres.in/api/users");

        int statusCode = res.statusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    public void testGetUserListwithMultipleHeaderAndMapConcept() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "bearer lkjsndkjnckjnkjnkjnkjnjnjknkj");

        Response res = given().headers(headers).

                queryParam("page", 2).
                when().get("https://reqres.in/api/users");

        int actualStatusCode = res.statusCode();
        Assert.assertEquals(actualStatusCode, 200);


    }

    @Test
    // Fetch the Response headers
    public void testFetchHeader() {
        Response response = given().
                queryParam("page", 2).
                when().get("https://reqres.in/api/users").then().extract().response();

        Headers headers = response.getHeaders();
        for (Header h : headers) {
            if (h.getName().contains("Server")) {
                System.out.println(h.getName() + " : " + h.getValue());
                Assert.assertEquals("cloudflare", h.getValue());
            }

        }
    }

    @Test
    // Automate cookies using Rest Assured (Not that important)
    //First Approach
    public void testCookies() {
        Response response = given().cookie("test", "testing").queryParam("page", 2)
                .when().get("https://reqres.in/api/users");

        int statsCode = response.statusCode();
        Assert.assertEquals(200, statsCode);


    }

    @Test
    //Cookie handling using cookie builder
    public void testCookieBuilderMethod() {
        Cookie cookies = new Cookie.Builder("test", "testing").setComment("using cookies key").build();
        Response response = given().cookie(cookies).queryParam("page", 2)
                .when().get("https://reqres.in/api/users");

        int statsCode = response.statusCode();
        Assert.assertEquals(200, statsCode);

    }

    @Test
    // cookie fetching using map concept
    public void testCookiesUsingMapConcept(){

        Response response = given().when().get("https://reqres.in/api/users?page=2");

        Map<String, String> cookie = response.getCookies();
        Assert.assertEquals(cookie, hasKey("JSESSIONID"));
        Assert.assertEquals(cookie, hasKey("ABCD1234"));
    }

    @Test
    //Cookie fetching using get detailed cookies
    public void testCookiesUsingDetailedCookies(){
        Response response = given().when().get("https://reqres.in/api/users?page=2");
        Cookies cookies1 = response.getDetailedCookies();
        cookies1.getValue("server");
        Assert.assertEquals(cookies1.getValue("server"), "cloudflare");


    }

    @Test
    // Automate basic auth using rest assured
    public void testBasicAuth(){
        Response resp = given().auth().basic("postman","password").when().get("https://postman-echo.com/basic-auth");
        int statsCode = resp.statusCode();
        Assert.assertEquals(200, statsCode);
        System.out.println(resp.body().asString());


    }


    @Test
    // Automate DELETE API using Rest Assured
    public void testDeleteApi(){
        Response resp = given().when().delete("https://reqres.in/api/users/2");
        Assert.assertEquals(StatusCode.NO_CONTENT.code, resp.getStatusCode());

    }


    @Test
    // Automate basic auth using rest assured
    public void validateWithTestData() throws IOException, ParseException {
        String username = JsonReader.getTestData("username");
        String password = JsonReader.getTestData("password");
        System.out.println(username + " : " + password);

        Response resp = given().auth().basic(username , password).when().get("https://postman-echo.com/basic-auth");
        int statsCode = resp.statusCode();
        Assert.assertEquals(200, statsCode);
        System.out.println(resp.body().asString());
        System.out.println("validateWithTestData executed successfully");


    }

    @Test
    // Fetch the Response headers
    public void ValidatewithDatafromPropertiesFile() {
        String serverAddress = PropertyReader.propertyReader("config.properties", "serverAddress");
        Response response = given().
                queryParam("page", 2).
                when().get(serverAddress).then().extract().response();

        Headers headers = response.getHeaders();
        for (Header h : headers) {
            if (h.getName().contains("Server")) {
                System.out.println(h.getName() + " : " + h.getValue());
                Assert.assertEquals("cloudflare", h.getValue());
            }

        }
    }
    @Test
    // Fetch the Response headers
    public void ValidateWithTestAndPropertiesData() throws IOException, ParseException {
        String serverAddress = PropertyReader.propertyReader("config.properties","server");
        String URL = JsonReader.getTestData("endpoint");
        Response response = given().
                queryParam("page", 2).
                when().get(serverAddress + URL).then().extract().response();

        Headers headers = response.getHeaders();
        for (Header h : headers) {
            if (h.getName().contains("Server")) {
                System.out.println(h.getName() + " : " + h.getValue());
                Assert.assertEquals("cloudflare", h.getValue());
            }

        }
    }

    @Test
    public void hardAssertDemo(){
        System.out.println("HardAssert");
        Assert.assertTrue(false);
        System.out.println("HardAssert");

    }
    @Test
    public void SoftAssertDemo(){
        SoftAssert softAssert = new SoftAssert();
        System.out.println("softAssert");
        softAssert.assertTrue(false);
        System.out.println("softAssert");
        softAssert.assertAll();

    }

    @DataProvider(name="testData")
    public Object[][] testData(){
        return new Object[][]{
                {"1", "John"},
                {"2", "jane"},
                {"3", "Bob"}

        };
    }

    @org.testng.annotations.Test(dataProvider  = "testData")
    @Parameters({"id", "name"})
    public void testEndpoint(String id, String name){
        given().queryParam("id")
                .queryParam("name")
                .when().get("https://reqres.in/api/users/").then().statusCode(200);
    }

    @Test
    public void test() throws IOException, ParseException {
        Object result =JsonReader.getArrayData("languages", 1);
        System.out.println(result);
        JSONArray jsonArray = getJsonArray("contacts");
        Iterator<String> iterator = jsonArray.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }



}






