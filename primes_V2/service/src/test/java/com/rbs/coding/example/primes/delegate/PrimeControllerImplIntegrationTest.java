package com.rbs.coding.example.primes.delegate;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.XML;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import com.rbs.coding.examples.primes.PrimesApplication;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = PrimesApplication.class)
public class PrimeControllerImplIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void initialiseRestAssuredMockMvcWebApplicationContext() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    public void givenValidTargetWhenGetPrimesThenRespondWithClient() {
        String expectedjson = "{\"Initial\":10,\"Primes\":[2,3,5,7]}";
        given()
                .when()
                .get("/primes/" + 10)
                .then()
                .log().ifValidationFails()
                .statusCode(OK.value())
                .contentType(JSON)
                .assertThat().body(containsString(expectedjson));
    }

    @Test
    public void givenValidTargetWhenGetPrimesWithXMLThenRespondWithClient() {
        String expectedjson = new StringBuilder()
                .append("<PrimesResponse>")
                .append("<Initial>10</Initial>")
                .append("<Primes>")
                .append("<Primes>2</Primes>")
                .append("<Primes>3</Primes>")
                .append("<Primes>5</Primes>")
                .append("<Primes>7</Primes><")
                .append("/Primes>")
                .append("</PrimesResponse>")
                .toString();

        given()
                .contentType(XML)
                .accept(XML)
                .when()
                .get("/primes/" + 10)
                .then()
                .log().ifValidationFails()
                .statusCode(OK.value())
                .contentType(XML)
                .assertThat().body(containsString(expectedjson));
    }

    @Test
    public void givenValidTargetWhenGetPrimesAndThenRespondWithClient() {
        String expectedjson = "{\"Initial\":10,\"Primes\":[2,3,5,7]}";
        given()
                .when()
                .get("/primes/" + 10)
                .then()
                .log().ifValidationFails()
                .statusCode(OK.value())
                .contentType(JSON)
                .assertThat().body(containsString(expectedjson));
    }

    @Test
    public void givenAndValidTargetWhenGetPrimesThenRespondWithClient() {
        String expectedjson = "{\"Initial\":10,\"Primes\":[2,3,5,7]}";
        given()
                .when()
                .get("/primes/" + 10)
                .then()
                .log().ifValidationFails()
                .statusCode(OK.value())
                .contentType(JSON)
                .assertThat().body(containsString(expectedjson));
    }

    @Test
    public void givenValidLimitBelowMinimumWhenGetPrimesThenReturnsBadRequest() {
        String expectedjson = "{" +
                "\"id\":\"400\"," +
                "\"code\":\"API_CLIENT_ERROR\"," +
                "\"message\":\"getPrimesResponseByLimit.limit: must be greater than or equal to 2\"}";
        given()
                .when()
                .get("/primes/" + 1)
                .then()
                .log().ifValidationFails()
                .statusCode(BAD_REQUEST.value())
                .contentType(JSON)
                .assertThat().body(containsString(expectedjson));
    }

    @Test
    public void givenValidLimitAboveMaximumWhenGetPrimesThenReturnsBadRequest() {
        String expectedjson = "{" +
                "\"id\":\"400\"," +
                "\"code\":\"API_CLIENT_ERROR\"," +
                "\"message\":\"getPrimesResponseByLimit.limit: must be less than or equal to 1000000\"}";
        given()
                .when()
                .get("/primes/" + 10000000000L)
                .then()
                .log().ifValidationFails()
                .statusCode(BAD_REQUEST.value())
                .contentType(JSON)
                .assertThat().body(containsString(expectedjson));
    }
}
