package ChariO.GiBoo.service;

import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.domain.Subscribe;
import ChariO.GiBoo.domain.User;
import ChariO.GiBoo.repository.SubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubService {

    private final SubRepository subRepository;

    @Transactional(readOnly = true)
    public List<Subscribe> findSub() { return subRepository.findAll();}

    @Transactional(readOnly = true)
    public Subscribe findById(Long id) { return subRepository.findById(id); }

    @Transactional(readOnly = true)
    public Subscribe findByUser(User user) { return subRepository.findByUser(user); }
}
