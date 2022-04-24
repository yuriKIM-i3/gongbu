package com.yuri.gongbu.domain.words;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import com.yuri.gongbu.domain.BaseTimeEntity;
import com.yuri.gongbu.global.GlobalVariable;
import com.yuri.gongbu.domain.user.User;

@Getter
@NoArgsConstructor
@Entity
public class Words extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wordId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(length = 50, nullable = false)
    private String wordName;

    @Column(length = 100, nullable = false)
    private String wordPronunciation;
    
    @Column(length = 255, nullable = false)
    private String wordMeaning;

    @Column(length = 500)
    private String wordExample;

    @Column 
    private Integer wordHits;

    @Column 
    private Integer wordLike;

    @Column 
    private Integer deleteFlg;

    @ManyToOne
    @JoinColumn(name="user_id", insertable=false, updatable=false)
    private User user;

    @Builder
    public Words(Integer userId, String wordName, String wordPronunciation, String wordMeaning, String wordExample, 
    Integer wordHits, Integer wordLike, Integer deleteFlg) {
        this.userId = userId;
        this.wordName = wordName;
        this.wordPronunciation = wordPronunciation;
        this.wordMeaning = wordMeaning;
        this.wordExample = wordExample;
        this.wordHits = wordHits;
        this.wordLike = wordLike;
        this.deleteFlg = deleteFlg;
    }

    public void update(String wordName, String wordPronunciation, String wordMeaning, String wordExample) {
        this.wordName = wordName;
        this.wordPronunciation = wordPronunciation;
        this.wordMeaning = wordMeaning;
        this.wordExample = wordExample;
    }

    public void delete() {
        this.deleteFlg = GlobalVariable.TRUE;
    }

    public void countUpHits() {
        this.wordHits += 1;
    }

    public void countUpLike() {
        this.wordLike += 1;
    }
}