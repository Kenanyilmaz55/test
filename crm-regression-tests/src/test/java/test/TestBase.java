package com.tanrikulu.crm.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import java.io.InputStream;
import java.util.Properties;

public class TestBase {
    protected static Properties properties;

    @BeforeAll
    public static void setup() {
        properties = new Properties();
        try (InputStream input = TestBase.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            properties.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        RestAssured.baseURI = properties.getProperty("base.uri", "http://localhost");
        RestAssured.port = Integer.parseInt(properties.getProperty("base.port", "3000"));

        try {
            String dbName = properties.getProperty("db.name", "TEST_TK");
            String loginPayload = "{ \"username\": \"admin\", \"password\": \"admin\", \"databasename\": \"" + dbName + "\" }";
            
            Response response = RestAssured.given()
                .contentType(io.restassured.http.ContentType.JSON)
                .body(loginPayload)
                .post("/Login");

            String sid = response.getCookie("connect.sid");
            if (sid != null) {
                properties.setProperty("session.cookie", sid);
                System.out.println("Successfully logged in and retrieved session cookie.");
            } else {
                System.out.println("Login failed or connect.sid not found in response.");
            }
        } catch (Exception e) {
            System.out.println("Error during automated login: " + e.getMessage());
        }
    }
}
