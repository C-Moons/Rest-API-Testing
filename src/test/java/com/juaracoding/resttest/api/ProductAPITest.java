package com.juaracoding.resttest.api;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class ProductAPITest {
    private RequestSpecification requestSpecification;
    private Response response;
    private ValidatableResponse validatableResponse;

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://fakestoreapi.com";
        requestSpecification = RestAssured.given();
    }

    @Test(enabled = false)
    public void myTestAPI() {
        response = requestSpecification.get("/products");

        validatableResponse = response.then();
        validatableResponse.statusCode(200);
        validatableResponse.statusLine("HTTP/1.1 200 OK");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");

    }

    @Test(priority = 1)
    public void findAllTest() {
        // Request
        validatableResponse = RestAssured.given()
                .when()
                .get("/products")
                .then();
        // Validation
        validatableResponse
                .statusCode(200).statusLine("HTTP/1.1 200 OK")
                .body("size()", Matchers.greaterThan(0))
                .body("id", Matchers.everyItem(Matchers.instanceOf(Integer.class)))
                .body("title", Matchers.everyItem(Matchers.instanceOf(String.class)))
                .body("price", Matchers.everyItem(Matchers.instanceOf(Number.class)))
                .body("description", Matchers.everyItem(Matchers.instanceOf(String.class)))
                .body("category", Matchers.everyItem(Matchers.instanceOf(String.class)))
                .body("image", Matchers.everyItem(Matchers.instanceOf(String.class)))
                .body("rating.rate", Matchers.everyItem(Matchers.instanceOf(Number.class)))
                .body("rating.count", Matchers.everyItem(Matchers.instanceOf(Integer.class)));
    }

    @Test(priority = 2)
    public void createTest() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "Oncom Tempe");
        payload.put("description", "Lorem ipsum...");
        payload.put("price", 29.99F);

        validatableResponse = RestAssured.given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/products")
                .then();

        validatableResponse.assertThat().statusCode(201)
                .body("id", Matchers.instanceOf(Integer.class))
                .body("title", Matchers.instanceOf(String.class))
                .body("price", Matchers.instanceOf(Number.class))
                .body("title", Matchers.equalTo("Oncom Tempe"))
                .body("price", Matchers.equalTo(29.99F));

    }

    // ==== UJIAN MINGGU 4 ====
    @Test(priority = 3)
    public void productDetailTest() {

        validatableResponse = RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/products/2")
                .then();

        validatableResponse.assertThat().statusCode(200)
                .body("id", Matchers.instanceOf(Integer.class))
                .body("title", Matchers.instanceOf(String.class))
                .body("price", Matchers.instanceOf(Number.class));
    }

    @Test(priority = 4)
    public void updateProductTest() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "updated Title");
        payload.put("description", "new description");
        payload.put("price", 100.99F);
        validatableResponse = RestAssured.given()
                .contentType("application/json")
                .body(payload)
                .when()
                .put("/products/7")
                .then();

        validatableResponse.assertThat().statusCode(200)
                .body("id", Matchers.instanceOf(Integer.class))
                .body("title", Matchers.equalTo("updated Title"))
                .body("price", Matchers.equalTo(100.99F))
                .body("description", Matchers.equalTo("new description"));
    }

    @Test(priority = 5)
    public void deleteProductTest(){
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "updated Title");
        payload.put("description", "new description");
        payload.put("price", 100.99F);
        validatableResponse = RestAssured.given()
                .contentType("application/json")
                .body(payload)
                .when()
                .delete("/products/6")
                .then();

        validatableResponse.assertThat().statusCode(200)
                .body("id", Matchers.instanceOf(Integer.class))
                .body("title", Matchers.instanceOf(String.class))
                .body("price", Matchers.instanceOf(Number.class))
                .body("description", Matchers.instanceOf(String.class));
                
    }
    }
