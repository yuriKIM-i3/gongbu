package com.yuri.gongbu.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import com.yuri.gongbu.service.WordsService;
import com.yuri.gongbu.global.GlobalVariable;
import com.yuri.gongbu.config.auth.dto.SessionUser;
import com.yuri.gongbu.web.dto.WordResponseDto;

public class WordsApiUtil {
    public static void countUpWordHit(Integer wordId, HttpServletRequest request, HttpServletResponse response, WordsService wordsService) {

        String cookieValue = Integer.toString(wordId);
        Cookie[] cookies = request.getCookies();
        Cookie oldCookie = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("wordId")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("[" + cookieValue + "]")) {
                wordsService.countUpWordHit(wordId);

                oldCookie.setValue(oldCookie.getValue() + "_[" + cookieValue + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(GlobalVariable.COOKIE_MAX_AGE);
                response.addCookie(oldCookie);
            }
        } else {
            wordsService.countUpWordHit(wordId);

            Cookie newCookie = new Cookie("wordId", "[" + cookieValue + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(GlobalVariable.COOKIE_MAX_AGE);
            response.addCookie(newCookie);
        }
    }

    public static boolean checkWordOwner(WordResponseDto wordResponseDto, SessionUser user) {
        return wordResponseDto.getUserId().equals(user.getUserId());
    } 
}