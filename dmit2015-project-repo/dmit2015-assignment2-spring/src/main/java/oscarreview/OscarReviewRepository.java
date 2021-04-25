package oscarreview;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 This class is the repository for an OscarReview
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

interface OscarReviewRepository extends JpaRepository<OscarReview, Long> {

}