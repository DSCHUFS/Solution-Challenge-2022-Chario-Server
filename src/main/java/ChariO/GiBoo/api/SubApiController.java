package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.Subscribe;
import ChariO.GiBoo.service.FacService;
import ChariO.GiBoo.service.SubService;
import ChariO.GiBoo.service.UserService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static ChariO.GiBoo.dto.SubscribeDtos.*;

@RestController
@RequiredArgsConstructor
public class SubApiController {

    private final SubService subscribeService;
    private final UserService userService;
    private final FacService facService;

/*  전체 구독 DB 조회 -> 불필요하므로 주석 처리
    @Operation(summary = "subscribe list", description = "구독리스트")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping(value = "/api/subscribes", produces = "application/json;charset=UTF-8")
    public SubResult subscribesAll(){
        List<Subscribe> findSubs = subscribeService.findSubs();
        List<SubDto> collect = findSubs.stream()
                .map(s -> new SubDto(s)) //Change to DTO
                .collect(Collectors.toList()); //Collector -> List convert
        return new SubResult(collect.size(), collect);
    }*/

    @Operation(summary = "subscribe list per user", description = "구독리스트")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/api/subscribe", produces = "application/json;charset=UTF-8")
    public UserSubResponse userSubscribe(@RequestHeader("Authorization") String u_uuid){
        Long u_id = userService.findByUuid(u_uuid);
        List<Subscribe> subscribeList = subscribeService.findByUser(u_id);
        List<UserSubDto> collect = subscribeList.stream()
                .map(s -> new UserSubDto(s))
                .collect(Collectors.toList());
        return new UserSubResponse(collect.size(), collect);
    }

    /**
     * 신규 사용자의 구독(좋아요)기관 추가.
     */
    @Operation(summary = "Get subscribe list from new user", description = "신규 사용자의 구독 리스트 추가")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/api/subscribe", produces = "application/json;charset=UTF-8")
    public SubscribeListPostResponse subscribeListPost(@RequestHeader("Authorization") String u_uuid,
                                                       @RequestBody SubscribeListPostRequest request){

        Long u_id = userService.findByUuid(u_uuid);
        List<Long> facList = request.getFac_id();

        for(Long tmp:facList) {
            Subscribe subscribe = new Subscribe();
            subscribe.setUser(userService.findOne(u_id));
            subscribe.setFacility(facService.findOne(tmp));
            subscribeService.newSubscribe(subscribe);
        }

        String status = "정상적으로 저장 되었습니다.";
        return new SubscribeListPostResponse(status);
    }

    /**
     * 현재 사용자가 신규 좋아요를 누름. + 해당 기관의 좋아요 개수
     */
    @Operation(summary = "user post subscribe", description = "사용자가 해당 기관을 새로 구독하는 경우")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/api/subscribe/{fac_id}", produces = "application/json;charset=UTF-8")
    public SubscribePostDeleteResponse subscribePost(@PathVariable("fac_id") Long f_id,
                                                     @RequestHeader("Authorization") String u_uuid){
        Long u_id = userService.findByUuid(u_uuid);
        List<Subscribe> subsByFacId = subscribeService.findSubsByFacId(f_id);
        long count = subsByFacId.stream().count();
        if (subsByFacId.stream().anyMatch(s -> s.getUser().getId() == u_id)){
            String status = "이미 좋아요를 하고 있습니다.";
            return new SubscribePostDeleteResponse(count, status);
        }
        Subscribe subscribe = new Subscribe();
        subscribe.setUser(userService.findOne(u_id));
        subscribe.setFacility(facService.findOne(f_id));
        subscribeService.newSubscribe(subscribe);
        return new SubscribePostDeleteResponse(count + 1, "정상적으로 저장되었습니다. ");
    }

    /**
     * 현재 사용자가 좋아요 취소 + 해당 기관의 좋아요 수
     */
    @Operation(summary = "user unsubscribe", description = "사용자가 해당 기관을 구독 취소하는 경우")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping(value = "/api/subscribe/{fac_id}", produces = "application/json;charset=UTF-8")
    public SubscribePostDeleteResponse subscribeDelete(@PathVariable("fac_id") Long f_id,
                                                       @RequestHeader("Authorization") String u_uuid){
        Long u_id = userService.findByUuid(u_uuid);
        List<Subscribe> subsByFacId = subscribeService.findSubsByFacId(f_id);
        if (subsByFacId.stream().anyMatch(s -> s.getUser().getId() == u_id)){
            subscribeService.deleteByUserFac(u_id, f_id);
            String status = "정상적으로 삭제되었습니다.";
            long count = subsByFacId.size();
            return new SubscribePostDeleteResponse(count, status);
        }
        String status = "존재하지 않는 구독입니다.";
        long count = subsByFacId.stream().count();
        return new SubscribePostDeleteResponse(count, status);
    }
}
