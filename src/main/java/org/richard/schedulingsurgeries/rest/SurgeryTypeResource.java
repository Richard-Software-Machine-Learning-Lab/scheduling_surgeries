package org.richard.schedulingsurgeries.rest;

import org.richard.schedulingsurgeries.domain.SurgeryType;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/surgerytypes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class SurgeryTypeResource {

    @POST
    @Path("{code}/{name}")
    public Response add(@PathParam("code") String code, @PathParam("name") String name) {
        SurgeryType surgeryType = new SurgeryType(code, name);
        SurgeryType.persist(surgeryType);
        return Response.accepted(surgeryType).build();
    }

    @DELETE
    @Path("{surgeryTypeId}")
    public Response delete(@PathParam("surgeryTypeId") Long surgeryTypeId) {
        SurgeryType surgeryType = SurgeryType.findById(surgeryTypeId);
        if (surgeryType == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        surgeryType.delete();
        return Response.status(Response.Status.OK).build();
    }

    @GET
    public List<SurgeryType> getSurgeryTypes(){
        return SurgeryType.listAll();
    }

    @GET
    @Path("{code}")
    public Long obtainSurgeryTypeIdentification(@PathParam("code") String code){
        List<SurgeryType> surgeryTypes = SurgeryType.listAll();
        for (SurgeryType surgeryType: surgeryTypes) {
              if (surgeryType.getCode().equals(code)) {
                   return surgeryType.getSurgeryTypeId();
              }
        }
        return -1L;
    }
}
