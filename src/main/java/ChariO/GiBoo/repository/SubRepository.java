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


    /**
     * 전체조회
     * @return 모든 구독 리스트
     */
    public List<Subscribe> findAll() {
        return em.createQuery("select s from Subscribe s", Subscribe.class)
                .getResultList();
    }


    /**
     * @param id
     * @return 일치하는 ID의 구독 리스트
     * 별 필요 없음
     */
    public Subscribe findById(Long id) {
        return em.find(Subscribe.class, id);
    }


    /**
     * @param user
     * @return 일치하는 user의 구독 기관 리스트
     */
    public Subscribe findByUser(User user) {
        return em.find(Subscribe.class, user);
    }

}
