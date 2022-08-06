package com.yuri.gongbu.config.auth.dto;

import com.yuri.gongbu.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private Integer userId;
    private String userName;
    private String userEmail;

    public SessionUser(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
    }
}
