package com.example.FunitureOnlineShop.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class UserResponse {
    @AllArgsConstructor
    @Setter
    @Getter
    public static class UserDto{

        // 이메일
        @NotEmpty
        private String email;

        // 이름
        @NotEmpty
        private String username;

        // 전화번호
        @NotEmpty
        private String phoneNumber;

        // 배송 주소
        @NotEmpty
        private String address;

        // 권한
        @NotEmpty
        private List<String> roles;

        // 적립 포인트
        @NotEmpty
        private Long point;

        @NotEmpty
        private String refreshToken;

        // DTO를 Entity로
        public User toEntity(){
            return User.builder()
                    .email(email)
                    .username(username)
                    .phoneNumber(phoneNumber)
                    .address(address)
                    .roles(roles)
                    .point(point)
                    .refreshToken(refreshToken)
                    .build();
        }

        // Entity를 DTO로
        public static UserDto toUserDto(User user){
            return new UserDto(
                    user.getEmail(),
                    user.getUsername(),
                    user.getPhoneNumber(),
                    user.getAddress(),
                    user.getRoles(),
                    user.getPoint(),
                    user.getRefreshToken());
        }
    }
}
