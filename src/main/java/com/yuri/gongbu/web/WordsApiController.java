package com.yuri.gongbu.web;

import com.yuri.gongbu.config.auth.LoginUser;
import com.yuri.gongbu.config.auth.dto.SessionUser;
import com.yuri.gongbu.domain.words.Words;
import com.yuri.gongbu.domain.words.WordsRepository;
import com.yuri.gongbu.global.GlobalVariable;
import com.yuri.gongbu.service.WordLikeService;
import com.yuri.gongbu.service.WordsService;
import com.yuri.gongbu.util.WordsApiUtil;
import com.yuri.gongbu.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class WordsApiController {

    private final WordsService wordsService;
    private final WordLikeService wordLikeService;
    private final WordsRepository wordsRepository;

    @GetMapping("/word/list")
    public String showAll(@RequestParam(defaultValue = "1") Integer page, Model model) {
        PageRequest pageable = PageRequest.of(page - 1, GlobalVariable.WORD_PAGE_SIZE);
        model.addAttribute("result", wordsService.findByDeleteFlgZero(pageable));
        model.addAttribute("isSorted", GlobalVariable.FALSE);

        return "word/list";
    }

    @GetMapping("/word/list/sort")
    public String showWordsSortedBy(@RequestParam(defaultValue = "1") Integer page, @RequestParam("sort") String sortedBy, Model model) {
        if (GlobalVariable.sorts.contains(sortedBy)) {
            PageRequest pageable = PageRequest.of(page - 1, GlobalVariable.WORD_PAGE_SIZE, Sort.by(Sort.Direction.DESC, sortedBy));
            model.addAttribute("result", wordsService.getSortedWords(pageable));
            model.addAttribute("sortedBy", sortedBy);
            model.addAttribute("isSorted", GlobalVariable.TRUE);

            return "word/list";
        } else {
            return "error";
        }
    }

    @GetMapping("/word/list/search")
    public String searchWord(@RequestParam(defaultValue = "1") Integer page, WordsListRequestDto requestDto, Model model) {
        PageRequest pageable = PageRequest.of(page - 1, GlobalVariable.WORD_PAGE_SIZE);
        model.addAttribute("result", wordsService.findByDeleteFlgAndWordNameLike(requestDto.getKeyword(), pageable));
        model.addAttribute("requestDto", requestDto);

        return "word/list_search";
    }

    @GetMapping("/word/add")
    public String addWord(Model model, WordAddRequestDto wordAddRequestDto) {

        model.addAttribute("wordAddRequestDto", wordAddRequestDto);
        return "word/add";
    }

    @PostMapping("/word/add")
    public String addWord(@Validated WordAddRequestDto wordAddRequestDto, BindingResult bindingResult, @LoginUser SessionUser user) {
        if (bindingResult.hasErrors()) {
            return "word/add";
        }

        wordAddRequestDto.setUserId(user.getUserId());
        wordsService.add(wordAddRequestDto);
        return "redirect:/word/list";
    }

    @GetMapping("/word/detail/{wordId}")
    public String readWord(@PathVariable Integer wordId, Model model, HttpServletRequest httpServletRequest, HttpServletResponse response) {

        WordsApiUtil.countUpWordHit(wordId, httpServletRequest, response, wordsService);
        model.addAttribute("word", wordsService.findByWordId(wordId));

        return "word/read";
    }

    @GetMapping("/word/edit/{wordId}")
    public String editWord(@PathVariable Integer wordId, Model model, @LoginUser SessionUser user) {

        WordResponseDto wordResponseDto = wordsService.findByWordId(wordId);

        if (!WordsApiUtil.checkWordOwner(wordResponseDto, user)) {
            model.addAttribute("errorMessege", GlobalVariable.INVALID_ACCESS);
            return "error";
        }

        model.addAttribute("wordEditRequestDto", wordsService.findByWordId(wordId));

        return "word/edit";
    }

    @PostMapping("/word/edit")
    public String editWord(@Validated WordEditRequestDto wordEditRequestDto, BindingResult bindingResult, @LoginUser SessionUser user) {
        if (bindingResult.hasErrors()) {
            return "word/edit";
        }

        wordsService.edit(wordEditRequestDto);

        return "redirect:/word/list";
    }

    @GetMapping("/word/delete/{wordId}")
    public String deleteWord(@PathVariable Integer wordId, Model model, @LoginUser SessionUser user) {

        WordResponseDto wordResponseDto = wordsService.findByWordId(wordId);

        if (!WordsApiUtil.checkWordOwner(wordResponseDto, user)) {
            model.addAttribute("errorMessege", GlobalVariable.INVALID_ACCESS);
            return "error";
        }

        wordsService.delete(wordId);

        return "redirect:/word/list";
    }

    @GetMapping("/word/like/{wordId}")
    public String countUpLike(@PathVariable Integer wordId, Model model, @LoginUser SessionUser user) {

        if (wordLikeService.isLikeButtonClicked(user.getUserId(), wordId)) {
            model.addAttribute("errorMessege", "すでに♥しました。");
            return "redirect:/word/detail/" + wordId;
        }

        wordLikeService.add(new WordLikeAddRequestDto(user.getUserId(), wordId));
        wordsService.countUpWordLike(wordId);
        model.addAttribute("word", wordsService.findByWordId(wordId));

        return "redirect:/word/detail/" + wordId;
    }
}
