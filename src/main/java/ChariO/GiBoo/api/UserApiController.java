package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    @Data
    static class UserDto{
        private String u_username;
        private String u_name;
        private String u_email;
        private String u_phone;
        private int u_birth; //Date 변경예정

        public UserDto(User user){
            this.u_username = user.getU_username();
            this.u_name = user.getU_name();
            this.u_email = user.getU_email();
            this.u_phone = user.getU_phone();
            this.u_birth = user.getU_birth();

        }
    }
}
