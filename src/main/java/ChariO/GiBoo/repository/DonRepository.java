package ChariO.GiBoo.repository;

import ChariO.GiBoo.domain.Donation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DonRepository {

    @Autowired
    private EntityManager em;

    public List<Donation> userDonateAll(Long id) {
        return em.createQuery(" select d from Donation d " +
                "join fetch d.facility f " +
                "where d.user.id = :id"
        , Donation.class)
                .setParameter("id", id)
                .getResultList();
    }

}
