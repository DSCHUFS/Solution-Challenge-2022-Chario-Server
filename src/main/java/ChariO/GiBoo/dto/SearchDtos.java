package ChariO.GiBoo.dto;

import ChariO.GiBoo.domain.Facility;
import lombok.AllArgsConstructor;
import lombok.Data;

public class SearchDtos {

    @Data
    @AllArgsConstructor
    public static class SearchGetRequest{
        private String keyword;
    }

    @Data
    @AllArgsConstructor
    public static class SearchGetResponse{
        private Long f_id;
        private String f_name;
        private String f_logo;
        private String f_intro;

        public SearchGetResponse(Facility facility){
            this.f_id = facility.getId();
            this.f_name = facility.getF_name();
            this.f_logo = facility.getF_logo();
            this.f_intro = facility.getF_intro();
        }
    }

    @Data
    @AllArgsConstructor
    public static class SearchGetResponse2<T> {
        private int count;
        private T data;
    }
}
