package org.richard.schedulingsurgeries.rest;

import org.richard.schedulingsurgeries.domain.*;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/surgeries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class SurgeryResource {

    @POST
    @Path("{patientId}/{surgeonId}/{anesthesiaId}/{anesthetistId}/{surgeryTypeId}/{procedureId}/{duration}")
    public Response add(@PathParam("patientId") Long patientId, @PathParam("surgeonId") Long surgeonId,
                        @PathParam("anesthesiaId") Long anesthesiaId,@PathParam("anesthetistId") Long anesthetistId,
                        @PathParam("surgeryTypeId") Long surgeryTypeId, @PathParam("procedureId") Long procedureId,
                        @PathParam("duration") Integer duration) {

        Patient patient = Patient.findById(patientId);
        Surgeon surgeon = Surgeon.findById(surgeonId);
        AnesthesiaType anesthesiaType = AnesthesiaType.findById(anesthesiaId);
        Anesthetist anesthetist = Anesthetist.findById(anesthetistId);
        SurgeryType surgeryType = SurgeryType.findById(surgeryTypeId);
        Procedure procedure = Procedure.findById(procedureId);
        Integer surgeryDuration = duration;
        Surgery surgery = new Surgery(patient, surgeon, anesthesiaType, anesthetist, surgeryType, procedure, surgeryDuration);
        Surgery.persist(surgery);
        return Response.accepted(surgery).build();
    }

    @DELETE
    @Path("{surgeryId}")
    public Response delete(@PathParam("surgeryId") Long surgeryId) {
        Surgery surgery = Surgery.findById(surgeryId);
        if (surgery == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        surgery.delete();
        return Response.status(Response.Status.OK).build();
    }

    @GET
    public List<Surgery> getSurgeries(){
        return Surgery.listAll();
    }
}
