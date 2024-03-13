//package com.userapi;

package com.library.tests;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostABook2 {
    RequestSpecification request;
    String baseuri="http://localhost:3000/";
    Response response;
    JSONObject jobject;
    JsonPath json;

    @BeforeSuite()
    public void init()
    {
        // req configuration
        request=RestAssured.given();
        request.baseUri(baseuri);

        //request.header("G-Token", "ROM831ESV");
        HashMap<String,String> hmap=new HashMap<String, String>();
        hmap.put("G-Token", "ROM831ESV");
        hmap.put("Content-Type","application/json");
        request.headers(hmap);
    }

    @Test(dataProvider = "getBooks") //(dataProvider = "takeBooksData")
    public void testData(String title, String author, String iasbn)
    {

        jobject=new JSONObject();// used for creating a payload     
        jobject.put("title", title);
        jobject.put("author",author);
        jobject.put("iasbn",iasbn );

        request.body(jobject.toJSONString());       
        response=request.post("books");     
        json=new JsonPath(response.asString());
    }

    @Test
    public void testPostBookStatus()
    {
        Assert.assertEquals(response.getStatusCode(), 201);
        System.out.println("id::::::"+json.get("id"));
        Assert.assertNotNull(json.get("id"));
        System.out.println(response.asString());
        System.out.println();
    }

    @DataProvider //(name="takeBooksData")
    public Object[][] getBooks()
    {
        return new Object[][] {
            {"Book11","Author11", "iasbn11"},
            {"Book21","Author21", "iasbn21"},
            {"Book31","Author31", "iasbn31"},
            {"Book41","Author41", "iasbn41"},
            {"Book51","Author51", "iasbn51"},
        };

    }
}