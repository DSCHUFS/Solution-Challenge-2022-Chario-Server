package ChariO.GiBoo.dto;

import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.domain.FacilityCategory;
import lombok.Data;

import java.util.List;

import static ChariO.GiBoo.dto.CateDtos.*;
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

    @Data
    public static class GetCateResponse {
        private Long count;
        private List<CateDto> categories;

        public GetCateResponse(List<CateDto> categories) {
            this.count = categories.stream().count();
            this.categories = categories;
        }
    }
}
