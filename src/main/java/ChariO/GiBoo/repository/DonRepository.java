package ChariO.GiBoo.repository;

import ChariO.GiBoo.domain.Donation;
import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DonRepository {

    @Autowired
    private EntityManager em;

    public Donation create(Long u_id, String f_name, int price, String don_date) {
        Donation donation = new Donation();
        User user = em.find(User.class, u_id);
        TypedQuery<Facility> facilityresult = em.createQuery("select f from Facility f " +
                        "where f.f_name = :f_name", Facility.class)
                        .setParameter("f_name", f_name);
        Facility facility = facilityresult.getSingleResult();

        donation.setUser(user);
        donation.setFacility(facility);
        donation.setDonationPrice(price);
        donation.setDonationDate(don_date);
        em.persist(donation);
        return donation;
    }

    public List<Donation> userDonateAll(Long id) {
        return em.createQuery(" select d from Donation d " +
                "join fetch d.facility f " +
                "where d.user.id = :id"
        , Donation.class)
                .setParameter("id", id)
                .getResultList();
    }

    public Donation update(Long u_id, String f_name, int price, String don_date) {
        Donation donation =
                em.createQuery("select d from Donation d" +
                " where d.user.id =: u_id " +
                " and d.facility.f_name =: f_name", Donation.class)
                .setParameter("u_id", u_id)
                .setParameter("f_name", f_name).getSingleResult();

        donation.setDonationPrice(price);
        donation.setDonationDate(don_date);
        return donation;
    }

    public Donation findByUserFac(Long u_id, String f_name){
        return em.createQuery("select d from Donation d " +
                        "where d.user.id =: u_id " +
                        "and d.facility.f_name =: f_name ", Donation.class)
                .setParameter("u_id", u_id)
                .setParameter("f_name", f_name)
                .getSingleResult();
    }

    public void delete(Donation donation){
        em.remove(donation);
    }
}
