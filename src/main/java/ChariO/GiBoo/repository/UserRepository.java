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

    public void save(User user) {em.persist(user);}

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findByUserName(String name) {
        return em.createQuery("select u from User u where u.u_username = :name", User.class )
        .setParameter("name", name).getResultList();
    }
