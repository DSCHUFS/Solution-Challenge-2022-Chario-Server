package ChariO.GiBoo.repository;

import ChariO.GiBoo.domain.Facility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FacRepository {

    private final EntityManager em;

    public List<Facility> findAll(){
        return em.createQuery("select f from Facility f", Facility.class)
                .getResultList();
    }

    public Facility findOne(Long id){
        return em.find(Facility.class, id);
    }
}
