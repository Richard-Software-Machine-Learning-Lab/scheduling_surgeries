package org.richard.schedulingsurgeries.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AnesthesiaTypeResourceTest {

    @Test
    public void testGetAnesthesiaTypes(){
               given()
               .when().get("/anesthesiatypes")
               .then()
               .statusCode(Response.Status.OK.getStatusCode());
    }


    @Test
    public void testAddAndDeleteAnesthesiaType() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("code", 20)
                .build();

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .when()
                .post("/anesthesiatypes/20")
                .then()
                .statusCode(Response.Status.ACCEPTED.getStatusCode());

    }
}
