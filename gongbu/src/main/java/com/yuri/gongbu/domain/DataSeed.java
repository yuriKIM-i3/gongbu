package com.yuri.gongbu.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.yuri.gongbu.domain.words.WordsRepository;
import com.yuri.gongbu.domain.user.UserRepository;
import com.yuri.gongbu.global.GlobalVariable;
import com.yuri.gongbu.domain.words.Words;
import com.yuri.gongbu.domain.user.User;
import com.yuri.gongbu.domain.user.Role;

@RequiredArgsConstructor
@Component
public class DataSeed implements CommandLineRunner {

    private final WordsRepository wordsRepository;
    private final UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
        if(args[0].equals("seedingData")){
            seedWordsTable();
            seedUserTable();
        }
	}

    private void seedWordsTable() {
        int userId = 1;

        wordsRepository.save(Words.builder()
            .userId(userId)
            .wordName("노잼")
            .wordPronunciation("ノジャム")    
            .wordMeaning("つまらない")         
            .wordExample("메이플 노잼")
            .wordHits(30)
            .wordLike(50)
            .deleteFlg(GlobalVariable.FALSE)
            .build());

        wordsRepository.save(Words.builder()
            .userId(userId)
            .wordName("꿀잼")
            .wordPronunciation("クルジャム")    
            .wordMeaning("めっちゃおもろい")         
            .wordExample("메이플 꿀잼")
            .wordHits(345)
            .wordLike(55)
            .deleteFlg(GlobalVariable.FALSE)
            .build());
    }

    private void seedUserTable() {

        userRepository.save(User.builder()
            .userName("jiho")
            .userEmail("cok")
            .userRole(Role.MEMBER)    
            .deleteFlg(GlobalVariable.FALSE)
            .build());

        userRepository.save(User.builder()
            .userName("yuriSAMA")
            .userEmail("y")
            .userRole(Role.MEMBER)    
            .deleteFlg(GlobalVariable.FALSE)
            .build());
    }
}