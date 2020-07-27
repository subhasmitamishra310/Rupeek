package service.controller;

import Common.controller.EndPoints;
import Util.RestUtils;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;


public class GetallCustRecords {

    public static Response getdetails(String AuthEndPoint, String CurrentendPoint, String userName, String password) {
        String token = RestUtils.getAuthToken(AuthEndPoint, userName, password).getBody().jsonPath().get("token");
        Response response = RestAssured.given().header("Authorization", "Bearer " + token).
                get(CurrentendPoint).then().extract().response();
        return response;
    }

    public static Response VerifyWithEmptyToken(String AuthEndPoint, String CurrentendPoint, String userName, String password) {
        String token = RestUtils.getAuthToken(AuthEndPoint, userName, password).getBody().jsonPath().get("token");
        Response response = RestAssured.given().header("Authorization", "Bearer " + "").
                get(CurrentendPoint).then().extract().response();
        return response;
    }
}