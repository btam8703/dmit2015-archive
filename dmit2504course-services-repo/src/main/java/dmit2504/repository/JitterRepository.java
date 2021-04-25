package dmit2504.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import dmit2504.entity.Jitter;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class JitterRepository {

    @PersistenceContext(unitName = "h2database-jpa-pu")
    private EntityManager entityManager;

    public void create(Jitter newJitter) {
        entityManager.persist(newJitter);
    }

    public Optional<Jitter> findOneById(Long id) {
        return Optional.of(entityManager.find(Jitter.class, id));
    }

    public List<Jitter> findAll() {
        return entityManager.createNativeQuery("SELECT * FROM Jitters").getResultList();
    }

    public void update(Jitter existingJitter) {
        //if (!entityManager.contains(existingJitter)) {
           // existingJitter = entityManager.
      //  }
        entityManager.merge(existingJitter);
    }

    public void delete(Long id) {
        Optional<Jitter> optionalJitter = findOneById(id);
        if (optionalJitter.isPresent()) {
            Jitter existingJitter = optionalJitter.get();
            entityManager.remove(existingJitter);
        }
    }
}
