package com.yuri.gongbu.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

import java.sql.Timestamp;

import com.yuri.gongbu.domain.BaseTimeEntity;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(length = 50, nullable = false)
    private String userName;

    @Column(length = 50, nullable = false)
    private String userEmail;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Role userRole;

    @Column 
    private Integer deleteFlg;

    @Builder
    public Users(String userName, String userEmail, Role userRole, Integer deleteFlg) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.deleteFlg = deleteFlg;
    }
}