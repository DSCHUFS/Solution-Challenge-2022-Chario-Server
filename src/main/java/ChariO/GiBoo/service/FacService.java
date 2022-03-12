package ChariO.GiBoo.service;

import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.domain.FacilityCategory;
import ChariO.GiBoo.repository.FacRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FacService {
    @Autowired
    private final FacRepository facRepository;

    public List<Facility> findFacs(){
        return facRepository.findAll();
    }

    public List<FacilityCategory> findFacsByCategory(Long category_id){ return facRepository.findByCategory(category_id);}

    public Facility findOne(Long facId){
        return facRepository.findOne(facId);
    }

    //For Search v1
    //public Facility findOneByName(String facName) { return facRepository.findOneByName(facName); }

    //For Search v2
    public List<Facility> findOneByNameLike(String facName) { return facRepository.findOneByNameLike(facName); }


}
