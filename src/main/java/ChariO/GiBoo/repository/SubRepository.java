package ChariO.GiBoo.repository;

import ChariO.GiBoo.domain.Subscribe;
import ChariO.GiBoo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SubRepository {

    private final EntityManager em;

    //전체조회
    public List<Subscribe> findAll() {
        return em.createQuery("select s from Subscribe s", Subscribe.class)
                .getResultList();
    }

    //Id 기준 조회
    public Subscribe findById(Long id) {
        return em.find(Subscribe.class, id);
    }

    //User 기준 조회
    public Subscribe findByUser(User user) {
        return em.find(Subscribe.class, user);
    }

}
