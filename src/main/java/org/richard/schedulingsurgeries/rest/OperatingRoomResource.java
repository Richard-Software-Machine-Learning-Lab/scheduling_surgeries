package org.richard.schedulingsurgeries.rest;

import org.richard.schedulingsurgeries.domain.OperatingRoom;
import org.richard.schedulingsurgeries.domain.Time;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class OperatingRoomResource {
    @POST
    @Path("{roomName}/{timeId}")
    public Response add(@PathParam("roomName") String roomName, @PathParam("timeId") Long timeId) {
        Time time = Time.findById(timeId);
        if (time == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        OperatingRoom operatingRoom = new OperatingRoom(roomName, time);
        OperatingRoom.persist(operatingRoom);
        return Response.accepted(operatingRoom).build();
    }


    @DELETE
    @Path("{roomId}")
    public Response delete(@PathParam("roomId") Long roomId) {
        OperatingRoom operatingRoom = OperatingRoom.findById(roomId);
        if (operatingRoom == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        operatingRoom.delete();
        return Response.status(Response.Status.OK).build();
    }

    @GET
    public List<OperatingRoom> getOperatingRooms(){
        return OperatingRoom.listAll();
    }
}
