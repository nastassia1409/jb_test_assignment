package tests;

import base.BaseTest;
import helpers.LicenseHelper;
import models.AssignLicenseRequest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AssignLicenseTests extends BaseTest {
    public static final String ASSIGN_LICENSE_ENDPOINT = "/customer/licenses/assign";


    private AssignLicenseRequest createDefaultRequest(String licenseId) {
        LicenseHelper licenseHelper = new LicenseHelper();
        return licenseHelper.createRequest("adlzhnkv@gmail.com", "Anastasiia", "Ivanova",
                           true, "II", 1, licenseId, true);
    }

    @Test
    void testAssignLicense_PositiveCase() {
        /*AssignLicenseRequest request = createDefaultRequest("XR1BPSMLST");

        Response response = given()
                .header("X-Api-Key", "cm0ipchn70a83kqr5n3hs71w5")
                .header("X-Customer-Code", CUSTOMER_CODE)
                .header("Content-Type", "application/json")
                .body(request)
                .post(ASSIGN_LICENSE_ENDPOINT);

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.getBody().asPrettyString());*/

        AssignLicenseRequest request2 = createDefaultRequest("RIQIET072L");

        given()
            .header("X-Api-Key", "cm0ipchn70a83kqr5n3hs71w5")
            .header("X-Customer-Code", CUSTOMER_CODE)
                .header("Content-Type", "application/json")
            .body(request2)
        //.when()
            .post(ASSIGN_LICENSE_ENDPOINT)
        .then()
            .statusCode(200);

    }

    @Test
    void testAssignLicense_NotAvailableToAssign() {
        AssignLicenseRequest request = createDefaultRequest("RIQIET072L");

        given()
                .header("X-Api-Key", "cm0ipchn70a83kqr5n3hs71w5")
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
    void testAssignLicense_LicenseFromOtherTeam() {
        AssignLicenseRequest request = createDefaultRequest("47BBHIY5CH");

        given()
                .header("X-Api-Key", "cm0ipchn70a83kqr5n3hs71w5")
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
    void testAssignLicense_NotAllowed() {
        AssignLicenseRequest request = createDefaultRequest("2NDKEQZ1ZS");

        given()
                .header("X-Api-Key", "czmsw4dfcqh0lyqu6yd7fzg3o") // viewer token
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


}