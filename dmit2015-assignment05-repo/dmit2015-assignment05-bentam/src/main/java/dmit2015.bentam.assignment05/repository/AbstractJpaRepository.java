package dmit2015.bentam.assignment05.repository;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.*;

/**
 This class contains the repository for the AbstractJpa
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-08
 */

@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class AbstractJpaRepository<E extends Serializable, ID> {

    private final Class<E> entityType;

    public AbstractJpaRepository(Class<E> entityType) {
        this.entityType = entityType;
    }

    @PersistenceContext(unitName = "oracle-jpa-pu")
    private EntityManager _entityManager;

    public <E> void create(E entity) {
        _entityManager.persist(entity);
    }

    public <ID> E find(ID id) {
        return _entityManager.find(entityType, id);
    }

    public <ID> Optional<E> findOneById(ID id) {
        Optional<E> optionalResult = Optional.empty();
        try {
            E singleResult = find(id);
            if (singleResult != null) {
                optionalResult = Optional.of(singleResult);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return optionalResult;
    }

    public <ID> E findFresh(ID id) {
        Map<String, Object> hints = new HashMap<>();
        hints.put("javax.persistence.cache.retrieveMode", "BYPASS");
        return _entityManager.find(entityType, id, hints);
    }

    public List<E> findAll() {
        final CriteriaQuery<E> criteriaQuery = _entityManager.getCriteriaBuilder().createQuery(entityType);
        Root<E> queryRoot = criteriaQuery.from(entityType);
        criteriaQuery.select(queryRoot);
        return _entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<E> findRange(int from, int to) {
        CriteriaQuery<E> criteriaQuery = _entityManager.getCriteriaBuilder().createQuery(entityType);
        Root<E> queryRoot = criteriaQuery.from(entityType);
        criteriaQuery.select(queryRoot);
        Query query = _entityManager.createQuery(criteriaQuery);
        query.setMaxResults(to - from + 1);
        query.setFirstResult(from);
        return query.getResultList();
    }

    public List<E> findRange(int[] range) {
        return findRange(range[0], range[1]);
    }

    public <E> E update(E entity) {
        E merge = _entityManager.merge(entity);
        return merge;
    }

    public <E> void delete(E entity) {
        if (isAttached(entity)) {
            _entityManager.remove(entity);
        } else {
            _entityManager.remove(_entityManager.merge(entity));
        }
    }

    public <ID> void deleteById(ID id) {
        Optional<E> optionalEntity = findOneById(id);
        if (optionalEntity.isPresent()) {
            E entity = optionalEntity.get();
            delete(entity);
        }
    }

    public int deleteAll() {
        CriteriaBuilder criteriaBuilder = _entityManager.getCriteriaBuilder();
        CriteriaDelete<E> delete = criteriaBuilder.createCriteriaDelete(entityType);
        Root<E> root = delete.from(entityType);
        return _entityManager.createQuery(delete).executeUpdate();
    }

    public <E> int count(Class<E> entityType) {
        CriteriaBuilder criteriaBuilder = _entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<E> queryRoot = criteriaQuery.from(entityType);
        criteriaQuery.select(criteriaBuilder.count(queryRoot));
        return ((Long) _entityManager.createQuery(criteriaQuery).getSingleResult()).intValue();
    }

    public boolean isAttached(Object entity) {
        return _entityManager.contains(entity);
    }

    public void clearCache() {
        _entityManager.flush();
        _entityManager.getEntityManagerFactory().getCache().evictAll();
    }

    protected EntityManager getEntityManager() {
        return _entityManager;
    }
}