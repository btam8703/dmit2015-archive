package dmit2015.assignment03.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import java.util.List;

/**
 This interface class is for the OscarReview entity
 @author Benjamin Tam
 @version 1.0
 @since 2020-03-11
 */

@RegisterRestClient//(baseUri = "http://localhost:8080/dmit2015-assignment03-server-bentam/webapi/")
@Path("reviews")
public interface OscarReviewService {

    @GET
    List<OscarReview> findAll();

    @GET
    @Path("{reviewId}")
    OscarReview findOneById(@PathParam("reviewId")Long id);

    @POST
    String create(OscarReview newOscarReview);

    @PUT
    @Path("{reviewId}")
    void update(@PathParam("reviewId") Long id, OscarReview updatedOscarReview);

    @DELETE
    @Path("{reviewId}")
    void delete(@PathParam("reviewId") Long id);
}
