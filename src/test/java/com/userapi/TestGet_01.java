package com.userapi;



import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

public class TestGet_01 {
    RequestSpecification request;
    String baseuri="https://reqres.in";
    Response response;
    JSONObject jobject;
    JsonPath json;

    @Test
    public void test() {


        response = RestAssured.get("https://reqres.in/api/users?page=2");

        System.out.println(response.statusCode());
        System.out.println(response.asString());
        System.out.println(response.getBody().asString());
        json=new JsonPath(response.asString());
        System.out.println("JSON Response \n"+json);
        System.out.println(response.statusLine());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

//  @Test
//  public void test1() {
//
//      given().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("data.id[0]", equalTo(7)).log().all();
//
//  }

    //TC for statuscode for GET req
    @Test(priority = 1)
    public void statusCodeCheckTest() 
    {
        //response= RestAssured.get(endpoint);
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());//200
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    //status line is ok
    @Test(priority = 2)
    public void statusLineTest()
    {
        Assert.assertTrue(response.getStatusLine().contains("OK"));
    }

    //check the time of response  < 300ms
    @Test(priority = 3)
    public void responseTimeTest() {
        Assert.assertTrue(response.getTime() < 300);
    }

    //content type - json
    @Test(priority = 4)
    public void responseContentType()
    {
        Assert.assertTrue(response.getHeader("Content-Type").contains("json"));
    }


    @Test(priority = 5) 
    public void checkFirstEle() 
    {
        System.out.println("Name of the user is :::"+json.get("data[0].first_name"));
        Assert.assertEquals(json.get("data[0].first_name"),"Michael");
        Assert.assertEquals(json.get("data[0].last_name"),"Lawson" );
        Assert.assertEquals(json.get("data[0].email"),"michael.lawson@reqres.in"); 
        
    }

//  @Test(dataProvider = "getData") 
//  public void checkAllData(String fname,String lname) 
//  {
//      Assert.assertEquals(json.get("data["+count+"].first_name"),fname);
//      Assert.assertEquals(json.get("data["+count+"].last_name"),lname); count++;
//
//  }
//
//  @DataProvider
//  public Object[][] getData() 
//  {
//      return TestData.getData("MyTestCaseData.xlsx", "up"); 
//  }


}
