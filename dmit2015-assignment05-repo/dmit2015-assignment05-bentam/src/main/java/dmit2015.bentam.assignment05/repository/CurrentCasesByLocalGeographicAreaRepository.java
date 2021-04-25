package dmit2015.bentam.assignment05.repository;

import dmit2015.bentam.assignment05.entity.CurrentCasesByLocalGeographicArea;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.builder.DSL;
import org.geolatte.geom.crs.CoordinateReferenceSystems;

/**
 This class contains the repository for a Current Case
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-08
 */

@ApplicationScoped
@Transactional
public class CurrentCasesByLocalGeographicAreaRepository extends AbstractJpaRepository<CurrentCasesByLocalGeographicArea, Short> {

    public CurrentCasesByLocalGeographicAreaRepository() {
        super(CurrentCasesByLocalGeographicArea.class);
    }

    public List<CurrentCasesByLocalGeographicArea> list() {
        String jpql = "SELECT e FROM CurrentCasesByLocalGeographicArea e";
        TypedQuery<CurrentCasesByLocalGeographicArea> query = getEntityManager().createQuery(jpql, CurrentCasesByLocalGeographicArea.class);
        List<CurrentCasesByLocalGeographicArea> queryResultList = query.getResultList();
        return queryResultList;
    }

    public Optional<CurrentCasesByLocalGeographicArea> contains(double longitude, double latitude) {
        Optional<CurrentCasesByLocalGeographicArea> optionalSingleResult = Optional.empty();
        final String jpql = "SELECT a FROM CurrentCasesByLocalGeographicArea a WHERE contains(a.polygon, :pointValue) = true";
        TypedQuery<CurrentCasesByLocalGeographicArea> query =
                getEntityManager().createQuery(jpql, CurrentCasesByLocalGeographicArea.class);
        Point<G2D> point = DSL.point(CoordinateReferenceSystems.WGS84, DSL.g(longitude,
                latitude));
        query.setParameter("pointValue", point);
        try {
            CurrentCasesByLocalGeographicArea singleResult = query.getSingleResult();
            optionalSingleResult = Optional.of(singleResult);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return optionalSingleResult;
    }

}