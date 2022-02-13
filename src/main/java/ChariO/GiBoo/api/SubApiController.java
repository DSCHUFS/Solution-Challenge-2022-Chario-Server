package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.Facility;
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

import static ChariO.GiBoo.api.UserApiController.*;


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
    public SubResult subscribes(){
        List<Subscribe> findSubs = subscribeService.findSubs();
        List<SubDto> collect = findSubs.stream()
                .map(s -> new SubDto(s))
                .collect(Collectors.toList()); //Collector -> List convert
        return new SubResult(collect.size());
    }

    /**
     * 결과 반환 Format
     * @param <T>
     */
    @Data
    @AllArgsConstructor
    static class SubResult<T> {
        private int count;
        //private T data;
    }


    /**
     * Data 전송 전용 Obj
     */
    @Data
    static class SubDto {
        private Long id;
        private User user; //User객체 전체를 가져옴..ID만 가져 올 수 없는지
        private Facility facility;

        public SubDto(Subscribe s){
            this.id = s.getId();
            this.user = s.getUser();
            this.facility = s.getFacility();
        }
    } //Query 가 너무 많이 조회되면서 Stackoverflow 발생;;

    /**
     * Subscribe 의 ID가 Response 값
     */
    @Data
    @AllArgsConstructor
    static class GetSubResponse {
        private String id;
    }

/**
    @GetMapping(value = "/api/subscribes/{id}", produces = "application/json;charset=UTF-8")
    public SubResult findsubscribes(@PathVariable("id") Long id){
        Subscribe findById = subscribeService.findById(id);
        List<UserDto> collect = userService.findById(id).stream().map(u -> new SubDto(u))
                .collect(Collectors.toList()); //Collector -> List convert
        return new SubResult(collect.size(), collect);
    }

    static class UserSubDto extends SubDto {
        private List<User> UserList;

        public UserSubDto(Subscribe subscribe){
            super(subscribe);
            this.UserList = subscribe.getUser();
            System.out.println("User = " + subscribe);
        }
    }

    @Data
    @AllArgsConstructor
    static class UserSubResponse<T>{
        private SubDto subDto;
        private T UserList;
    }
    **/
}
