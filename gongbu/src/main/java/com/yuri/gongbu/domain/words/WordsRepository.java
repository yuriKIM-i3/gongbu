package com.yuri.gongbu.domain.words;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WordsRepository extends JpaRepository<Words, Integer> {
    Page<Words> findByDeleteFlg(Integer deleteFlg, Pageable pageable);
}