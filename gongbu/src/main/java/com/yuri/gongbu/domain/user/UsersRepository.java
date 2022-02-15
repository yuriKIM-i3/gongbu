package com.yuri.gongbu.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.yuri.gongbu.domain.user.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUserEmailAndDeleteFlg(String userEmail, Integer deleteFlg);
}