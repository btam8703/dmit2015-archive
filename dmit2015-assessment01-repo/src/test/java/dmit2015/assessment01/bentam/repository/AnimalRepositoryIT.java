package dmit2015.assessment01.bentam.repository;

import dmit2015.assessment01.bentam.config.ApplicationConfig;
import dmit2015.assessment01.bentam.entity.Animal;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.io.*;
import java.util.*;

import javax.inject.Inject;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;


/**
 This Integration Test class contains various methods that test the CRUD methods for the Animal entity
 @author Benjamin Tam
 @version 1.0
 @since 2020-02-05
 */



@ExtendWith(ArquillianExtension.class)
class AnimalRepositoryIT {
    @Inject
    private AnimalRepository _animalRepository;

    @Deployment
    public static WebArchive createDeployment() {
        PomEquippedResolveStage pomFile = Maven.resolver().loadPomFromFile("pom.xml");

        return ShrinkWrap.create(WebArchive.class,"test.war")
                .addAsLibraries(pomFile.resolve("com.h2database:h2:1.4.200").withTransitivity().asFile())
                .addAsLibraries(pomFile.resolve("org.hamcrest:hamcrest:2.2").withTransitivity().asFile())
                .addAsLibraries(pomFile.resolve("org.hibernate:hibernate-core:5.3.20.Final").withTransitivity().asFile())
                .addClass(ApplicationConfig.class)
                .addClasses(Animal.class, AnimalRepository.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("META-INF/sql/import-data.sql")
                .addAsWebInfResource(EmptyAsset.INSTANCE,"beans.xml");
    }

    @Transactional(TransactionMode.ROLLBACK)
    @Test
    void shouldCreate() {
        //Don't know why this is not running, getting the following error
        //org.hibernate.PersistentObjectException: detached entity passed to persist: dmit2015.assessment01
        Animal newAddAnimal = new Animal();
        newAddAnimal.setYear(2024);
        newAddAnimal.setType("Dragon");
        _animalRepository.create(newAddAnimal);

        Optional<Animal> optionalAnimal = _animalRepository.findOneByYear(newAddAnimal.getYear());
        assertTrue(optionalAnimal.isPresent());
        Animal existingAnimal = optionalAnimal.get();
        assertNotNull(existingAnimal);
        assertEquals(2024, existingAnimal.getYear());
        assertEquals("Dragon", existingAnimal.getType());
    }

    @Test
    void shouldFindOne() {
        final Integer year1 = 2021;
        final Integer year2 = 2022;
        Optional<Animal> optionalAnimal = _animalRepository.findOneByYear(year1);
        assertTrue(optionalAnimal.isPresent());
        Animal existingAnimal = optionalAnimal.get();
        assertNotNull(existingAnimal);
        assertEquals(2021, existingAnimal.getYear());
        assertEquals("Ox", existingAnimal.getType());

       optionalAnimal = _animalRepository.findOneByYear(year2);
       assertTrue(optionalAnimal.isPresent());
       existingAnimal = optionalAnimal.get();
       assertNotNull(existingAnimal);
       assertEquals(2022, existingAnimal.getYear());
       assertEquals("Tiger", existingAnimal.getType());
    }

    @Test
    void shouldFindAll() {
        List<Animal> queryResultList = _animalRepository.findAll();
        assertEquals(3, queryResultList.size());

        queryResultList.forEach(System.out::println);
    }
}