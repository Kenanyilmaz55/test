package com.tanrikulu.crm.test;

import com.tanrikulu.crm.model.StateRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CrmApiRegressionTest extends TestBase {

    @Test
    @DisplayName("GET /BranchList - Şube Listesi Getirme ve Response Time Kontrolü")
    public void testGetBranchList() {
        String sessionCookie = properties.getProperty("session.cookie");
        String dbName = properties.getProperty("db.name");

        given()
            .cookie("connect.sid", sessionCookie)
            .queryParam("DBName", dbName)
        .when()
            .get("/BranchList")
        .then()
            .log().all()
            .statusCode(200)
            .time(lessThan(5000L)) // Zaman kontrolü
            .body("message_set[0].message_type", equalTo("S"))
            .body("message_set[0].message", containsString("Şubeler"))
            .body("data_set.size()", greaterThan(0))
            .body("data_set[0]", hasKey("key_col"))
            .body("data_set[0]", hasKey("text_col"));
    }

    @Test
    @DisplayName("POST /AddNewState - Yeni İl Ekleme (Request Body İçerir)")
    public void testAddNewState() {
        String sessionCookie = properties.getProperty("session.cookie");
        
        // SAP B1 State Code max 3 characters. Generates a random 3-digit number.
        String uniqueCode = String.valueOf((int)(Math.random() * 900) + 100); 
        String stateName = "Test " + uniqueCode;

        StateRequest requestBody = new StateRequest("TR", uniqueCode, stateName);

        given()
            .cookie("connect.sid", sessionCookie)
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/AddNewState")
        .then()
            .log().all()
            .statusCode(200)
            .time(lessThan(5000L))
            .body("message_set[0].message_type", equalTo("S"))
            .body("message_set[0].message", containsString("Yeni il başarıyla oluşturuldu"))
            .body("data_set.Code", equalTo(uniqueCode))
            .body("data_set.Name", equalTo(stateName));
    }
}
