package com.yuri.gongbu.domain.words;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface WordsRepository extends JpaRepository<Words, Integer> {
    List<Words> findByDeleteFlg(Integer deleteFlg);
    List<Words> findByDeleteFlg(Integer deleteFlg, Sort sort);
}