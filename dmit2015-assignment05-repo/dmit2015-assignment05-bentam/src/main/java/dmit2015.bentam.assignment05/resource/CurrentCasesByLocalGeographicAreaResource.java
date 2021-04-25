package dmit2015.bentam.assignment05.resource;

import dmit2015.bentam.assignment05.entity.CurrentCasesByLocalGeographicArea;
import dmit2015.bentam.assignment05.repository.CurrentCasesByLocalGeographicAreaRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/**
 * curl -i -X GET http://localhost:8080/dmit2015-assignment05-bentam/webapi/CurrentCasesByLocalGeographicArea/
 *
 * curl -i -X GET http://localhost:8080/dmit2015-assignment05-bentam/webapi/CurrentCasesByLocalGeographicArea/contains/-113.503519/53.5678765
 *
 * curl -i -X GET http://localhost:8080/dmit2015-assignment05-bentam/webapi/CurrentCasesByLocalGeographicArea/contains/-113.6344048/53.4867745
 *
 */

/**
 This resource class handles current cases
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-08
 */

@RequestScoped
@Path("CurrentCasesByLocalGeographicArea")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CurrentCasesByLocalGeographicAreaResource {
    @Inject
    private CurrentCasesByLocalGeographicAreaRepository _repository;
    @GET
    public Response list() {

        return Response.ok(_repository.list()).build();
    }

    @GET
    @Path("/contains/{longitude}/{latitude}")
    public Response contains(@PathParam("longitude") double longitude,
                             @PathParam("latitude") double latitude) {
        Optional<CurrentCasesByLocalGeographicArea> optionalSingleResult =
                _repository.contains(longitude, latitude);
        if (optionalSingleResult.isEmpty()) {
            throw new NotFoundException();
        }
        CurrentCasesByLocalGeographicArea singleResult = optionalSingleResult.get();
        return Response.ok(singleResult).build();
    }
}
