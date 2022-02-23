package ChariO.GiBoo.repository;

import ChariO.GiBoo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    @Autowired
    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    //public User findOne(Long id) {return em.find(User.class, id);}

    public List<User> findOne(Long id) {
        return em.createQuery("select distinct u from User u" +
                " join fetch u.subscribeList s" +
                " where u.id = :id ", User.class)
                .setParameter("id", id)
                .getResultList();
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

}