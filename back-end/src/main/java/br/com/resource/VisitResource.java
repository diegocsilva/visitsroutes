package br.com.resource;

import br.com.dto.FilesVisitsDTO;
import br.com.service.VisitService;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/visit")
public class VisitResource {

    @Inject
    VisitService service;

    @POST
    @Path("/process")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public FilesVisitsDTO process(MultipartInput input)  {
        FilesVisitsDTO filesVisitsDTO = service.processFiles(input);
        if (filesVisitsDTO == null) {
            throw new WebApplicationException("Error processing files.", 404);
        }
        return filesVisitsDTO;
    }
}