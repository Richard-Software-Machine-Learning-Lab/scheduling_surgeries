package org.richard.schedulingsurgeries.rest;

import org.richard.schedulingsurgeries.domain.AnesthesiaType;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/anesthesiatypes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class AnesthesiaTypeResource {

    @POST
    @Path("{code}")
    public Response add(@PathParam("code") Integer code) {
        AnesthesiaType anesthesiaType = new AnesthesiaType(code);
        AnesthesiaType.persist(anesthesiaType);
        return Response.accepted(anesthesiaType).build();
    }

    @DELETE
    @Path("{anesthesiaTypeId}")
    public Response delete(@PathParam("anesthesiaTypeId") Long anesthesiaTypeId) {
        AnesthesiaType anesthesiaType = AnesthesiaType.findById(anesthesiaTypeId);
        if (anesthesiaType == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        anesthesiaType.delete();
        return Response.status(Response.Status.OK).build();
    }

    @GET
    public List<AnesthesiaType> getAnesthesiaTypes(){
        return AnesthesiaType.listAll();
    }

    @GET
    @Path("{code}")
    public Long getAnesthesiaTypeId(@PathParam("code") Integer code){
        List<AnesthesiaType> anesthesiaTypes = AnesthesiaType.listAll();
        for (AnesthesiaType anesthesiaType: anesthesiaTypes) {
            if(anesthesiaType.getCode() == code){
                return anesthesiaType.getAnesthesiaId();
            }
        }
        return -1L;
    }


}
