package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import models.TransferRequest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ChangeLicencesTeamTests extends BaseTest {

    public static final String CHANGE_TEAM_ENDPOINT = "/customer/changeLicensesTeam";
    private static final String EXPIRED_LICENSE_ID = "OIY94JBDUQ";
    private static final String INVALID_LICENSE_ID = "INVALID";
    private static final String VALID_TEAM_ID = "";
    private static final String INVALID_TEAM_ID = "";
    public static final int TEAM_1_ID = 2573297;
    public static final int TEAM_2_ID = 2717496;

    @Test
    void testTransferLicense_SingleLicense() {
        given()
                .header("X-Api-Key", API_KEY_COMPANY_ADMIN)
                .header("X-Customer-Code", CUSTOMER_CODE)
                .header("Content-Type", "application/json")
                .body(new TransferRequest(List.of("U886DF2U2D"), TEAM_2_ID))
                .when()
                .post(CHANGE_TEAM_ENDPOINT)
                .then()
                .statusCode(200);
    }

    @Test
    void testTransferLicense_MultipleLicense() {
        given()
                .header("X-Api-Key", API_KEY_COMPANY_ADMIN)
                .header("X-Customer-Code", CUSTOMER_CODE)
                .header("Content-Type", "application/json")
                .body(new TransferRequest(List.of("JKFK70A7F0", "07WB7RP9UB"), TEAM_2_ID))
                .when()
                .post(CHANGE_TEAM_ENDPOINT)
                .then()
                .statusCode(200);
    }

    // are there any licences not eligible for transfer?
    @Test
    void testlLicenseTransfer_Partial() {
        given()
                .header("X-Api-Key", API_KEY_COMPANY_ADMIN)
                .header("X-Customer-Code", CUSTOMER_CODE)
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(new TransferRequest(List.of("PZ5RBM8QZA", EXPIRED_LICENSE_ID), TEAM_2_ID))
                .when()
                .post(CHANGE_TEAM_ENDPOINT)
                .then()
                .statusCode(200);
    }

    @Test
    void testLicenseTransfer_AssignedToUser() {
        given()
                .header("X-Api-Key", API_KEY_COMPANY_ADMIN)
                .header("X-Customer-Code", CUSTOMER_CODE)
                .contentType(ContentType.JSON)
                .body(new TransferRequest(List.of("WT6QU3QO7S"), TEAM_2_ID))
                .when()
                .post(CHANGE_TEAM_ENDPOINT)
                .then()
                .statusCode(200);
    }

    @Test
    void testLienseTransfer_LicenseKey() {
        given()
                .header("X-Api-Key", API_KEY_COMPANY_ADMIN)
                .header("X-Customer-Code", CUSTOMER_CODE)
                .header("Content-Type", "application/json")
                .body(new TransferRequest(List.of("T5JTU1RVR6"), TEAM_2_ID))
                .when()
                .post(CHANGE_TEAM_ENDPOINT)
                .then()
                .statusCode(200);
    }

    @Test
    void testTransferLicense_InvalidILicenseId() {
        given()
                .header("X-Api-Key", API_KEY_COMPANY_ADMIN)
                .header("X-Customer-Code", CUSTOMER_CODE)
                .header("Content-Type", "application/json")
                .body(new TransferRequest(List.of(INVALID_LICENSE_ID), TEAM_2_ID))
                .when()
                .post(CHANGE_TEAM_ENDPOINT)
                .then()
                .statusCode(404)
                .body("code", notNullValue())
                .body("description", containsString("invalid"));
    }

    @Test
    void testTransferLicense_MissingId() {
        given()
                .header("X-Api-Key", API_KEY_COMPANY_ADMIN)
                .header("X-Customer-Code", CUSTOMER_CODE)
                .contentType(ContentType.JSON)
                .body("{ \"targetTeamId\": 2717496 }")
                .when()
                .post(CHANGE_TEAM_ENDPOINT)
                .then()
                .statusCode(400);
    }

    @Test
    void testTransferLicense_MissingTeamId() {
        given()
                .header("X-Api-Key", API_KEY_COMPANY_ADMIN)
                .header("X-Customer-Code", CUSTOMER_CODE)
                .contentType(ContentType.JSON)
                .body("{ \"licenseIds\": [\"WT6QU3QO7S\"] }")
                .when()
                .post(CHANGE_TEAM_ENDPOINT)
                .then()
                .statusCode(400);
    }

    @Test
    void testTransferLicense_WrongUserType() {
        given()
                .header("X-Api-Key", API_KEY_VIEWER)
                .header("X-Customer-Code", CUSTOMER_CODE)
                .contentType(ContentType.JSON)
                .body(new TransferRequest(List.of("WT6QU3QO7S"), TEAM_2_ID))
                .when()
                .post(CHANGE_TEAM_ENDPOINT)
                .then()
                .statusCode(403);
    }

    @Test
    void testTransferLicense_InvalidCustomerCode() {
        given()
                .header("X-Customer-Code", "invalid-code")
                .contentType(ContentType.JSON)
                .body(new TransferRequest(List.of("WT6QU3QO7S"), 1234))
                .when()
                .post(CHANGE_TEAM_ENDPOINT)
                .then()
                .statusCode(403);
    }

    @Test
    void testTransferLicense_LicenseListIsEmpty() {
        given()
                .header("X-Customer-Code", API_KEY_COMPANY_ADMIN)
                .contentType(ContentType.JSON)
                .body(new TransferRequest(List.of(), 1234))
                .when()
                .post(CHANGE_TEAM_ENDPOINT)
                .then()
                .statusCode(400);
    }

    @Test
    void testTransferLicense_TargetTeamNotExists() {
        given()
                .header("X-Customer-Code", API_KEY_COMPANY_ADMIN)
                .contentType(ContentType.JSON)
                .body(new TransferRequest(List.of("WT6QU3QO7S"), 999999))
                .when()
                .post(CHANGE_TEAM_ENDPOINT)
                .then()
                .statusCode(400)
                .body("description", containsString("not exist"));
    }

}
