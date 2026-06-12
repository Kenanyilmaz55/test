package com.tanrikulu.crm.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CrmActivityTest extends TestBase {

    @Test
    @DisplayName("GET /CRMActivityList - CRM Aktivite Listesini Getirme")
    public void testGetCrmActivityList() {
        String sessionCookie = properties.getProperty("session.cookie");
        String dbName = properties.getProperty("db.name");

        given()
            .cookie("connect.sid", sessionCookie)
            .queryParam("DBName", dbName)
        .when()
            .get("/CRMActivityList")
        .then()
            .log().all()
            .statusCode(200)
            .time(lessThan(5000L));
    }

    @Test
    @DisplayName("GET /ActivityTypeList - Aktivite Tiplerini Getirme")
    public void testGetActivityTypeList() {
        String sessionCookie = properties.getProperty("session.cookie");
        String dbName = properties.getProperty("db.name");

        given()
            .cookie("connect.sid", sessionCookie)
            .queryParam("DBName", dbName)
        .when()
            .get("/ActivityTypeList")
        .then()
            .log().all()
            .statusCode(200)
            .time(lessThan(5000L));
    }
}
