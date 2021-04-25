/*USER:
curl -k -i -X POST https://localhost:8443/dmit2015-project-rest-services-benjamintam/webapi/jwt/jsonLogin \
    -d '{"username":"usermovie","password":"Password2015"}' \
    -H 'Content-Type:application/json'

curl -i -X GET http://localhost:8080/dmit2015-project-rest-services-benjamintam/webapi/reviews

curl -i -X GET http://localhost:8080/dmit2015-project-rest-services-benjamintam/webapi/reviews/username \
    -H 'Authorization: Bearer eyJraWQiOiJxdWlja3N0YXJ0LWp3dC1pc3N1ZXIiLCJ0eXAiOiJqd3QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ1c2VybW92aWUiLCJ1cG4iOiJ1c2VybW92aWUiLCJpc3MiOiJxdWlja3N0YXJ0LWp3dC1pc3N1ZXIiLCJhdWQiOiJqd3QtYXVkaWVuY2UiLCJncm91cHMiOlsiVVNFUiJdLCJqdGkiOiI1Y2M1ZTI2Yi04ODUzLTRmNDQtYTgyMS03ZmQ2ZDQwMzc1NGQiLCJpYXQiOjE2MTkxMDM4MjQsImV4cCI6MTYxOTExODIyNH0.c1vLSexTGNPLIfHNPl4jGwGYYpDh5q1ONIbTW44JDzI_d30_xEEWk7nTQL3s4tFe3jMfpa4VCyrnAr466lBtgSbsGrVfX9e0lGpc3dzGroFrWBvFtcBBOyIDwP7ijF-p6ImscjiDFHPcVZkxlfMNhiduuHPxGJiJOGEyW236FTxVZpLEWipTisgvSQ2fxbf4R-FtE5nEE2nW1geIrI65G0smbaSHnNWrcdXBl1z793gKU1IG9iu1bxFtSJcDyaRa2HDCE9CJQlqL69CfzCNUwqQadfLG5wVXgKQcFcWKmff3ZVLQgxohcNogx_cT6-tIXIDAGXs6ySc73hvNJA52NA'

curl -i -X POST http://localhost:8080/dmit2015-project-rest-services-benjamintam/webapi/reviews \
    -d '{"category":"movie","nominee":"The Shining","review":"Really scary movie"}' \
    -H 'Authorization: Bearer eyJraWQiOiJxdWlja3N0YXJ0LWp3dC1pc3N1ZXIiLCJ0eXAiOiJqd3QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ1c2VybW92aWUiLCJ1cG4iOiJ1c2VybW92aWUiLCJpc3MiOiJxdWlja3N0YXJ0LWp3dC1pc3N1ZXIiLCJhdWQiOiJqd3QtYXVkaWVuY2UiLCJncm91cHMiOlsiVVNFUiJdLCJqdGkiOiI1Y2M1ZTI2Yi04ODUzLTRmNDQtYTgyMS03ZmQ2ZDQwMzc1NGQiLCJpYXQiOjE2MTkxMDM4MjQsImV4cCI6MTYxOTExODIyNH0.c1vLSexTGNPLIfHNPl4jGwGYYpDh5q1ONIbTW44JDzI_d30_xEEWk7nTQL3s4tFe3jMfpa4VCyrnAr466lBtgSbsGrVfX9e0lGpc3dzGroFrWBvFtcBBOyIDwP7ijF-p6ImscjiDFHPcVZkxlfMNhiduuHPxGJiJOGEyW236FTxVZpLEWipTisgvSQ2fxbf4R-FtE5nEE2nW1geIrI65G0smbaSHnNWrcdXBl1z793gKU1IG9iu1bxFtSJcDyaRa2HDCE9CJQlqL69CfzCNUwqQadfLG5wVXgKQcFcWKmff3ZVLQgxohcNogx_cT6-tIXIDAGXs6ySc73hvNJA52NA' \
    -H 'Content-Type: application/json'

curl -i -X GET http://localhost:8080/dmit2015-project-rest-services-benjamintam/webapi/reviews/5

curl -i -X PUT http://localhost:8080/dmit2015-project-rest-services-benjamintam/webapi/reviews/5 \
    -d '{"category":"editing","id":5,"nominee":"2001","review":"good movie"}' \
    -H 'Authorization: Bearer eyJraWQiOiJxdWlja3N0YXJ0LWp3dC1pc3N1ZXIiLCJ0eXAiOiJqd3QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ1c2VybW92aWUiLCJ1cG4iOiJ1c2VybW92aWUiLCJpc3MiOiJxdWlja3N0YXJ0LWp3dC1pc3N1ZXIiLCJhdWQiOiJqd3QtYXVkaWVuY2UiLCJncm91cHMiOlsiVVNFUiJdLCJqdGkiOiI1Y2M1ZTI2Yi04ODUzLTRmNDQtYTgyMS03ZmQ2ZDQwMzc1NGQiLCJpYXQiOjE2MTkxMDM4MjQsImV4cCI6MTYxOTExODIyNH0.c1vLSexTGNPLIfHNPl4jGwGYYpDh5q1ONIbTW44JDzI_d30_xEEWk7nTQL3s4tFe3jMfpa4VCyrnAr466lBtgSbsGrVfX9e0lGpc3dzGroFrWBvFtcBBOyIDwP7ijF-p6ImscjiDFHPcVZkxlfMNhiduuHPxGJiJOGEyW236FTxVZpLEWipTisgvSQ2fxbf4R-FtE5nEE2nW1geIrI65G0smbaSHnNWrcdXBl1z793gKU1IG9iu1bxFtSJcDyaRa2HDCE9CJQlqL69CfzCNUwqQadfLG5wVXgKQcFcWKmff3ZVLQgxohcNogx_cT6-tIXIDAGXs6ySc73hvNJA52NA' \
    -H 'Content-Type: application/json'

curl -i -X DELETE http://localhost:8080/dmit2015-project-rest-services-benjamintam/webapi/reviews/5 \
    -H 'Authorization: Bearer eyJraWQiOiJxdWlja3N0YXJ0LWp3dC1pc3N1ZXIiLCJ0eXAiOiJqd3QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ1c2VybW92aWUiLCJ1cG4iOiJ1c2VybW92aWUiLCJpc3MiOiJxdWlja3N0YXJ0LWp3dC1pc3N1ZXIiLCJhdWQiOiJqd3QtYXVkaWVuY2UiLCJncm91cHMiOlsiVVNFUiJdLCJqdGkiOiI1Y2M1ZTI2Yi04ODUzLTRmNDQtYTgyMS03ZmQ2ZDQwMzc1NGQiLCJpYXQiOjE2MTkxMDM4MjQsImV4cCI6MTYxOTExODIyNH0.c1vLSexTGNPLIfHNPl4jGwGYYpDh5q1ONIbTW44JDzI_d30_xEEWk7nTQL3s4tFe3jMfpa4VCyrnAr466lBtgSbsGrVfX9e0lGpc3dzGroFrWBvFtcBBOyIDwP7ijF-p6ImscjiDFHPcVZkxlfMNhiduuHPxGJiJOGEyW236FTxVZpLEWipTisgvSQ2fxbf4R-FtE5nEE2nW1geIrI65G0smbaSHnNWrcdXBl1z793gKU1IG9iu1bxFtSJcDyaRa2HDCE9CJQlqL69CfzCNUwqQadfLG5wVXgKQcFcWKmff3ZVLQgxohcNogx_cT6-tIXIDAGXs6ySc73hvNJA52NA' \

ADMIN:
curl -k -i -X POST https://localhost:8443/dmit2015-project-rest-services-benjamintam/webapi/jwt/jsonLogin \
    -d '{"username":"adminmovie","password":"Password2015"}' \
    -H 'Content-Type:application/json'

curl -i -X GET http://localhost:8080/dmit2015-project-rest-services-benjamintam/webapi/reviews/username \
    -H 'Authorization: Bearer eyJraWQiOiJxdWlja3N0YXJ0LWp3dC1pc3N1ZXIiLCJ0eXAiOiJqd3QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbm1vdmllIiwidXBuIjoiYWRtaW5tb3ZpZSIsImlzcyI6InF1aWNrc3RhcnQtand0LWlzc3VlciIsImF1ZCI6Imp3dC1hdWRpZW5jZSIsImdyb3VwcyI6WyJBRE1JTiJdLCJqdGkiOiI5ZDlmNTUxYS03MjU2LTQ2YzAtOWYwNC02ODIzYjkzNTMwYTgiLCJpYXQiOjE2MTkxMDM5NzgsImV4cCI6MTYxOTExODM3OH0.T0JeF_LlJoQ-lc3S3vq0v-Fq9i9Vz6ayOFtoYgshBr1xYV-WYDFPe2suD7-ijETvMVCFFrmhEdxH8qaZxsbRC17FmQ9ZyMa4eM1gyQiY-oy7r4ZHIP5gVX2No2tsGdjQvlcYShzWvPqzc3tR4TexLnEIAMvxZyO69pqwIJVTlngTs4-SFm1Ve7gc8PxI6qMj49W3lG3BLOuO3zudxOsPNmSesNxDoFTbBd9eRfn6yt6tbkquksLoLTAdVJijsHvlAmPZwVyX8PYR7VYzIydcGgRfsWmizZRbPy4BwDZlZGlJk4slCUQoyZoi30weataiRqoS_vf9KHEK6uEih80Ilg'

curl -i -X DELETE http://localhost:8080/dmit2015-project-rest-services-benjamintam/webapi/reviews/5 \
    -H 'Authorization: Bearer eyJraWQiOiJxdWlja3N0YXJ0LWp3dC1pc3N1ZXIiLCJ0eXAiOiJqd3QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbm1vdmllIiwidXBuIjoiYWRtaW5tb3ZpZSIsImlzcyI6InF1aWNrc3RhcnQtand0LWlzc3VlciIsImF1ZCI6Imp3dC1hdWRpZW5jZSIsImdyb3VwcyI6WyJBRE1JTiJdLCJqdGkiOiI5ZDlmNTUxYS03MjU2LTQ2YzAtOWYwNC02ODIzYjkzNTMwYTgiLCJpYXQiOjE2MTkxMDM5NzgsImV4cCI6MTYxOTExODM3OH0.T0JeF_LlJoQ-lc3S3vq0v-Fq9i9Vz6ayOFtoYgshBr1xYV-WYDFPe2suD7-ijETvMVCFFrmhEdxH8qaZxsbRC17FmQ9ZyMa4eM1gyQiY-oy7r4ZHIP5gVX2No2tsGdjQvlcYShzWvPqzc3tR4TexLnEIAMvxZyO69pqwIJVTlngTs4-SFm1Ve7gc8PxI6qMj49W3lG3BLOuO3zudxOsPNmSesNxDoFTbBd9eRfn6yt6tbkquksLoLTAdVJijsHvlAmPZwVyX8PYR7VYzIydcGgRfsWmizZRbPy4BwDZlZGlJk4slCUQoyZoi30weataiRqoS_vf9KHEK6uEih80Ilg' \

curl -i -X GET http://localhost:8080/dmit2015-project-rest-services-benjamintam/webapi/reviews/5
*/

