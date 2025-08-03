package tests;

import base.BaseTest;
import helpers.LicenseHelper;
import models.AssignLicenseRequest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AssignLicenseTests extends BaseTest {

    private AssignLicenseRequest createDefaultRequest(String licenseId) {
        LicenseHelper licenseHelper = new LicenseHelper();
        return licenseHelper.createRequest("adlzhnkv@gmail.com", "Anastasiia", "Ivanova",
                           true, "II", 1, licenseId, true);
    }

    @Test
    void testAssignLicense_Positive() {
        AssignLicenseRequest request = createDefaultRequest("RIQIET072L");

        given()
            .header("X-Api-Key", API_KEY_COMPANY_ADMIN)
            .header("X-Customer-Code", CUSTOMER_CODE)
                .header("Content-Type", "application/json")
            .body(request)
        //.when()
            .post(ASSIGN_LICENSE_ENDPOINT)
        .then()
            .statusCode(200);
    }

    @Test
    void testAssignLicense_InvalidRequest() {
        AssignLicenseRequest request = createDefaultRequest("47BBHIY5CH");

        given()
                .header("X-Api-Key", API_KEY_COMPANY_ADMIN)
                .header("X-Customer-Code", CUSTOMER_CODE)
                .header("Content-Type", "application/json")
                .body(request)
                .when()
                .post(ASSIGN_LICENSE_ENDPOINT)
                .then()
                .statusCode(400)
                .body("code", equalTo("LICENSE_IS_NOT_AVAILABLE_TO_ASSIGN"))
                .body("description", equalTo("ALLOCATED"));

    }

    @Test
    void testAssignLicense_InsufficientPermissions() {
        AssignLicenseRequest request = createDefaultRequest("2NDKEQZ1ZS");

        given()
                .header("X-Api-Key", API_KEY_VIEWER) // viewer token
                .header("X-Customer-Code", CUSTOMER_CODE)
                .header("Content-Type", "application/json")
                .body(request)
                .when()
                .post(ASSIGN_LICENSE_ENDPOINT)
                .then()
                .statusCode(403)
                .body("code", equalTo("INSUFFICIENT_PERMISSIONS"))
                .body("description", equalTo("Missing Edit permission on customer 6703615 or on team with id 2573297"));
    }
    @Test
    void testAssignLicense_LicenseFromOtherTeam() {
        AssignLicenseRequest request = createDefaultRequest("47BBHIY5CH");

        given()
                .header("X-Api-Key", API_KEY_COMPANY_ADMIN)
                .header("X-Customer-Code", CUSTOMER_CODE)
                .header("Content-Type", "application/json")
                .body(request)
                .when()
                .post(ASSIGN_LICENSE_ENDPOINT)
                .then()
                .statusCode(400)
                .body("code", equalTo("LICENSE_IS_NOT_AVAILABLE_TO_ASSIGN"))
                .body("description", equalTo("ALLOCATED"));

    }

}