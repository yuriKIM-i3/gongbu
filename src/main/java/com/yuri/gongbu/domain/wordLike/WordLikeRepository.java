package com.yuri.gongbu.domain.wordLike;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WordLikeRepository extends JpaRepository<WordLike, Integer> {
    WordLike findByUserIdAndWordId(Integer userId, Integer wordId);
}