package dmit2015.resource;

import dmit2015.entity.OscarReview;
import dmit2015.repository.OscarReviewRepository;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequestScoped
@Path("reviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

/**
 This class contains the resource class for the OscarReview entity
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

public class OscarReviewResource {

    @Inject
    private JsonWebToken _callerPrincipal;

    @Inject
    private OscarReviewRepository _oscarReviewRepository;

    @GET
    public Response findAllReviews() {
        List<OscarReview> reviews = _oscarReviewRepository.findAll();
        return Response.ok(reviews).build();
    }

    @RolesAllowed({"USER","ADMIN"})
    @GET
    @Path("username")
    public Response findAllReviewsByUsername() {
        String username = _callerPrincipal.getName();
        Set<String> roles = _callerPrincipal.getGroups();
        List<OscarReview> reviews = new ArrayList<OscarReview>();
        if (roles.contains("USER")) {
            reviews = _oscarReviewRepository.findAllByUsername(username);
        }
        else if (roles.contains("ADMIN")){
            reviews = _oscarReviewRepository.findAll();
        }
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

    @RolesAllowed({"USER","ADMIN"})
    @POST
    public Response createNewReview(@Valid OscarReview newReview, @Context UriInfo uriInfo) {
        if (newReview == null) {
            throw new BadRequestException();
        }
        String username = _callerPrincipal.getName();
        newReview.setUsername(username);
        _oscarReviewRepository.add(newReview);
        URI locationUri = uriInfo.getAbsolutePathBuilder().path(newReview.getId().toString()).build();
        return Response.created(locationUri).build();
    }

    @RolesAllowed({"USER","ADMIN"})
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

    @RolesAllowed({"ADMIN"})
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
