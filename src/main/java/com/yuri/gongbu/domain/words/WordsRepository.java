package com.yuri.gongbu.domain.words;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WordsRepository extends JpaRepository<Words, Integer> {
    Page<Words> findByDeleteFlgOrderByWordIdDesc(Integer deleteFlg, Pageable pageable);

    Page<Words> findByDeleteFlg(Integer deleteFlg, Pageable pageable);

    Page<Words> findByDeleteFlgAndWordNameContaining(Integer deleteFlg, String wordName, Pageable pageable);

    Optional<Words> findByWordIdAndDeleteFlg(Integer wordId, Integer deleteFlg);

    Optional<Words> findByWordId(Integer wordId);

    List<Words> findTop10ByDeleteFlgOrderByWordLikeDesc(Integer deleteFlg);

    List<Words> findTop10ByDeleteFlgOrderByWordHitsDesc(Integer deleteFlg);

    List<Words> findByDeleteFlg(Integer deleteFlg);

    Words findTop1ByDeleteFlgOrderByWordIdAsc(Integer deleteFlg);

    Page<Words> findByUserIdAndDeleteFlg(Integer userId, Integer deleteFlg, Pageable pageable);
}
