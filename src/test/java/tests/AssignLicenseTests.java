package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class AssignLicenseTests extends BaseTest {
    public static final String ASSIGN_LICENSE_ENDPOINT = "/customer/licenses/assign";

    @Test
    void testAssignLicense() {
        given()
                .header("X-Api-Key", API_KEY_COMPANY_ADMIN)
                .header("X-Customer-Code", CUSTOMER_CODE)
                .contentType(ContentType.JSON)
                .body("""
                            {
                              "contact": {
                                "email": "adlzhnkv@gmail.com",
                                "firstName": "Anastasiia",
                                "lastName": "Ivanova"
                              },
                              "includeOfflineActivationCode": true,
                              "license": {
                                "productCode": "II",
                                "team": 1
                              },
                              "licenseId": "XR1BPSMLST",
                              "sendEmail": true
                            }
                        """)
                //.when()
                .post(ASSIGN_LICENSE_ENDPOINT)
                .then()
                .statusCode(200);
    }

    @Test
    void testAssignLicense_LicenseNotAvailable() {

        given()
                .header("X-Api-Key", "cm0ipchn70a83kqr5n3hs71w5")
                .header("X-Customer-Code", CUSTOMER_CODE)
                .header("Content-Type", "application/json")
                .body("""
                            {
                              "contact": {
                                "email": "adlzhnkv@gmail.com",
                                "firstName": "Anastasiia",
                                "lastName": "Ivanova"
                              },
                              "includeOfflineActivationCode": true,
                              "license": {
                                "productCode": "II",
                                "team": 1
                              },
                              "licenseId": "RIQIET072L",
                              "sendEmail": true
                            }
                        """)
                .when()
                .post(ASSIGN_LICENSE_ENDPOINT)
                .then()
                .statusCode(400)
                .body("code", equalTo("LICENSE_IS_NOT_AVAILABLE_TO_ASSIGN"))
                .body("description", equalTo("ALLOCATED"));

    }

    @Test
    void testAssignLicense_InvalidCustomerCode() {

        given()
                .header("X-Api-Key", "9ob1wyrrh28v3zoqe0qx14naf")
                .header("X-Customer-Code", 123456)
                .header("Content-Type", "application/json")
                .body("""
                            {
                              "contact": {
                                "email": "adlzhnkv@gmail.com",
                                "firstName": "Anastasiia",
                                "lastName": "Ivanova"
                              },
                              "includeOfflineActivationCode": true,
                              "license": {
                                "productCode": "II",
                                "team": 1
                              },
                              "licenseId": "RIQIET072L",
                              "sendEmail": true
                            }
                        """)
                .when()
                .post(ASSIGN_LICENSE_ENDPOINT)
                .then()
                .statusCode(401)
                .body("code", equalTo("INVALID_TOKEN"))
                .body("description", equalTo("The token provided is invalid"));

    }

    @Test
    void testAssignLicense_InsufficientPermissions() {

        given()
                .header("X-Api-Key", "czmsw4dfcqh0lyqu6yd7fzg3o") // viewer token
                .header("X-Customer-Code", CUSTOMER_CODE)
                .header("Content-Type", "application/json")
                .body("""
                            {
                              "contact": {
                                "email": "adlzhnkv@gmail.com",
                                "firstName": "Anastasiia",
                                "lastName": "Ivanova"
                              },
                              "includeOfflineActivationCode": true,
                              "license": {
                                "productCode": "II",
                                "team": 1
                              },
                              "licenseId": "2NDKEQZ1ZS",
                              "sendEmail": true
                            }
                        """)
                .when()
                .post(ASSIGN_LICENSE_ENDPOINT)
                .then()
                .statusCode(403)
                .body("code", equalTo("INSUFFICIENT_PERMISSIONS"))
                .body("description", equalTo("Missing Edit permission on customer 6703615 or on team with id 2573297"));
    }


}