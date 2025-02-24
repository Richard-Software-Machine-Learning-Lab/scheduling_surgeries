package org.richard.schedulingsurgeries.rest;

import org.richard.schedulingsurgeries.domain.Insurance;
import org.richard.schedulingsurgeries.domain.Patient;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class PatientResource {
    @POST
    @Path("{patientId}/{firstName}/{lastName}/{insuranceId}")
    public Response add(@PathParam("patientId") Long patientId, @PathParam("firstName") String firstName,
                        @PathParam("lastName") String lastName, @PathParam("insuranceId") Long insuranceId) {
        if (Patient.findById(patientId) != null) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        Insurance insurance = Insurance.findById(insuranceId);
        Patient patient = new Patient(patientId, firstName, lastName, insurance);
        Patient.persist(patient);
        return Response.accepted(patient).build();
    }

    @DELETE
    @Path("{patientId}")
    public Response delete(@PathParam("patientId") Long patientId) {
        Patient patient = Patient.findById(patientId);
        if (patient == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        patient.delete();
        return Response.status(Response.Status.OK).build();
    }

    @GET
    public List<Patient> getPatients(){
        return Patient.listAll();
    }

    @GET
    @Path("{patientId}")
    public Response isPatientCreated(@PathParam("patientId") Long patientId){
        Patient patient = Patient.findById(patientId);
        if (patient == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FOUND).build();
    }

}
