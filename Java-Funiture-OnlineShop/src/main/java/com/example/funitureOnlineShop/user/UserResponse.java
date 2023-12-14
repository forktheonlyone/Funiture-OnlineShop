package com.example.funitureOnlineShop.user;

import lombok.Getter;
import lombok.Setter;

public class UserResponse {
    @Getter
    @Setter
    public static class UserDTO {
        private Long id;
        private String email;
        private String username;

        public static UserDTO fromEntity(User user) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setUsername(user.getUsername());

            return userDTO;
        }
    }
}
