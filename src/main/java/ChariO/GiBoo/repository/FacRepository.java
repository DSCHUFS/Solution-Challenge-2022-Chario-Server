package ChariO.GiBoo.repository;

import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.domain.FacilityCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FacRepository {

    @Autowired
    private final EntityManager em;

    public List<Facility> findAll() {
        return em.createQuery("select f from Facility f", Facility.class)
                .getResultList();
    }

    public List<FacilityCategory> findByCategory(Long category_id) {
        return em.createQuery("select fc from FacilityCategory fc " +
                        "join fetch fc.facility f " +
                        "where fc.category.id = :category_id", FacilityCategory.class)
                .setParameter("category_id", category_id)
                .getResultList();
    }

    public Facility findOne(Long id) {
        return em.find(Facility.class, id);
    }

    //For Search v1
    public Facility findOneByName(String facName) {
        return em.createQuery(" select f from Facility f"+
                " where f.f_name = :facName", Facility.class)
                .setParameter("facName", facName).getSingleResult();
    }

    //For Search v2
    public List<Facility> findOneByNameLike(String facName) {
        String jpql = "select f from Facility f where f.f_name like :facName";
        TypedQuery<Facility> query = em.createQuery(jpql, Facility.class).setParameter("facName", "%" + facName +  "%").setMaxResults(1000);
        return query.getResultList();
    }

}