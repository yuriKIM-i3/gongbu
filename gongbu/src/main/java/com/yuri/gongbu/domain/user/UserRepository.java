package com.yuri.gongbu.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.yuri.gongbu.domain.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    //登録済みのユーザーかを確認
    Optional<User> findByUserEmailAndDeleteFlg(String userEmail, Integer deleteFlg);
}