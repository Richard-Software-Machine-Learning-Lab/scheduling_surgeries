package org.richard.schedulingsurgeries.rest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AnesthetistResourceTest {

    @Test
    public void testGetAnesthetist(){
               given()
               .when().get("/anesthetists")
               .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }


    @Test
    public void testAddAndDeleteAnesthetist(){
        JsonObject jsonObject = Json.createObjectBuilder()
                                .add("anesthetistId", 2345)
                                .add("firstName", "NA")
                                 .add("lastName", "NA")
                                 .build();

                 given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .when()
                .post("/anesthetists/2345/NA/NA")
                .then()
                .statusCode(Response.Status.ACCEPTED.getStatusCode());

                 given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .when()
                .post("/anesthetists/2345/NA/NA")
                .then()
                .statusCode(Response.Status.NOT_ACCEPTABLE.getStatusCode());
    }

}
