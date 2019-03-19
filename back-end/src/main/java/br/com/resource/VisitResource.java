package br.com.resource;

import br.com.dto.ResponseApi;
import br.com.dto.VisitDTO;
import br.com.model.Employee;
import br.com.service.VisitService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/visit")
@Produces("application/json")
@Consumes("application/json")
public class VisitResource {

    @Inject
    VisitService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/process")
    public Response process(VisitDTO visitDTO) {
        Object result = service.processFiles(visitDTO);
        if (result == null){
            throw new WebApplicationException("Error processing files.", 404);
        }
        return ResponseApi.build(result,"TERMINOU!");
    }
}