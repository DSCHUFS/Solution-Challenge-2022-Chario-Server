package ChariO.GiBoo.service;

import ChariO.GiBoo.domain.Contents;
import ChariO.GiBoo.repository.ConRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConService {
    private final ConRepository contentRepository;

    public List<Contents> findByFac(Long id){
        return contentRepository.findByFac(id);
    }

    public Contents findOne(Long con_id){
        return contentRepository.findOne(con_id);
    }
}
