package com.yuri.gongbu.domain.user;

import com.yuri.gongbu.global.GlobalVariable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN", "admin"),
    MEMBER("ROLE_MEMBER", "member"),
    GUEST("ROLE_GUEST", "guest");

    private final String key;
    private final String title;
}
