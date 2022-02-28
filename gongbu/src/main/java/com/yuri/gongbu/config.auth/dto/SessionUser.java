package com.yuri.gongbu.config.auth.dto;

import lombok.Getter;

import java.io.Serializable;

import com.yuri.gongbu.domain.user.User;

@Getter
public class SessionUser implements Serializable {
    private String userName;
    private String userEmail;

    public SessionUser(User user) {
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
    }
}