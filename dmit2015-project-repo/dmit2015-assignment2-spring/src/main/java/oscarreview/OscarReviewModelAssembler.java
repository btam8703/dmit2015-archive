package oscarreview;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 This class is the ModelAssembler for an OscarReview
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

@Component
class OscarReviewModelAssembler implements RepresentationModelAssembler<OscarReview, EntityModel<OscarReview>> {

    @Override
    public EntityModel<OscarReview> toModel(OscarReview review) {

        return EntityModel.of(review, //
                linkTo(methodOn(OscarReviewController.class).one(review.getId())).withSelfRel(),
                linkTo(methodOn(OscarReviewController.class).all()).withRel("reviews"));
    }
}