package org.richard.schedulingsurgeries.rest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SurgeryTypeResourceTest {

    @Test
    public void testGetSurgeryTypes(){
               given()
               .when().get("/surgerytypes")
               .then()
               .statusCode(Response.Status.OK.getStatusCode());
    }


    @Test
    public void testAddAndDeleteSurgeryType(){
        JsonObject jsonObject = Json.createObjectBuilder()
                                .add("surgeryTypeId", 2)
                                .add("code", "A")
                                 .add("name", "Ambulatoria")
                                 .build();

                 given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .when()
                .post("/surgerytypes/A/Ambulatoria")
                .then()
                .statusCode(Response.Status.ACCEPTED.getStatusCode());

                 given()
                 .when()
                 .delete("/surgerytypes/2")
                 .then()
                 .statusCode(Response.Status.OK.getStatusCode());

                 given()
                .when()
                .delete("/surgerytypes/56")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }
}
