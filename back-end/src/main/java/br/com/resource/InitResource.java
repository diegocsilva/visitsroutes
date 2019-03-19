package br.com.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/init")
public class InitResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get")
    public String hello() {
        return "hello";
    }
}