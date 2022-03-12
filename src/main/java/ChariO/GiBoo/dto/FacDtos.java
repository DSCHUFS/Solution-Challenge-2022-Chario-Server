package ChariO.GiBoo.dto;

import ChariO.GiBoo.domain.Contents;
import ChariO.GiBoo.domain.Facility;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public class FacDtos {
    @Data
    @AllArgsConstructor
    public static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    public static class FacDto {
        private Long f_id;
        private String f_name;
        private String f_intro;
        private int f_minimal;
        private String f_home;
        private String f_ars;
        private String f_phone;
        private String f_pay;
        private String f_logo;

        public FacDto(Facility f){
            this.f_id = f.getId();
            this.f_name = f.getF_name();
            this.f_intro = f.getF_intro();
            this.f_minimal = f.getF_minimal();
            this.f_home = f.getF_home();
            this.f_ars = f.getF_ars();
            this.f_phone = f.getF_phone();
            this.f_pay = f.getF_pay();
            this.f_logo = f.getF_logo();
        }
    }

    public static class DetailFacDto extends FacDto{
        private List<Contents> contentsList;

        public DetailFacDto(Facility facility){
            super(facility);
            this.contentsList = facility.getContentsList();
            System.out.println("facility = " + facility);
        }
    }

    @Data
    @AllArgsConstructor
    public static class DetailFacResponse<T>{
        private FacDto facDto;
        private T contentsList;
    }

    @Data
    @AllArgsConstructor
    public static class GetFacResponse {
        private String f_name;
    }
}
