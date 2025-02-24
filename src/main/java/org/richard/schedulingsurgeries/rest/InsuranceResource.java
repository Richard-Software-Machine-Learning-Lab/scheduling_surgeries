package org.richard.schedulingsurgeries.rest;

import org.richard.schedulingsurgeries.domain.Insurance;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/insurances")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class InsuranceResource {
    @POST
    @Path("{name}")
    public Response add(@PathParam("name") String name) {
        Insurance insurance = new Insurance(name);
        Insurance.persist(insurance);
        return Response.accepted(insurance).build();
    }

    @DELETE
    @Path("{insuranceId}")
    public Response delete(@PathParam("insuranceId") Long insuranceId) {
        Insurance insurance = Insurance.findById(insuranceId);
        if (insurance == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        insurance.delete();
        return Response.status(Response.Status.OK).build();
    }

    @GET
    public List<Insurance> getInsurances(){
        return Insurance.listAll();
    }
}
