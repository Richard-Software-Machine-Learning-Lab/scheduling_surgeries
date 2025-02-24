package org.richard.schedulingsurgeries.rest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static io.restassured.RestAssured.given;


@QuarkusTest
public class PatientResourceTest {

    @Test
    public void testGetPatients(){
               given()
               .when().get("/patients")
               .then()
               .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    public void testPatient(){
        JsonObject jsonObject = Json.createObjectBuilder()
                                .add("patientId", 999)
                                .add("firstName", "NA")
                                 .add("lastName", "NA")
                                 .build();

                 given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .when()
                .post("/patients/999/NA/NA/1")
                .then()
                .statusCode(Response.Status.ACCEPTED.getStatusCode());

                 given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .when()
                .post("/patients/999/NA/NA/1")
                .then()
                .statusCode(Response.Status.NOT_ACCEPTABLE.getStatusCode());

                 given()
                 .when().get("/patients/999")
                 .then()
                 .statusCode(Response.Status.FOUND.getStatusCode());

                 given()
                .when().get("/patients/876")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());

    }

}
