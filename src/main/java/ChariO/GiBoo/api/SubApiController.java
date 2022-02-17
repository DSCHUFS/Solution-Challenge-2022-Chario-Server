package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.domain.FacilityCategory;
import ChariO.GiBoo.domain.Subscribe;
import ChariO.GiBoo.domain.User;
import ChariO.GiBoo.service.SubService;
import ChariO.GiBoo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SubApiController {

    private final SubService subscribeService;
    private final UserService userService;

    /**
     * Swagger 명세
     */
    @Operation(summary = "subscribe list", description = "구독리스트")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })

    @GetMapping(value = "/api/subscribes", produces = "application/json;charset=UTF-8")
    public SubResult subscribesAllv1(){
        List<Subscribe> findSubs = subscribeService.findSubs();
        List<SubDto> collect = findSubs.stream()
                .map(s -> new SubDto(s)) //Change to DTO
                .collect(Collectors.toList()); //Collector -> List convert
        return new SubResult(collect.size(), collect);
    }

    /**
     * 결과 반환 Format
     * @param <T>
     */
    @Data
    @AllArgsConstructor
    static class SubResult<T> {
        private int count;
        private T data;
    }


    /**
     * Data 전송 전용 Obj
     */
    @Data
    static class SubDto {
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


    /**
     * Subscribe 의 ID가 Response 값
     */
    @Data
    @AllArgsConstructor
    static class GetSubResponse {
        private String id;
    }
}
