package ChariO.GiBoo.service;

import ChariO.GiBoo.domain.Contents;
import ChariO.GiBoo.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepository;

    public List<Contents> findByFac(Long id){
        return contentRepository.findByFac(id);
    }
}
