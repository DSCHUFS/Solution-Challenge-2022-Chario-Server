package ChariO.GiBoo.service;

import ChariO.GiBoo.domain.FacilityCategory;
import ChariO.GiBoo.repository.FacCateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacCateService {

    @Autowired
    private final FacCateRepository facCateRepository;

    @Transactional(readOnly = true)
    public List<FacilityCategory> findFacCates() { return facCateRepository.findAll();}

    @Transactional(readOnly = true)
    public FacilityCategory findById(Long id) { return facCateRepository.findById(id);}

    @Transactional(readOnly = true)
    public List<FacilityCategory> findCatesByFacId(Long id) { return facCateRepository.findByFacId(id);}

}
