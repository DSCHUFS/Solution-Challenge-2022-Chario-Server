package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.User;
import ChariO.GiBoo.repository.UserRepository;
import ChariO.GiBoo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static ChariO.GiBoo.dto.UserDtos.*;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

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
     * @param id
     * @return User 한명 조회
     */
    @Operation(summary = "Get user by id", description = "Id로 유저 찾기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })

    @GetMapping(value="/api/users/{id}", produces="application/json;charset=UTF-8")
    public OneResult OneUser(@PathVariable("id") Long id) {
        List<User> userByID = userService.findOne(id);
        List<EveryInfoUserDto> result = userByID.stream()
                .map(u -> new EveryInfoUserDto(u))
                .collect(Collectors.toList());
        return new OneResult(result);
    }

    /**테스트용**/
    @GetMapping(value="/api/users1/{id}", produces="application/json;charset=UTF-8")
    public OneResult OneUser1(@PathVariable("id") Long id,
                              @RequestParam(value="offset", defaultValue = "0") int offset,
                              @RequestParam(value="limit", defaultValue = "100") int limit)
    {
        List<User> userByID = userService.findOne1(id, offset, limit);
        List<EveryInfoUserDto> result = userByID.stream()
                .map(u -> new EveryInfoUserDto(u))
                .collect(Collectors.toList());
        return new OneResult(result);
    }

}
