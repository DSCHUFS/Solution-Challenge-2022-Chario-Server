package ChariO.GiBoo.repository;

import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void post(String u_username, String u_email,
                     String u_name, String u_phone, String u_birth, String u_uuid) {
        User user = new User();

        user.setU_username(u_username);
        user.setU_email(u_email);
        user.setU_name(u_name);
        user.setU_phone(u_phone);
        user.setU_birth(u_birth);
        user.setU_uuid(u_uuid);

        em.persist(user);
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    public User findOne(Long id){
        return em.find(User.class, id);
    }

    public User findUserSubscribe(Long id) {
        return em.createQuery("select distinct u from User u" +
                " join fetch u.subscribeList s" +
                " where u.id = :id ", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    /**테스트용**/
    public List<User> findOne1(Long id,int offset, int limit) {
        return em.createQuery("select distinct u from User u" +
                        " join fetch u.subscribeList s" +
                        " join s.facility f" +
                        " join f.facilityCategoryList c" +
                        " join c.category fc" +
                        " where u.id = :id ", User.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .setParameter("id", id)
                .getResultList();
    }

    public User update(Long u_id, String u_name, String u_email, String u_phone){
        User user = em.find(User.class, u_id);
        user.setU_name(u_name);
        user.setU_email(u_email);
        user.setU_phone(u_phone);
        em.merge(user);
        return user;
    }

    /** 추후에 spring-data-jpa 사용 시 이동 예정 */
    public void delete(Long u_id) {
        User user = em.find(User.class, u_id);
        em.remove(user);
    }

    public Long findIdByUuid(String u_uuid){
        User user = em.createQuery("select distinct u from User u" +
                        " where u.u_uuid = :u_uuid ", User.class)
                .setParameter("u_uuid", u_uuid)
                .getSingleResult();
        return user.getId();
    }

    //public void delte(User user) {}
}