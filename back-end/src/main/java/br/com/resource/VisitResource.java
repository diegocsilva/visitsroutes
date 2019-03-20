package br.com.resource;

import br.com.dto.ResponseApi;
import br.com.service.VisitService;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/visit")
public class VisitResource {

    @Inject
    VisitService service;

    @POST
    @Path("/process")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response process(MultipartInput input)  {
        Object result = null;
        result = service.processFiles(input);
        if (result == null) {
            throw new WebApplicationException("Error processing files.", 404);
        }
        return ResponseApi.build(result);
    }
}