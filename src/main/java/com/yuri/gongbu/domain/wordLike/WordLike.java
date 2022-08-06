package com.yuri.gongbu.domain.wordLike;

import com.yuri.gongbu.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class WordLike extends BaseTimeEntity {

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
