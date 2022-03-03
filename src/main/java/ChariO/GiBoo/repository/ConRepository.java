package ChariO.GiBoo.repository;

import ChariO.GiBoo.domain.Contents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConRepository {

    private final EntityManager em;
    private final FacRepository facRepository;

    public Contents findOne(Long id){
        return em.createQuery("select c from Contents c " +
                "join fetch c.facility "+
                "where c.id = :id", Contents.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Contents> findByFac(Long id){
        return em.createQuery("select c from Contents c where c.facility.id = :id", Contents.class)
                .setParameter("id", id)
                .getResultList();
    }
}
