package com.yuri.gongbu.config.auth.dto;

import com.yuri.gongbu.domain.user.Role;
import com.yuri.gongbu.domain.user.User;
import com.yuri.gongbu.global.GlobalVariable;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String userName;
    private String userEmail;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String userName, String userEmail) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return "line".equals(registrationId)  ? ofLine("userId", attributes) : ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .userName((String) attributes.get("name"))
                .userEmail((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofLine(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .userName((String) attributes.get("displayName"))
                .userEmail((String) attributes.get("userId"))//取得したユーザー情報の中でuserIdは変化しない情報なのでuserEmailに保存
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .userName(userName)
                .userEmail(userEmail)
                .userRole(Role.MEMBER)
                .deleteFlg(GlobalVariable.FALSE)
                .build();
    }
}
