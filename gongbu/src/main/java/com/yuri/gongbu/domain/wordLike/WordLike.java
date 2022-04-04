package com.yuri.gongbu.domain.wordLike;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.yuri.gongbu.domain.BaseTimeEntity;

@Getter
@NoArgsConstructor
@Entity
public class WordLike extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wordLikeId;

    @Column
    private Integer userId;

    @Column
    private Integer wordId;

    @Builder
    public WordLike(Integer userId, Integer wordId) {
        this.userId = userId;
        this.wordId = wordId;
    }
}