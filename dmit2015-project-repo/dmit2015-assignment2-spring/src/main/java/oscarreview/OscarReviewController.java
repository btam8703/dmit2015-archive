package oscarreview;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 This class is the controller for an OscarReview
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

@RestController
class OscarReviewController {

    private final OscarReviewRepository repository;

    private final OscarReviewModelAssembler assembler;

    OscarReviewController(OscarReviewRepository repository, OscarReviewModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @PostMapping("/reviews")
    ResponseEntity<?> newReview(@RequestBody OscarReview newReview) {

        EntityModel<OscarReview> entityModel = assembler.toModel(repository.save(newReview));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/reviews/{id}")
    ResponseEntity<?> replaceReview(@RequestBody OscarReview newReview, @PathVariable Long id) {

        OscarReview updatedReview = repository.findById(id) //
                .map(review -> {
                    review.setCategory(newReview.getCategory());
                    review.setNominee(newReview.getNominee());
                    review.setReview(newReview.getReview());
                    review.setUsername(newReview.getUsername());
                    return repository.save(review);
                }) //
                .orElseGet(() -> {
                    newReview.setId(id);
                    return repository.save(newReview);
                });

        EntityModel<OscarReview> entityModel = assembler.toModel(updatedReview);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/reviews/{id}")
    ResponseEntity<?> deleteReview(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reviews/{id}")
    EntityModel<OscarReview> one(@PathVariable Long id) {

        OscarReview review = repository.findById(id) //
                .orElseThrow(() -> new OscarReviewNotFoundException(id));

        return assembler.toModel(review);
    }

    @GetMapping("/reviews")
    CollectionModel<EntityModel<OscarReview>> all() {

        List<EntityModel<OscarReview>> reviews = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(reviews, linkTo(methodOn(OscarReviewController.class).all()).withSelfRel());
    }

}