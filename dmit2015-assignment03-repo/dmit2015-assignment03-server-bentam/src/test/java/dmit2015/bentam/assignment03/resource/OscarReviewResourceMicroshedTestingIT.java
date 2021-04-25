package dmit2015.bentam.assignment03.resource;

import dmit2015.bentam.assignment03.entity.OscarReview;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.junit.jupiter.Container;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

/**
 This Integration Test class contains various methods that test the JAXRS Web CRUD methods
 @author Benjamin Tam
 @version 1.1
 @since 2020-02-28
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@MicroShedTest
public class OscarReviewResourceMicroshedTestingIT {

    @Container
    public static ApplicationContainer app = new ApplicationContainer()
            .withAppContextRoot("/dmit2015-assignment03-server-bentam")
            .withReadinessPath("/dmit2015-assignment03-server-bentam/webapi/reviews")
            .withStartupTimeout(Duration.ofSeconds(120));

    String testDataResourceLocation;

    @Order(1)
    @Test
    void shouldListAll() {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/webapi/reviews")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();
        String jsonBody = response.getBody().asString();

        // Create a new Jsonb instance using the default JsonbBuilder implementation
        Jsonb jsonb = JsonbBuilder.create();
        List<OscarReview> reviews = jsonb.fromJson(jsonBody, new ArrayList<OscarReviewResource>(){}.getClass().getGenericSuperclass());
        assertEquals(4, reviews.size());
    }

    @Order(2)
    @Test
    void shouldCreate() {
        OscarReview currentReview = new OscarReview();
        currentReview.setCategory("editing");
        currentReview.setNominee("testingmovie");
        currentReview.setReview("super boring movie");
        currentReview.setUsername("boringguy1");

        Jsonb jsonb = JsonbBuilder.create();
        String jsonBody = jsonb.toJson(currentReview);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post("/webapi/reviews")
                .then()
                .statusCode(201)
                .extract()
                .response();
        testDataResourceLocation = response.getHeader("location");
    }

    @Order(3)
    @Test
    void shouldFindOne() {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(testDataResourceLocation)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();
        String jsonBody = response.getBody().asString();
        Jsonb jsonb = JsonbBuilder.create();
        OscarReview existingReview = jsonb.fromJson(jsonBody, OscarReview.class);
        assertEquals("editing", existingReview.getCategory());
        assertEquals("testingmovie", existingReview.getNominee());
        assertEquals("super boring movie", existingReview.getReview());
        assertEquals("boringguy1", existingReview.getUsername());
    }

    @Order(4)
    @Test
    void shouldUpdate() {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(testDataResourceLocation)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();
        String jsonBody = response.getBody().asString();
        Jsonb jsonb = JsonbBuilder.create();
        OscarReview currentReview = jsonb.fromJson(jsonBody, OscarReview.class);
        currentReview.setNominee("Frozen");
        currentReview.setCategory("editing");
        currentReview.setReview("terrible editing");
        currentReview.setUsername("stevenspielberg");

        String jsonRequestBody = jsonb.toJson(currentReview);
        given()
                .contentType(ContentType.JSON)
                .body(jsonRequestBody)
                .when()
                .put(testDataResourceLocation)
                .then()
                .statusCode(204);
    }

    @Order(5)
    @Test
    void shouldDelete() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete(testDataResourceLocation)
                .then()
                .statusCode(204);
    }
}