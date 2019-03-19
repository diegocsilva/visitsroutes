package br.com.resource;

import br.com.model.Coordinate;
import br.com.model.Employee;
import br.com.service.EmployeeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employee")
@Produces("application/json")
@Consumes("application/json")
public class EmployeeResource {

    @Inject
    EmployeeService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{name}")
    public Employee findByName(@PathParam("name") String name) {
        return service.findByName(name);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/save")
    public Response save(Employee employee) {
        service.save(employee);
        Employee result = service.findByName(employee.getName());
        if (result != null){
            return Response.ok(result).build();
        }
        service.findAll();
        return Response.status(Response.Status.NO_CONTENT.getStatusCode(), "Sem resultados").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/model")
    public Employee model() {
        Employee employee = new Employee();
        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(-27.6019111);
        coordinate.setLongitude(-48.5957299);
        employee.setCoordinate(coordinate);
        employee.setName("diego");
        return employee;
    }
}