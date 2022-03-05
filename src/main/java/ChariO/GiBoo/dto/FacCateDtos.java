package ChariO.GiBoo.dto;

import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.domain.FacilityCategory;
import lombok.Data;

import static ChariO.GiBoo.dto.FacDtos.*;

public class FacCateDtos {

    @Data
    public static class FacCateDto {
        private String category;

        public FacCateDto(FacilityCategory fc) {
            this.category =  fc.getCategory().getCate_name();
        }
    }

    @Data
    public static class FacByCateDto {
        private FacDto facility;

        public FacByCateDto(FacilityCategory fc){
            this.facility = new FacDto(fc.getFacility());
        }
    }
}
