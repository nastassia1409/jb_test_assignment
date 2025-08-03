package helpers;

import io.restassured.http.ContentType;
import models.AssignLicenseRequest;
import models.TransferRequest;
import java.util.List;
import static base.BaseTest.*;
import static io.restassured.RestAssured.given;
import static base.BaseTest.TEAM_1_ID;

public class LicenseHelper {
    public void moveLicensesBack() {
        given()
                .header("X-Api-Key", API_KEY_COMPANY_ADMIN)
                .header("X-Customer-Code", CUSTOMER_CODE)
                .contentType(ContentType.JSON)
                .body(new TransferRequest(List.of("U886DF2U2D", "JKFK70A7F0", "07WB7RP9UB", "PZ5RBM8QZA"), TEAM_1_ID))
                .when()
                .post(CHANGE_TEAM_ENDPOINT)
                .then()
                .statusCode(200);
    }

    public AssignLicenseRequest createRequest(String email, String firstName, String lastName,
                                               boolean includeOfflineActivationCode,
                                               String productCode, int team,
                                               String licenseId, boolean sendEmail) {
        return new AssignLicenseRequest(
                new AssignLicenseRequest.Contact(email, firstName, lastName),
                includeOfflineActivationCode,
                new AssignLicenseRequest.License(productCode, team),
                licenseId,
                sendEmail
        );
    }
}
