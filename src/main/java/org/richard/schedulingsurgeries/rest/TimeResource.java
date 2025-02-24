package org.richard.schedulingsurgeries.rest;

import org.richard.schedulingsurgeries.domain.Time;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/time")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class TimeResource {

    @POST
    @Path("{timeId}/{openingTime}/{closingTime}")
    public Response add(@PathParam("timeId") Long timeId, @PathParam("openingTime") String openingTime,
                        @PathParam("closingTime") String closingTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime openingOperatingRoom = LocalDateTime.parse(openingTime, format);
        LocalDateTime closingOperationRoom = LocalDateTime.parse(closingTime, format);
        Time time = Time.findById(timeId);
        if (time == null) {
            time = new Time(openingOperatingRoom, closingOperationRoom);
        } else {
            time.setStartingTimeWeekly(openingOperatingRoom);
            time.setFinishTimeWeekly(closingOperationRoom);
        }

        Time.persist(time);
        return Response.accepted(time).build();
    }

    @DELETE
    @Path("{timeId}")
    public Response delete(@PathParam("timeId") Long timeId) {
        Time time = Time.findById(timeId);
        if (time == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        time.delete();
        return Response.status(Response.Status.OK).build();
    }

    @GET
    public List<Time> getTime(){
        return Time.listAll();
    }
}
