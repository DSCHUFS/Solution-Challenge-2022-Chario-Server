package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.User;
import ChariO.GiBoo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static ChariO.GiBoo.dto.UserDtos.*;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    /**
     * User 저장
     * @return
     */
    /**
    @Operation(summary = "Save user", description = "유저리스트")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping(value="/api/user", produces= "application/json;charset=UTF-8")
    public void userSave() {
        User userSave = userService.userSave();

    }**/

    /**
     * 전체 Userlist 조회
     */
    @Operation(summary = "user list", description = "유저리스트")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
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
    @Operation(summary = "Get user by id", description = "Id로 유저 찾기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping(value = "/api/user/v1/", produces = "application/json;charset=UTF-8")
    public OneUserResponse oneUser(@RequestHeader("Authorization") Long u_id){
        User user = userService.findOne(u_id);
        OneUserResponse oneUserResponse = new OneUserResponse(user);
        return oneUserResponse;
    }


    @GetMapping(value="/api/user/", produces="application/json;charset=UTF-8")
    public EveryInfoUserDto OneUser(@RequestHeader("Authorization") Long u_id) {
        User userByID = userService.findOne(u_id);
        EveryInfoUserDto result = new EveryInfoUserDto(userByID);
        return result;
    }

    /**테스트용**/
    @GetMapping(value="/api/user1/", produces="application/json;charset=UTF-8")
    public OneResult OneUser1(@RequestHeader("Authorization") Long u_id,
                              @RequestParam(value="offset", defaultValue = "0") int offset,
                              @RequestParam(value="limit", defaultValue = "100") int limit)
    {
        List<User> userByID = userService.findOne1(u_id, offset, limit);
        List<EveryInfoUserDto> result = userByID.stream()
                .map(u -> new EveryInfoUserDto(u))
                .collect(Collectors.toList());
        return new OneResult(result);
    }
}
