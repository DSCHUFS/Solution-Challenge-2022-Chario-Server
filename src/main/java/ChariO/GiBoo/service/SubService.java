package ChariO.GiBoo.service;

import ChariO.GiBoo.domain.Subscribe;
import ChariO.GiBoo.domain.User;
import ChariO.GiBoo.repository.SubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubService {

    private final SubRepository subRepository;

    @Transactional(readOnly = true)
    public List<Subscribe> findSubs() { return subRepository.findAll();}

    @Transactional(readOnly = true)
    public Subscribe findById(Long id) { return subRepository.findById(id); }

    @Transactional(readOnly = true)
    public List<Subscribe> findByUser(Long u_id) { return subRepository.findByUser(u_id); }

    @Transactional(readOnly = true)
    public List<Subscribe> findSubsByFacId(Long id) {return subRepository.findByFacId(id);}

    @Transactional
    public Optional<Subscribe> findSubByFacUser(Long u_id, Long f_id){
        Subscribe subscribe = subRepository.findByUserFac(u_id, f_id);
        Optional<Subscribe> result = Optional.of(subscribe);
        return result;
    }

    @Transactional()
    public void newSubscribe(Subscribe subscribe) {
        subRepository.save(subscribe);
    }

    @Transactional
    public void deleteByUserFac(Long u_id, Long f_id){
        Subscribe subscribe = subRepository.findByUserFac(u_id, f_id);
        subRepository.delete(subscribe);
    }
}
