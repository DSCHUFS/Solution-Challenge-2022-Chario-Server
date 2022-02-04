package ChariO.GiBoo.service;

import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.repository.FacRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacService {
    private final FacRepository facRepository;

    @Transactional(readOnly = true)
    public List<Facility> findFacs(){
        return facRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Facility findOne(Long facId){
        return facRepository.findOne(facId);
    }
}
