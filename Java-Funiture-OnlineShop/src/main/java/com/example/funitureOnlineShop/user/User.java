package com.example.funitureOnlineShop.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_tb")
// 가입한 회원
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이메일 (아이디)
    @Column(length = 100, nullable = false, unique = true)
    private String email;

    // 비밀번호
    @Column(length = 255, nullable = false)
    private String password;

    // 회원명
    @Column(length = 45, nullable = false)
    private String username;

    // 전화번호
    @Column(length = 11, nullable = false)
    private String phoneNumber;

    // 주소
    @Column(length = 255, nullable = false)
    private String address;

    // 권한
    @Column(length = 30)
    @Convert(converter = StringArrayConverter.class)
    private List<String> roles = new ArrayList<>();

    // 적립 포인트
    @Column(length = 255, nullable = false)
    private Long point;

    // 갱신 토큰
    @Column
    private String refreshToken;

    public void setRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

    @Builder
    public User(Long id, String email, String password, String username, String phoneNumber, String address, List<String> roles, Long point, String refreshToken) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.roles = roles;
        this.point = point;
        this.refreshToken = refreshToken;
    }

    // 회원 정보 출력
    public void output(){
        System.out.println("id : " + id);
        System.out.println("email : " + email);
        System.out.println("password : " + password);
        System.out.println("username : " + username);
        System.out.println("phoneNumber : " + phoneNumber);
        System.out.println("address : " + address);
        System.out.println("roles : " + roles);
        System.out.println("point : " + point);
        System.out.println("refreshToken : " + refreshToken);
    }
}
