package com.yuri.gongbu.domain.words;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface WordsRepository extends JpaRepository<Words, Integer> {
    Page<Words> findByDeleteFlg(Integer deleteFlg, Pageable pageable);
    Page<Words> findByDeleteFlgAndWordNameContaining(Integer deleteFlg, String wordName, Pageable pageable);
    Optional<Words> findByWordIdAndDeleteFlg(Integer wordId, Integer deleteFlg);
    Optional<Words> findByWordId(Integer wordId);
}