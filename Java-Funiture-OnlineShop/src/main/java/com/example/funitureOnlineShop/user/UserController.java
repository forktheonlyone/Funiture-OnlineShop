package com.example.funitureOnlineShop.user;

import com.example.funitureOnlineShop.core.security.CustomUserDetails;
import com.example.funitureOnlineShop.core.security.JwtTokenProvider;
import com.example.funitureOnlineShop.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 유저 회원가입
    @PostMapping("/join")
    public ResponseEntity<Object> join(@RequestBody @Valid UserRequest.JoinDto joinDto, Error error){
        userService.join(joinDto);

        return ResponseEntity.ok(ApiUtils.success(null));
    }
    // 관리자 회원가입
    @PostMapping("/joinAdmin")
    public ResponseEntity<Object> joinAdmin(@RequestBody @Valid UserRequest.JoinAdminDto joinDto, Error error){
        userService.joinAdmin(joinDto);

        return ResponseEntity.ok(ApiUtils.success(null));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserRequest.JoinDto joinDto, HttpServletResponse res, Error error){
        String jwt = userService.login(joinDto, res);
        return ResponseEntity.ok().header(JwtTokenProvider.HEADER, jwt)
                .body(ApiUtils.success(null));
    }

    // 로그아웃
    @PostMapping("/logout")
    public String logout(@AuthenticationPrincipal CustomUserDetails customUserDetails, HttpServletResponse res, Error error){
        return userService.logout(customUserDetails.getUser().getId(), res);
    }
    // 회원정보확인
    @GetMapping("/info")
    public ResponseEntity<UserResponse.UserDTO> getUserInfo(@RequestBody @Valid UserRequest.UserInfoDto userInfoDto) {
        UserResponse.UserDTO userDTO = userService.getUserInfo(userInfoDto);
        return ResponseEntity.ok(userDTO);
    }
    // 회원탈퇴
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("탈퇴가 성공적으로 이루어졌습니다.. ㅠ");
    }

}
