package com.userapi;
import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostTest 
{

    RequestSpecification request;
    String baseuri="https://reqres.in";
    Response response;
    JSONObject jobject;
    JsonPath json;

    @BeforeSuite
    public void init()
    {
        request=RestAssured.given();
        request.baseUri(baseuri);

        // create a payload to post -first way
        //request.body("{ \"name\" : \"priti\",\"job\" : \"SME\"}"); //{"name":"Priti","job","Tester"}

        // create a payload to post- second way
        jobject=new JSONObject();
        jobject.put("name","Anand");
        jobject.put("job","Tester");
        request.header("Content-Type","application/json");
        request.body(jobject.toJSONString());       



        response=request.post("/api/users");
        json=new JsonPath(response.asString());

        System.out.println("json value is --> "+json);
        System.out.println("value of key -id is "+json.getString("id"));
      //  System.out.println(json.get());


    }

    @Test
    public void checkPostStatusTest()
    {
        Assert.assertEquals(response.getStatusCode(),201);
        System.out.println(response.asString());
    }


}