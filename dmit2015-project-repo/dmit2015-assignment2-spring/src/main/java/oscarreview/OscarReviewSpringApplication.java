package oscarreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 This is the main class of the application
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

@SpringBootApplication
public class OscarReviewSpringApplication {

	public static void main(String... args) {
		SpringApplication.run(OscarReviewSpringApplication.class, args);
	}

	//curl -v localhost:8080/reviews
	//
	//curl -v localhost:8080/reviews/1
	//
	//curl -v -X POST localhost:8080/reviews -H 'Content-Type:application/json' -d '{"category": "editing", "nominee": "Whiplash", "review": "Good editing this year", "username": "moviereviewer100"}'
	//
	//curl -v localhost:8080/reviews/5
	//
	//curl -v -X PUT localhost:8080/reviews/5 -H 'Content-Type:application/json' -d '{"category": "movie", "nominee": "The Thing", "review": "Cool flick", "username": "ihatehorror"}'
	//
	//curl -v -X DELETE localhost:8080/reviews/5
}
