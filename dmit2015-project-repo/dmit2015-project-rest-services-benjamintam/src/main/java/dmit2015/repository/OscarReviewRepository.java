package dmit2015.repository;

import dmit2015.entity.OscarReview;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 This class is a repository for the OscarReview class
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

@ApplicationScoped
@Transactional
public class OscarReviewRepository {

    @PersistenceContext(unitName ="h2database-jpa-pu")
    private EntityManager entityManager;

    public void add(OscarReview newReview) {
        entityManager.persist(newReview);
    }

    public void update(OscarReview updatedReview) {
        Optional<OscarReview> optionalReview = findById(updatedReview.getId());
        if (optionalReview.isPresent()) {
            OscarReview existingReview = optionalReview.get();
            existingReview.setCategory(updatedReview.getCategory());
            existingReview.setNominee(updatedReview.getNominee());
            existingReview.setReview(updatedReview.getReview());
            existingReview.setUsername(updatedReview.getUsername());
            entityManager.merge(existingReview);
            entityManager.flush();
        }
    }

    public void remove(OscarReview existingReview) {
        entityManager.remove(entityManager.merge(existingReview));
        entityManager.flush();
    }

    public void remove(Long reviewID) {
        Optional<OscarReview> optionalReview = findById(reviewID);
        if (optionalReview.isPresent()) {
            OscarReview existingReview = optionalReview.get();
            remove(existingReview);
        }
    }

    public Optional<OscarReview> findById(Long reviewID) {
        Optional<OscarReview> optionalReview = Optional.empty();
        try {
            OscarReview querySingleResult = entityManager.find(OscarReview.class, reviewID);
            if (querySingleResult != null) {
                optionalReview = Optional.of(querySingleResult);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return optionalReview;
    }

    public List<OscarReview> findAll() {
        return entityManager.createQuery(
                "SELECT m FROM OscarReview m  "
                , OscarReview.class)
                .getResultList();
    }

    public List<OscarReview> findAllByUsername(String username) {
        return entityManager.createQuery("SELECT m FROM OscarReview m WHERE m.username = :usernameReq", OscarReview.class)
                .setParameter("usernameReq", username).getResultList();

    }
}
