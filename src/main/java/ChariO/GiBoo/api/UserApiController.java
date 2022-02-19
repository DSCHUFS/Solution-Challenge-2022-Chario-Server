package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.User;
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
public class UserApiController {

    private final UserService userService;

    /**
     * Swagger 명세
     */
    @Operation(summary = "user list", description = "유저리스트")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })

    @GetMapping(value="/api/users", produces = "application/json;charset=UTF-8")
    public UserResult userAllv1(){
        List<User> findUsers = userService.findUsers();
        List<UserDto> collect = findUsers.stream()
                .map(user -> new UserDto(user))
                .collect(Collectors.toList());
        return new UserResult(collect.size(), collect);
    }

    @Operation(summary = "Get user by id", description = "Id로 유저 찾기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })

    @GetMapping(value="/api/users/{id}", produces="application/json;charset=UTF-8")
    public OneUserResult OneUser(@PathVariable("id") Long id) {
        User findOneUser = userService.findOne(id);
        return new OneUserResult(new UserDto(findOneUser));
    }
    @Data
    @AllArgsConstructor
    static class UserResult<T>{
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class OneUserResult<T>{
        private T data;
    }


    @Data
    static class UserDto{
        private String u_username;
        private String u_name;
        private String u_email;
        private String u_phone;
        private String u_birth; //Date 변경예정

        public UserDto(User user){
            this.u_username = user.getU_username();
            this.u_name = user.getU_name();
            this.u_email = user.getU_email();
            this.u_phone = user.getU_phone();
            this.u_birth = user.getU_birth();

        }
    }

    @Data
    @AllArgsConstructor
    static class GetUserResponse {
        private String id;
    }
}
