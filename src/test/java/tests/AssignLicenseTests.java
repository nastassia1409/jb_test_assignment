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
       String jsonBody = """
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
        """;

        Response response = given()
                .header("X-Api-Key", "cm0ipchn70a83kqr5n3hs71w5")
                .header("X-Customer-Code", CUSTOMER_CODE)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .post(ASSIGN_LICENSE_ENDPOINT);

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.getBody().asPrettyString());

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
        //.when()
            .post("/customer/licenses/assign")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("contact.email", equalTo("adlzhnkv@gmail.com"))
            .body("contact.firstName", notNullValue())
            .body("contact.lastName", notNullValue())
            .body("includeOfflineActivationCode", equalTo(true))
            .body("license.productCode", equalTo("II"))
            .body("license.team", instanceOf(Integer.class))
            .body("licenseId", equalTo("ABC1234567"))
            .body("sendEmail", equalTo(true));
    }

    @Test
    void testAssignLicense_InvalidRequest() {

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
                .post("/customer/licenses/assign")
                .then()
                .statusCode(400)
                .body("code", equalTo("LICENSE_IS_NOT_AVAILABLE_TO_ASSIGN"))
                .body("description", equalTo("ALLOCATED"));

    }

    @Test
    void testAssignLicense_NotAllowed() {

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
                .post("/customer/licenses/assign")
                .then()
                .statusCode(403)
                .body("code", equalTo("INSUFFICIENT_PERMISSIONS"))
                .body("description", equalTo("Missing Edit permission on customer 6703615 or on team with id 2573297"));
    }

    @Test
    void testAssignLicense_SchemaValidation() {
        given()
            .header("Authorization", "Bearer cm0ipchn70a83kqr5n3hs71w5")
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
                      "licenseId": "RIQIET072L",
                      "sendEmail": true
                    }
                """)
        .when()
            .post("/customer/licenses/assign")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/assign-license-schema.json"));
    }
}
