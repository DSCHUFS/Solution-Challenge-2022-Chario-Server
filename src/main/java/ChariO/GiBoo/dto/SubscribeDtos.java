package ChariO.GiBoo.dto;

import ChariO.GiBoo.domain.FacilityCategory;
import ChariO.GiBoo.domain.Subscribe;
import ChariO.GiBoo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

import static ChariO.GiBoo.dto.FacCateDtos.*;

public class SubscribeDtos {

    /**
     * 결과 반환 Format
     * @param <T>
     */
    @Data
    @AllArgsConstructor
    public static class SubResult<T> {
        private int count;
        private T data;
    }


    /**
     * Data 전송 전용 Obj
     */
    @Data
    public static class SubDto {
        private Long id;
        private User user;
        private String f_name;
        private String f_logo;
        private String f_ars;
        private String f_phone;
        private String f_home;
        private String f_pay;
        private int f_minimal;
        private String f_intro;
        private List<FacilityCategory> facilityCategoryList;

        public SubDto(Subscribe s){
            this.id = s.getId();

            //User
            this.user = s.getUser();

            //Facility
            this.f_name = s.getFacility().getF_name();
            this.f_logo = s.getFacility().getF_logo();
            this.f_phone = s.getFacility().getF_phone();
            this.f_home = s.getFacility().getF_home();
            this.f_pay = s.getFacility().getF_pay();
            this.f_minimal = s.getFacility().getF_minimal();
            this.f_intro = s.getFacility().getF_intro();
            this.facilityCategoryList = s.getFacility().getFacilityCategoryList();
        }
    }

    @Data
    public static class UserSubDto {
        private Long id;
        private String f_name;
        private String f_logo;
        private String f_intro;
        private List<FacCateDto> facilityCategory;

        public UserSubDto(Subscribe s){
            this.id = s.getId();

            //Facility
            this.f_name = s.getFacility().getF_name();
            this.f_logo = s.getFacility().getF_logo();
            this.f_intro = s.getFacility().getF_intro();
            this.facilityCategory = s.getFacility().getFacilityCategoryList()
                    .stream()
                    .map(facilityCategory -> new FacCateDto(facilityCategory))
                    .collect(Collectors.toList());
        }
    }


    @Data
    @AllArgsConstructor
    public static class GetSubResponse {
        private String id;
    }

    @Data
    public static class subscribePostDeleteResponse {
        Long count;
        String status;

        public subscribePostDeleteResponse(Long cnt, String status){
            this.count = cnt;
            this.status = status;
        }
    }
}
