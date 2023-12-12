package com.example.FunitureOnlineShop.user;

import com.example.FunitureOnlineShop.core.security.JwtTokenProvider;
import com.example.FunitureOnlineShop.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<Object> join(@RequestBody @Valid UserRequest.JoinDto joinDto, Error error){
        userService.join(joinDto);

        return ResponseEntity.ok(ApiUtils.success(null));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserRequest.JoinDto joinDto, HttpServletResponse res, Error error){
        String jwt = userService.login(joinDto, res);
        return ResponseEntity.ok().header(JwtTokenProvider.HEADER, jwt)
                .body(ApiUtils.success(null));
    }
}
