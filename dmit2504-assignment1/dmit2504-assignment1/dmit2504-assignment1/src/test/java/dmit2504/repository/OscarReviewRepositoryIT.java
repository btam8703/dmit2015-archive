package dmit2504.repository;

import dmit2504.config.ApplicationConfig;
import dmit2504.entity.OscarReview;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 This Integration Test class contains various methods that test the CRUD methods
 @author Benjamin Tam
 @version 1.1
 @since 2020-02-05
 */

@ExtendWith(ArquillianExtension.class) //Run with Junit 5 instead of 4
class OscarReviewRepositoryIT {

    @Deployment
    public static WebArchive createDeployment() {
        PomEquippedResolveStage pomFile = Maven.resolver().loadPomFromFile("pom.xml");

        return ShrinkWrap.create(WebArchive.class,"test.war")
//                .addAsLibraries(pomFile.resolve("groupId:artifactId:version").withTransitivity().asFile())
                .addAsLibraries(pomFile.resolve("com.h2database:h2:1.4.200").withTransitivity().asFile())
                // .addAsLibraries(pomFile.resolve("com.microsoft.sqlserver:mssql-jdbc:8.4.1.jre11").withTransitivity().asFile())
                // .addAsLibraries(pomFile.resolve("com.oracle.database.jdbc:ojdbc10:19.9.0.0").withTransitivity().asFile())
                .addAsLibraries(pomFile.resolve("org.hamcrest:hamcrest:2.2").withTransitivity().asFile())
                .addAsLibraries(pomFile.resolve("org.hibernate:hibernate-core:5.3.20.Final").withTransitivity().asFile())
                .addClass(ApplicationConfig.class)
                .addClasses(OscarReview.class, OscarReviewRepository.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("META-INF/sql/import-data.sql")
                .addAsWebInfResource(EmptyAsset.INSTANCE,"beans.xml");
    }

    @Inject
    private OscarReviewRepository _oscarReviewRepository;

    @Transactional(TransactionMode.ROLLBACK)
    @Test
    void shouldAdd() {
        OscarReview newReview = new OscarReview();
        newReview.setCategory("film");
        newReview.setNominee("Star Wars");
        newReview.setReview("Great and fun movie");
        newReview.setUsername("moviefan87");
        _oscarReviewRepository.add(newReview);

        Optional<OscarReview> optionalReview = _oscarReviewRepository.findById(newReview.getId());
        assertTrue(optionalReview.isPresent());
        OscarReview existingReview = optionalReview.get();
        assertNotNull(existingReview);
        assertEquals("film", existingReview.getCategory());
        assertEquals("Star Wars", existingReview.getNominee());
        assertEquals("Great and fun movie", existingReview.getReview());
        assertEquals("moviefan87", existingReview.getUsername());

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime creationTime = existingReview.getCreatedDateTime();
        Duration durationBetween = Duration.between(currentTime, creationTime);

        long timeDiff = Math.abs(durationBetween.toMillis());
        assertTrue(timeDiff < 200);
    }

    @Transactional(TransactionMode.ROLLBACK)
    @Test
    void shouldUpdate() {
        final Long reviewId = 1L;
        Optional<OscarReview> optionalReview = _oscarReviewRepository.findById(reviewId);
        assertTrue(optionalReview.isPresent());
        OscarReview existingReview = optionalReview.get();
        assertNotNull(existingReview);
        existingReview.setCategory("film");
        existingReview.setNominee("Avengers");
        existingReview.setReview("Greatest movie");
        existingReview.setUsername("movielover");
        _oscarReviewRepository.update(existingReview);

        Optional<OscarReview> optionalUpdatedReview = _oscarReviewRepository.findById(reviewId);
        assertTrue(optionalUpdatedReview.isPresent());
        OscarReview updatedReview = optionalUpdatedReview.get();
        assertNotNull(updatedReview);
        assertEquals(existingReview.getCategory(), updatedReview.getCategory());
        assertEquals(existingReview.getNominee(), updatedReview.getNominee());
        assertEquals(existingReview.getReview(), updatedReview.getReview());
        assertEquals(existingReview.getUsername(), updatedReview.getUsername());

        //Check if not null
        assertNotEquals("null", updatedReview.getLastModifiedDateTime().toString());
        assertNotNull(updatedReview.getLastModifiedDateTime());

        //Check if recently updated
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime updateTime = existingReview.getLastModifiedDateTime();
        Duration durationBetween = Duration.between(currentTime, updateTime);

        long timeDiff = Math.abs(durationBetween.toMillis());
        assertTrue(timeDiff < 200);
    }

    @Transactional(TransactionMode.ROLLBACK)
    @Test
    void shouldRemove() {
        final Long reviewId = 1L;
        Optional<OscarReview> optionalOscarReview = _oscarReviewRepository.findById(reviewId);
        assertTrue(optionalOscarReview.isPresent());
        OscarReview existingReview = optionalOscarReview.get();
        assertNotNull(existingReview);
        _oscarReviewRepository.remove(existingReview.getId());
        optionalOscarReview = _oscarReviewRepository.findById(reviewId);
        assertTrue(optionalOscarReview.isEmpty());
    }

    @Test
    void shouldFindById() {
        final Long reviewId = 1L;
        Optional<OscarReview> optionalReview = _oscarReviewRepository.findById(reviewId);
        assertTrue(optionalReview.isPresent());
        OscarReview existingReview = optionalReview.get();
        assertNotNull(existingReview);
        assertEquals("actor", existingReview.getCategory());
        assertEquals("Sam Wu", existingReview.getNominee());
        assertEquals("Heart wrenching performance by Sam Wu", existingReview.getReview());
        assertEquals("filmguy1", existingReview.getUsername());
        assertEquals("2020-02-05T00:00", existingReview.getCreatedDateTime().toString());
    }

    @Test
    void shouldFindAll() {
        List<OscarReview> queryResultList = _oscarReviewRepository.findAll();
        assertEquals(4, queryResultList.size());

        OscarReview firstReview = queryResultList.get(0);
        assertEquals("actor", firstReview.getCategory());
        assertEquals("Sam Wu", firstReview.getNominee());
        assertEquals("Heart wrenching performance by Sam Wu", firstReview.getReview());
        assertEquals("filmguy1", firstReview.getUsername());
        assertEquals("2020-02-05T00:00", firstReview.getCreatedDateTime().toString());


        OscarReview lastReview  = queryResultList.get(queryResultList.size() - 1);
        assertEquals("film", lastReview.getCategory());
        assertEquals("Generic Movie", lastReview.getNominee());
        assertEquals("Best film of the year hands down", lastReview.getReview());
        assertEquals("bttreview", lastReview.getUsername());
        assertEquals("2020-02-05T00:00", lastReview.getCreatedDateTime().toString());

        queryResultList.forEach(System.out::println);
    }

    @Test void shouldFail() { fail("this should fail"); }
}