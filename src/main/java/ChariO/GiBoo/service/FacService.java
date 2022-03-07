package ChariO.GiBoo.service;

import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.domain.FacilityCategory;
import ChariO.GiBoo.repository.FacRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FacService {
    private final FacRepository facRepository;

    public List<Facility> findFacs(){
        return facRepository.findAll();
    }

    public List<FacilityCategory> findFacsByCategory(Long category_id){ return facRepository.findByCategory(category_id);}

    public Facility findOne(Long facId){
        return facRepository.findOne(facId);
    }

    //For Search
    public Facility findOneByName(String facName) { return facRepository.findOneByName(facName); }

}
