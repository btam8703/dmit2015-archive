package dmit2015.assessment01.bentam.repository;

import dmit2015.assessment01.bentam.entity.Animal;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 This class is a repository for the Animal class
 @author Benjamin Tam
 @version 1.0
 @since 2021-02-12
 */


@ApplicationScoped
@Transactional
public class AnimalRepository {

    @PersistenceContext(unitName ="h2database-jpa-pu")
    private EntityManager _entityManager;

    public void create(Animal newAnimal) {
        _entityManager.persist(newAnimal);
    }

    public Optional<Animal> findOneByYear(Integer year) {
        Optional<Animal> optionalAnimal = Optional.empty();
        try {
            Animal querySingleResult = _entityManager.find(Animal.class, year);
            if (querySingleResult != null) {
                optionalAnimal = Optional.of(querySingleResult);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return optionalAnimal;
    }

    public List<Animal> findAll() {
        return _entityManager.createQuery(
                "SELECT m FROM Animal m"
                , Animal.class)
                .getResultList();
    }
}
