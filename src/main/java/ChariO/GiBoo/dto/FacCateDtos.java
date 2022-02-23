package ChariO.GiBoo.dto;

import ChariO.GiBoo.domain.FacilityCategory;
import lombok.Data;

public class FacCateDtos {

    @Data
    public static class FacCateDto {
        private String category;

        public FacCateDto(FacilityCategory fc) {
            this.category =  fc.getCategory().getCate_name();
        }
    }
}
