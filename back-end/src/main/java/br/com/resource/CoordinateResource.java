package br.com.resource;

import br.com.model.Coordinate;
import br.com.service.CoordinateService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/coordinate")
@Produces("application/json")
@Consumes("application/json")
public class CoordinateResource {

    @Inject
    CoordinateService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/distance")
    public Response distance(Map<String, Coordinate> coordinates) {
        Double result = service.distanceCoordinate(coordinates.get("coordinateA")
                , coordinates.get("coordinateB"));
        if (result!=null){
            return Response.ok(result).build();
        }
        return Response.status(Response.Status.
                INTERNAL_SERVER_ERROR.getStatusCode(), "Ocorreu um erro!").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/model")
    public Map<String, Coordinate> model() {
        Map<String, Coordinate> coordinateMap = new HashMap<>();
        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(-27.6019111);
        coordinate.setLongitude(-48.5957299);

        coordinateMap.put("coordinateA", coordinate);
        coordinateMap.put("coordinateB", coordinate);
        return coordinateMap;
    }

}