package setup;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;


public class Base {

    @BeforeClass
    public void initiateRestAssured() {
        RestAssured.baseURI = "http://13.126.80.194:8080";
    }
}
