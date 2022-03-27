package com.yuri.gongbu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.gongbu.domain.user.UserRepository;
import com.yuri.gongbu.global.GlobalVariable;
import com.yuri.gongbu.domain.user.User;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void signOut(Integer userId) {
        User user = userRepository.findByUserIdAndDeleteFlg(userId, GlobalVariable.FALSE);
        user.signOut();
    }
    
}