package com.yuri.gongbu.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import java.util.Random;

import com.yuri.gongbu.service.WordsService;
import com.yuri.gongbu.domain.words.WordsRepository;
import com.yuri.gongbu.domain.words.Words;
import com.yuri.gongbu.global.GlobalVariable;
import com.yuri.gongbu.web.dto.WordsListRequestDto;
import com.yuri.gongbu.web.dto.WordAddRequestDto;
import com.yuri.gongbu.config.auth.dto.SessionUser;
import com.yuri.gongbu.config.auth.LoginUser;

@RequiredArgsConstructor
@Controller
public class WordsApiController{

    private final WordsService wordsService;
    private final WordsRepository wordsRepository;

    @GetMapping("/list")
    public String showAll(@RequestParam(defaultValue = "1") Integer page, Model model) {
        PageRequest pageable = PageRequest.of(page - 1, GlobalVariable.WORD_PAGE_SIZE);
        model.addAttribute("result", wordsService.findByDeleteFlgZero(pageable));
        model.addAttribute("isSorted", GlobalVariable.FALSE);

        return "list";
    }

    @GetMapping("/list/sort")
    public String showWordsSortedBy(@RequestParam(defaultValue = "1") Integer page, @RequestParam("sort") String sortedBy, Model model) {
        if (GlobalVariable.sorts.contains(sortedBy)) {
            PageRequest pageable = PageRequest.of(page - 1, GlobalVariable.WORD_PAGE_SIZE, Sort.by(Sort.Direction.DESC, sortedBy));
            model.addAttribute("result", wordsService.findByDeleteFlgZero(pageable));
            model.addAttribute("sortedBy", sortedBy);
            model.addAttribute("isSorted", GlobalVariable.TRUE);

            return "list";
        } else {
            return "error";
        }
    }

    @GetMapping("/list/search")
    public String searchWord(@RequestParam(defaultValue = "1") Integer page, WordsListRequestDto requestDto, Model model){
        PageRequest pageable = PageRequest.of(page - 1, GlobalVariable.WORD_PAGE_SIZE);
        model.addAttribute("result", wordsService.findByDeleteFlgAndWordNameLike(requestDto.getKeyword(), pageable));
        model.addAttribute("requestDto", requestDto);

        return "list_search";
    }

    @GetMapping("/word/add")
    public String addWord(Model model, WordAddRequestDto wordAddRequestDto, @LoginUser SessionUser user) {
        //ログインしたユーザーで登録画面にアクセスするとエラー画面に移動
        if(user == null) {
            model.addAttribute("errorMessege", GlobalVariable.INVALID_ACCESS);
            return "error";
        }
        model.addAttribute("wordAddRequestDto", wordAddRequestDto);
        return "/word/add";
    }

    @PostMapping("/word/add")
    public String addWord(@Validated WordAddRequestDto wordAddRequestDto, BindingResult bindingResult, @LoginUser SessionUser user) {
        if (bindingResult.hasErrors()) {
            return "/word/add";
        }

        wordAddRequestDto.setUserId(user.getUserId());
        wordsService.addWord(wordAddRequestDto);
        return "redirect:/list";
    }
}