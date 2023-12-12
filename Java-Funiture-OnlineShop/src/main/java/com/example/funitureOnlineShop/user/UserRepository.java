package com.example.funitureOnlineShop.user;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<User, Integer> : CRUD를 정의하지 않아도 사용할 수 있음
// 메소드 추가 정의도 가능
public interface UserRepository extends JpaRepository<User, Long> {

}
