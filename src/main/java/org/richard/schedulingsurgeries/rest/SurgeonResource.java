package org.richard.schedulingsurgeries.rest;

import org.richard.schedulingsurgeries.domain.Speciality;
import org.richard.schedulingsurgeries.domain.Surgeon;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/surgeons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class SurgeonResource {

    @POST
    @Path("{surgeonId}/{firstName}/{lastName}/{specialityId}")
    public Response add(@PathParam("surgeonId") Long surgeonId, @PathParam("firstName") String firstName,
                        @PathParam("lastName") String lastName, @PathParam("specialityId") Long specialityId) {
        List<Speciality> specialities = Speciality.listAll();
        Speciality speciality = specialities.get(specialityId.intValue() - 1);
        Surgeon surgeon = new Surgeon(surgeonId, firstName, lastName, speciality);
        Surgeon.persist(surgeon);
        return Response.accepted(surgeon).build();
    }

    @DELETE
    @Path("{surgeonId}")
    public Response delete(@PathParam("surgeonId") Long surgeonId) {
        Surgeon surgeon = Surgeon.findById(surgeonId);
        if (surgeon == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        surgeon.delete();
        return Response.status(Response.Status.OK).build();
    }

    @GET
    public List<Surgeon> getSurgeons(){
        return Surgeon.listAll();
    }

    @GET
    @Path("{surgeonId}")
    public String getSpeciality(@PathParam("surgeonId") Long surgeonId){
        List<Surgeon> surgeons = Surgeon.listAll();
        for (Surgeon eachSurgeon: surgeons) {
            if (eachSurgeon.getSurgeonId().equals(surgeonId)) {
                return eachSurgeon.getSpeciality().getName();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build().toString();
    }


}
