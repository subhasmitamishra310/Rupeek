package Util;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class RestUtils {
    public static Response getAuthToken(String testurl, String username, String password) {
        Map<String, Object> jsonAsMap = new HashMap();
        jsonAsMap.put("username", username);
        jsonAsMap.put("password", password);
        Response response = RestAssured.given().
                contentType("application/json").
                body(jsonAsMap).
                when().log().all().post(testurl).
                then().extract().response();
        return response;
    }
}
