package base;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public abstract class BaseTest {

    public static final String CUSTOMER_CODE = "6703615";
    protected static final String API_KEY_COMPANY_ADMIN = "";
    protected static final String API_KEY_TEAM_ADMIN = "";
    protected static final String API_KEY_VIEWER = "";

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://account.jetbrains.com/api/v1";
        RestAssured.defaultParser = Parser.JSON;
        //unassignLicense();
    }

    private static void unassignLicense() {
        /*Response response = given()
                .header("X-Api-Key", "cm0ipchn70a83kqr5n3hs71w5")
                .header("X-Customer-Code", "6703615")
                //.header("licenseId", "WT6QU3QO7S")
                .post("/customer/licenses/revoke?licenseId=WT6QU3QO7S");

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.getBody().asPrettyString());*/
    }

    @AfterEach
    void tearDown(){
    }
}