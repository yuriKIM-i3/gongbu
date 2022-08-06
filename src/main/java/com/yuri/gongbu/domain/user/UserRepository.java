package com.yuri.gongbu.domain.user;

import com.yuri.gongbu.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    //登録済みのユーザーかを確認
    Optional<User> findByUserEmailAndDeleteFlg(String userEmail, Integer deleteFlg);

    User findByUserIdAndDeleteFlg(Integer userId, Integer deleteFlg);

    User findFirstByOrderByUserIdDesc();
}
