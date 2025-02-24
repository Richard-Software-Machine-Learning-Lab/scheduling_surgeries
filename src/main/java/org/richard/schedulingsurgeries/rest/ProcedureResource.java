package org.richard.schedulingsurgeries.rest;

import org.richard.schedulingsurgeries.domain.Procedure;
import org.richard.schedulingsurgeries.domain.Speciality;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/procedures")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class ProcedureResource {
    @POST
    @Path("{name}/{specialityId}")
    public Response add(@PathParam("name") String name, @PathParam("specialityId") Long specialityId) {
        List<Speciality> specialities = Speciality.listAll();
        Speciality speciality = specialities.get(specialityId.intValue() - 1);
        Procedure procedure = new Procedure(name, speciality);
        Procedure.persist(procedure);
        return Response.accepted(procedure).build();
    }

    @DELETE
    @Path("{procedureId}")
    public Response delete(@PathParam("procedureId") Long procedureId) {
        Procedure procedure = Procedure.findById(procedureId);
        if (procedure == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        procedure.delete();
        return Response.status(Response.Status.OK).build();
    }

    @GET
    public List<Procedure> getProcedures(){
        return Procedure.listAll();
    }

    @GET
    @Path("{name}")
    public Long getAnesthesiaTypeId(@PathParam("name") String name){
        List<Procedure> procedures = Procedure.listAll();
        for (Procedure procedure: procedures) {
            if (procedure.getName().equals(name)) {
                return procedure.getProcedureId();
            }
        }
        return -1L;
    }

}
