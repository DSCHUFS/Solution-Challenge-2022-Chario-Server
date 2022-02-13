package ChariO.GiBoo.repository;

import ChariO.GiBoo.domain.Subscribe;
import ChariO.GiBoo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    public User findById(Long id) {return em.find(User.class, id);}

    public User findByUserName(String Username) {return em.find(User.class, Username);}

}
