package ChariO.GiBoo.dto;

import ChariO.GiBoo.domain.Subscribe;
import ChariO.GiBoo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

import static ChariO.GiBoo.dto.SubscribeDtos.*;

public class UserDtos {

    @Data
    @AllArgsConstructor
    public static class UserResult<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    public static class OneResult<T> {
        private T data;
    }

    @Data
    public static class UserDto{
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
    public static class EveryInfoUserDto {
        private String u_username;
        private String u_name;
        private String u_email;
        private String u_phone;
        private String u_birth;
        private List<UserSubDto> subscribes;

        public EveryInfoUserDto(User u) {
            this.u_username = u.getU_username();
            this.u_name = u.getU_name();
            this.u_email = u.getU_email();
            this.u_phone = u.getU_phone();
            this.u_birth = u.getU_birth();
            this.subscribes = u.getSubscribeList().stream()
                    .map(subscribe -> new UserSubDto(subscribe))
                    .collect(Collectors.toList());
        }
    }

    @Data
    @AllArgsConstructor
    public static class GetUserResponse {
        private String id;
    }

}
