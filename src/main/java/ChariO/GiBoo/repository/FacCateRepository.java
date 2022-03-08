package ChariO.GiBoo.repository;

import ChariO.GiBoo.domain.Category;
import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.domain.FacilityCategory;
import ChariO.GiBoo.dto.CateDtos;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static ChariO.GiBoo.dto.CateDtos.*;

@Repository
@RequiredArgsConstructor
public class FacCateRepository {

    @Autowired
    private final EntityManager em;

    public List<FacilityCategory> findAll() {
        return em.createQuery("select fc from FacilityCategory fc", FacilityCategory.class)
                .getResultList();
    }

    public FacilityCategory findById(Long id) {
        return em.find(FacilityCategory.class, id);
    }

    public List<FacilityCategory> findByFacId(Long id){
        return em.createQuery("select fc from FacilityCategory fc" +
                " join fetch fc.facility f" +
                " join fetch fc.category c" +
                " where fc.facility.id = :id ", FacilityCategory.class)
                .setParameter("id", id)
                .getResultList();
    }
    
    public List<CateDto> findCategoryAll(){
        List<Category> categories = em.createQuery("select c from Category c", Category.class)
                .getResultList();
        List<CateDto> result = categories.stream().map(c -> new CateDto(c)).collect(Collectors.toList());
        return result;
    }
}
