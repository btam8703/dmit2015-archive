package oscarreview;

/**
 This class contains exception for an OscarReview not being found
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

class OscarReviewNotFoundException extends RuntimeException {

    OscarReviewNotFoundException(Long id) {
        super("Could not find review " + id);
    }
}