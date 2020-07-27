package testcases;

import Common.controller.EndPoints;
import Util.RestUtils;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.controller.GetallCustRecords;
import setup.Base;

public class Scenarios extends Base {
    EndPoints endPoints;

    @BeforeClass
    public void initializeObjects() {
        endPoints = new EndPoints();
    }

    @Test
    public void login() {
        Response response = RestUtils.getAuthToken(endPoints.AUTH, "rupeek", "password");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void getOneCustDetails() {
        Response response = GetallCustRecords.getdetails(endPoints.AUTH, endPoints.GET_CUSTOMER_DETAIL_USING_PHONE + "8037602400", "rupeek", "password");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.path("phone"), "8037602400");
        Assert.assertEquals(response.path("first_name"), "Aliko");
        Assert.assertEquals(response.path("last_name"), "Dangote");
        Assert.assertEquals(response.path("career"), "Billionaire Industrialist");
    }

    @Test
    public void VerifyByPassingWrongPhone() {
        Response response = GetallCustRecords.getdetails(endPoints.AUTH, endPoints.GET_CUSTOMER_DETAIL_USING_PHONE + "8037602", "rupeek", "password");
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test
    public void VerifyByPassingThePhoneforWhichDataIsNotThereInDB() {
        Response response = GetallCustRecords.getdetails(endPoints.AUTH, endPoints.GET_CUSTOMER_DETAIL_USING_PHONE + "8037602", "rupeek", "password");
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test
    public void getAllCustDetails() {
        Response response = GetallCustRecords.getdetails(endPoints.AUTH, endPoints.GET_ALL_CUSTOMER, "rupeek", "password");
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("Lists of phone numbers are " + response.path("phone"));
        System.out.println("Lists of first names are " + response.path("first_name"));
        System.out.println("Lists of last names are " + response.path("last_name"));
        System.out.println("Lists of careers are " + response.path("career"));
    }

    @Test
    public void VerifyWithWrongURL() {
        Response response = GetallCustRecords.getdetails(endPoints.AUTH, endPoints.WRONG_URL, "rupeek", "password");
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test
    public void VerifyForUnAuthenticatedUsers() {
        Response response = GetallCustRecords.VerifyWithEmptyToken(endPoints.AUTH, endPoints.GET_ALL_CUSTOMER, "rupeekjhgk", "password");
        Assert.assertEquals(response.getStatusCode(), 401);
    }

    @Test
    public void VerifyForUnAuthorisedUsers() {
        Response response = GetallCustRecords.getdetails(endPoints.AUTH, endPoints.GET_ALL_CUSTOMER, "rupeekkkk", "password");
        Assert.assertEquals(response.getStatusCode(), 401);
    }

    @Test
    public void VerifywithemptyUnAndPwd() {
        Response response = GetallCustRecords.getdetails(endPoints.AUTH, endPoints.GET_ALL_CUSTOMER, "", "");
        Assert.assertEquals(response.getStatusCode(), 401);
    }

    @Test
    public void VerifywithemptsyUnAndPwd() {
        Response response = GetallCustRecords.getdetails(endPoints.AUTH, endPoints.GET_ALL_CUSTOMER, "", "");
        Assert.assertEquals(response.getStatusCode(), 401);
    }

}
