package base;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    public static final String API_KEY_COMPANY_ADMIN = "";
    public static final String API_KEY_TEAM_ADMIN = "";
    public static final String API_KEY_VIEWER = "";
    public static final String CUSTOMER_CODE = "6703615";
    public static final String CHANGE_TEAM_ENDPOINT = "/customer/changeLicensesTeam";
    public static final String ASSIGN_LICENSE_ENDPOINT = "/customer/licenses/assign";
    public static final int TEAM_1_ID = 2573297;
    public static final int TEAM_2_ID = 2717496;
    protected static final int INVALID_TEAM_ID = 999999;
    protected static final String EXPIRED_LICENSE_ID = "OIY94JBDUQ";
    protected static final String INVALID_LICENSE_ID = "INVALID";

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://account.jetbrains.com/api/v1";
        RestAssured.defaultParser = Parser.JSON;
    }

}