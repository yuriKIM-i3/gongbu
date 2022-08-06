package com.yuri.gongbu.service;

import com.yuri.gongbu.domain.user.User;
import com.yuri.gongbu.domain.user.UserRepository;
import com.yuri.gongbu.global.GlobalVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void withdrawal(Integer userId) {
        User user = userRepository.findByUserIdAndDeleteFlg(userId, GlobalVariable.FALSE);
        user.withdrawal();
    }

}
