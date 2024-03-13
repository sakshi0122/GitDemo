package com.userapi;

//package com.cg;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserApiTest
{
    @Test
    public void GetUserDetails()
    {
        RestAssured.baseURI="https://reqres.in";

        RequestSpecification httpRequest = RestAssured.given();

        Response responseData = httpRequest.request(Method.GET,"/api/users");

        System.out.println("Status Retrieved= "+responseData.getStatusLine());

        System.out.println("Response id "+responseData.prettyPrint());

        
    }

}
