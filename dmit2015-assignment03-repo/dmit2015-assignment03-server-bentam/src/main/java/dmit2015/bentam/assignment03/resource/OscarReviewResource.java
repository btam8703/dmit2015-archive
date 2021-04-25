/*
 * Read all reviews
 curl -i -X GET http://localhost:8080/dmit2015-assignment03-server-bentam/webapi/reviews
 *
 * Read a single review
 curl -i -X GET http://localhost:8080/dmit2015-assignment03-server-bentam/webapi/reviews/2
 *
 * Create new reviews
 curl -i -X POST http://localhost:8080/dmit2015-assignment03-server-bentam/webapi/reviews \
 -d '{"category":"movie","nominee":"The Shining","review":"Really scary movie","username":"ilovemovies1"}' \
 -H 'Content-Type: application/json'
 *
 curl -i -X POST http://localhost:8080/dmit2015-assignment03-server-bentam/webapi/reviews \
 -d '{"category":"actor","nominee":"Jack Nicholson","review":"great acting","username":"horrorguy1"}' \
 -H 'Content-Type: application/json'
 *
 curl -i -X POST http://localhost:8080/dmit2015-assignment03-server-bentam/webapi/reviews \
 -d '{"category":"film","nominee":"Godzilla","review":"good movie","username":"monsterlover"}' \
 -H 'Content-Type: application/json'
 *
 curl -i -X GET http://localhost:8080/dmit2015-assignment03-server-bentam/webapi/reviews/5
 curl -i -X GET http://localhost:8080/dmit2015-assignment03-server-bentam/webapi/reviews/6
 curl -i -X GET http://localhost:8080/dmit2015-assignment03-server-bentam/webapi/reviews/7
 *
 * Updating a review
 curl -i -X PUT http://localhost:8080/dmit2015-assignment02-benjamintam/webapi/reviews/5 \
 -d '{"category":"editing","id":5,"nominee":"2001","review":"good movie","username":"stanley"}' \
 -H 'Content-Type: application/json'
 *
 curl -i -X GET http://localhost:8080/dmit2015-assignment02-benjamintam/webapi/reviews/5
 *
 * Delete a review
 *
 *
 */

package dmit2015.bentam.assignment03.resource;

import dmit2015.bentam.assignment03.entity.OscarReview;
import dmit2015.bentam.assignment03.repository.OscarReviewRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("reviews")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

/**
 This class contains the various methods used for the JAXRS webservices
 @author Benjamin Tam
 @version 1.1
 @since 2020-02-28
 */

public class OscarReviewResource {

    @Inject
    private OscarReviewRepository _oscarReviewRepository;

    @GET
    public Response findAllReviews() {
        List<OscarReview> reviews = _oscarReviewRepository.findAll();
        return Response.ok(reviews).build();
    }

    @GET
    @Path("{id : \\d+}")
    public Response findOneById(@PathParam("id") Long reviewId) {
        if (reviewId == null) {
            throw new BadRequestException();
        }

        Optional<OscarReview> optionalReview = _oscarReviewRepository.findById(reviewId);
        if (optionalReview.isEmpty()) {
            throw new NotFoundException();
        }
        OscarReview existingReview = optionalReview.get();
        return Response.ok(existingReview).build();
    }

    @POST
    public Response createNewReview(@Valid OscarReview newReview, @Context UriInfo uriInfo) {
        if (newReview == null) {
            throw new BadRequestException();
        }

        _oscarReviewRepository.add(newReview);
        URI locationUri = uriInfo.getAbsolutePathBuilder().path(newReview.getId().toString()).build();
        return Response.created(locationUri).build();
    }

    @PUT
    @Path("{id : \\d+}")
    public Response updateReview(@PathParam("id") Long reviewId, @Valid OscarReview updatedReview) {
        if (reviewId == null || updatedReview.getId() == null || !updatedReview.getId().equals(reviewId)) {
            throw new BadRequestException();
        }

        Optional<OscarReview> optionalReview = _oscarReviewRepository.findById(reviewId);
        if (optionalReview.isEmpty()) {
            throw new NotFoundException();
        }

        OscarReview existingReview = optionalReview.get();
        existingReview.setCategory(updatedReview.getCategory());
        existingReview.setNominee(updatedReview.getNominee());
        existingReview.setReview(updatedReview.getReview());
        existingReview.setUsername(updatedReview.getUsername());
        _oscarReviewRepository.update(existingReview);

        return Response.noContent().build();
    }

    @DELETE
    @Path("{id : \\d+}")
    public Response deleteReview(@PathParam("id") Long reviewId) {
        if (reviewId == null) {
            throw new BadRequestException();
        }
        Optional<OscarReview> existingReview = _oscarReviewRepository.findById(reviewId);
        if (existingReview.isEmpty()) {
            throw new NotFoundException();
        }

        _oscarReviewRepository.remove(reviewId);
        return Response.noContent().build();
    }

}
