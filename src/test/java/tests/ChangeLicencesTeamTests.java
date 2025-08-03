package tests;

import base.BaseTest;
import helpers.LicenseHelper;
import io.restassured.http.ContentType;
import models.TransferRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ChangeLicencesTeamTests extends BaseTest {

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
                .statusCode(200);
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
                .statusCode(404);
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
                .header("X-Api-Key", API_KEY_TEAM_ADMIN)
                .header("X-Customer-Code", "invalid-code")
                .contentType(ContentType.JSON)
                .body(new TransferRequest(List.of("WT6QU3QO7S"), TEAM_2_ID))
                .when()
                .post(CHANGE_TEAM_ENDPOINT)
                .then()
                .statusCode(401);
    }

    @Test
    void testTransferLicense_LicenseListIsEmpty() {
        given()
                .header("X-Api-Key", API_KEY_COMPANY_ADMIN)
                .header("X-Customer-Code", CUSTOMER_CODE)
                .contentType(ContentType.JSON)
                .body(new TransferRequest(List.of(), TEAM_2_ID))
                .when()
                .post(CHANGE_TEAM_ENDPOINT)
                .then()
                .statusCode(200);
    }

    @Test
    void testTransferLicense_TargetTeamNotExists() {
        given()
                .header("X-Api-Key", API_KEY_COMPANY_ADMIN)
                .header("X-Customer-Code", CUSTOMER_CODE)
                .contentType(ContentType.JSON)
                .body(new TransferRequest(List.of("WT6QU3QO7S"), INVALID_TEAM_ID))
                .when()
                .post(CHANGE_TEAM_ENDPOINT)
                .then()
                .statusCode(404)
                .body("description", equalTo(Integer.toString(INVALID_TEAM_ID)));
    }

    @BeforeAll
    static void setUp() {
        LicenseHelper licenseHelper = new LicenseHelper();
        licenseHelper.moveLicensesBack();
    }


}
