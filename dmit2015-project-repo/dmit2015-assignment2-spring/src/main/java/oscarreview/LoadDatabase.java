package oscarreview;

import org.hibernate.type.LocalDateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 This class loads items into the database
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(OscarReviewRepository oscarReviewRepository) {
        return args -> {
            oscarReviewRepository.save(new OscarReview("actor", "Sam Wu", "Heart wrenching performance by Sam Wu", "filmguy1"));
            oscarReviewRepository.save(new OscarReview("effects", "Transformers", "Good realistic effects", "reviewer21"));
            oscarReviewRepository.save(new OscarReview("editing", "Crouching Tiger Hidden Dragon", "Cuts feel good to the viewer", "coolguy2"));
            oscarReviewRepository.save(new OscarReview("film", "Generic Movie", "Best film of the year hands down", "bttreview"));


            oscarReviewRepository.findAll().forEach(OscarReview -> log.info("Preloaded " + OscarReview));

        };
    }
}
