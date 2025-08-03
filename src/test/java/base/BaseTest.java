package base;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public abstract class BaseTest {

    public static final String CUSTOMER_CODE = "6703615";
    public static final String CHANGE_TEAM_ENDPOINT = "/customer/changeLicensesTeam";
    public static final String API_KEY_COMPANY_ADMIN = "";
    public static final String API_KEY_TEAM_ADMIN = "";
    public static final String API_KEY_VIEWER = "";

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://account.jetbrains.com/api/v1";
        RestAssured.defaultParser = Parser.JSON;
        //unassignLicense();
    }

    @AfterEach
    void tearDown(){
    }
}