package com.yuri.gongbu.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.PageRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;

import com.yuri.gongbu.config.auth.dto.SessionUser;
import com.yuri.gongbu.config.auth.LoginUser;
import com.yuri.gongbu.service.WordsService;
import com.yuri.gongbu.global.GlobalVariable;
import com.yuri.gongbu.service.UserService;

@RequiredArgsConstructor
@Controller
public class MyPageApiController{

    private final WordsService wordsService;
    private final UserService userService;

    @GetMapping("/myPage")
    public String goMyPage(Model model, @LoginUser SessionUser user, @RequestParam(defaultValue = "1") Integer page) {
        PageRequest pageable = PageRequest.of(page - 1, GlobalVariable.MY_PAGE_WORD_PAGE_SIZE);
        model.addAttribute("result", wordsService.findMyWord(user.getUserId(), pageable));
        return "my_page/my_page";
    }

    @GetMapping("/withdrawal")
    public String withdrawal(@LoginUser SessionUser user, HttpServletRequest request) {
        userService.withdrawal(user.getUserId());
        HttpSession session = request.getSession(false);
        session.invalidate();

        return "redirect:/";
    }
}