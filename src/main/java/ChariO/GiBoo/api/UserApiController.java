package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.User;
import ChariO.GiBoo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ChariO.GiBoo.dto.UserDtos.*;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    /**
     * @Header u_id
     * @return status, 저장된 정보
     */

    @Operation(summary = "사용자 저장", description = "전달받은 파라미터로 사용자  저장")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping(value="/api/user", produces= "application/json;charset=UTF-8")
    public UserPostResponse userPost(@RequestHeader("Authorization") String u_uuid, @Valid @RequestBody UserPostRequest request){
        userService.userPost(request.getU_username(), request.getU_name(),
                request.getU_email(), request.getU_phone(), request.getU_birth(), u_uuid);
        String status = "회원 등록이 완료되었습니다.";
        UserPostResponse userPostResponse = new UserPostResponse(status);
        return userPostResponse;
    }

    /**
     * 전체 Userlist 조회
     */
    @Operation(summary = "사용 X -> 사용자 전체 조회", description = "서버 개발 테스트 용도. 추후 삭제 예정")
    @GetMapping(value="/api/users", produces = "application/json;charset=UTF-8")
    public UserResult userAll(){
        List<User> findUsers = userService.findUsers();
        List<UserDto> collect = findUsers.stream()
                .map(user -> new UserDto(user))
                .collect(Collectors.toList());
        return new UserResult(collect.size(), collect);
    }

    /**
     * @Header u_id
     * @return User 한명 조회
     */
    @Operation(summary = "사용자 단일 조회", description = "사용자의 username, name, email, phone, birth 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/api/user", produces = "application/json;charset=UTF-8")
    public OneUserResponse oneUser(@RequestHeader("Authorization") String u_uuid){
        Long u_id = userService.findByUuid(u_uuid);
        User user = userService.findOne(u_id);
        OneUserResponse oneUserResponse = new OneUserResponse(user);
        return oneUserResponse;
    }

    /**테스트용**/
    @Operation(summary = "사용 X -> 단일 사용자 (+구독 기관 + 카테고리) 조회", description = "사용자 정보, 사용자의 구독 기관, 해당 구독 기관의 카테고리 함께 조회 -> 추후 삭제 예정")
    @GetMapping(value="/api/user1", produces="application/json;charset=UTF-8")
    public OneResult OneUser1(@RequestHeader("Authorization") String u_uuid,
                              @RequestParam(value="offset", defaultValue = "0") int offset,
                              @RequestParam(value="limit", defaultValue = "100") int limit) {
        Long u_id = userService.findByUuid(u_uuid);
        List<User> userByID = userService.findOne1(u_id, offset, limit);
        List<EveryInfoUserDto> result = userByID.stream()
                .map(u -> new EveryInfoUserDto(u))
                .collect(Collectors.toList());
        return new OneResult(result);
    }

    @Operation(summary = "사용자 정보 수정", description = "본인만 접근 가능, name, email, phone 필드가 모두 있어야 request 가능")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "빈 필드가 있습니다."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "존재하는 회원이 없습니다.")
    })
    @PutMapping(value = "/api/user", produces="application/json;charset=UTF-8")
    public UserPutResponse userPut(@RequestHeader("Authorization") String u_uuid,
                                   @Valid @RequestBody UserPutRequest request) {
        Long u_id = userService.findByUuid(u_uuid);
        User user = userService.updateOne(u_id, request.getName(), request.getEmail(), request.getPhone());
        String status = "변경 사항이 저장되었습니다.";
        return new UserPutResponse(user, status);
    }

    /**
     * @Header u_id
     * @return status
     */
    @Operation(summary = "유저 삭제", description = "정상적으로 삭제 완료 시, '회원 정보가 삭제되었습니다' 확인 가능.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "존재하는 회원이 없습니다.")
    })
    @DeleteMapping(value = "/api/user", produces="application/json;charset=UTF-8")
    public UserDeleteResponse userDelete(@RequestHeader("Authorization") String u_uuid){
        Long u_id = userService.findByUuid(u_uuid);
        userService.deleteOne(u_id);
        String status = "회원 정보가 삭제되었습니다.";
        return new UserDeleteResponse(status);
    }
}
