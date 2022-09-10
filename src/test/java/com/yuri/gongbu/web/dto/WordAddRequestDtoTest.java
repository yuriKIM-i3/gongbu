// package com.yuri.gongbu.web.dto;

// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.validation.Validator;
// import org.springframework.validation.BindException;
// import org.springframework.validation.BindingResult;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.AfterAll;
// import static org.assertj.core.api.Assertions.assertThat;
// import static org.junit.jupiter.api.Assertions.assertNull;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import java.io.StringWriter;

// import com.yuri.gongbu.web.dto.WordAddRequestDto;
// import com.yuri.gongbu.global.GlobalVariable;
// import com.yuri.gongbu.domain.user.UserRepository;
// import com.yuri.gongbu.domain.words.WordsRepository;

// @SpringBootTest
// public class WordAddRequestDtoTest {

//     @Autowired
//     Validator validator;

//     private WordAddRequestDto wordAddRequestDto = new WordAddRequestDto();
//     private BindingResult bindingResult = new BindException(wordAddRequestDto, "wordAddRequestDto");

//     @BeforeEach
//     void setup() {
//         wordAddRequestDto.setWordName("non-null");
//         wordAddRequestDto.setWordPronunciation("non-null");
//         wordAddRequestDto.setWordMeaning("non-null");
//     }

//     @Test
//     void wordNameInputNull() {
//         // given
//         wordAddRequestDto.setWordName(null);
//         // when
//         validator.validate(wordAddRequestDto, bindingResult);
//         // then
//         assertNotNull(bindingResult.getFieldError());
//     }


//     @Test
//     void wordNameUptoMaxLength() {
//         // given
//         String wordName = createString(GlobalVariable.WORD_NAME_MAX_LENGTH);
//         wordAddRequestDto.setWordName(wordName);
//         // when
//         validator.validate(wordAddRequestDto, bindingResult);
//         // then
//         assertNull(bindingResult.getFieldError());
//     }

//     @Test
//     void wordNameOverMaxLength() {
//         // given
//         String wordName = createString(GlobalVariable.WORD_NAME_MAX_LENGTH + 1);
//         wordAddRequestDto.setWordName(wordName);
//         // when
//         validator.validate(wordAddRequestDto, bindingResult);
//         // then
//         assertNotNull(bindingResult.getFieldError());
//     }

//     @Test
//     void wordPronunciationInputNull() {
//         // given
//         wordAddRequestDto.setWordPronunciation(null);
//         // when
//         validator.validate(wordAddRequestDto, bindingResult);
//         // then
//         assertNotNull(bindingResult.getFieldError());
//     }

//     @Test
//     void wordPronunciationUptoMaxLength() {
//         // given
//         String wordPronunciation = createString(GlobalVariable.WORD_PRONUNCIATION_MAX_LENGTH);
//         wordAddRequestDto.setWordPronunciation(wordPronunciation);
//         // when
//         validator.validate(wordAddRequestDto, bindingResult);
//         // then
//         assertNull(bindingResult.getFieldError());
//     }

//     @Test
//     void wordPronunciationOverMaxLength() {
//         // given
//         String wordPronunciation = createString(GlobalVariable.WORD_PRONUNCIATION_MAX_LENGTH + 1);
//         wordAddRequestDto.setWordPronunciation(wordPronunciation);
//         // when
//         validator.validate(wordAddRequestDto, bindingResult);
//         // then
//         assertNotNull(bindingResult.getFieldError());
//     }

//     @Test
//     void wordMeaningInputNull() {
//         // given
//         wordAddRequestDto.setWordMeaning(null);
//         // when
//         validator.validate(wordAddRequestDto, bindingResult);
//         // then
//         assertNotNull(bindingResult.getFieldError());
//     }

//     @Test
//     void wordMeaningUptoMaxLength() {
//         // given
//         String wordMeaning = createString(GlobalVariable.WORD_MEANING_MAX_LENGTH);
//         wordAddRequestDto.setWordMeaning(wordMeaning);
//         // when
//         validator.validate(wordAddRequestDto, bindingResult);
//         // then
//         assertNull(bindingResult.getFieldError());
//     }

//     @Test
//     public void wordMeaningOverMaxLength() {
//         // given
//         String wordMeaning = createString(GlobalVariable.WORD_MEANING_MAX_LENGTH + 1);
//         wordAddRequestDto.setWordMeaning(wordMeaning);
//         // when
//         validator.validate(wordAddRequestDto, bindingResult);
//         // then
//         assertNotNull(bindingResult.getFieldError());
//     }

//     @Test
//     public void wordExampleUptoMaxLength() {
//         // given
//         String wordExample=  createString(GlobalVariable.WORD_EXAMPLE_MAX_LENGTH);
//         wordAddRequestDto.setWordExample(wordExample);
//         // when
//         validator.validate(wordAddRequestDto, bindingResult);
//         // then
//         assertNull(bindingResult.getFieldError());
//     }

//     @Test
//     public void wordExampleOverMaxLength() {
//         // given
//         String wordExample = createString(GlobalVariable.WORD_EXAMPLE_MAX_LENGTH + 1);
//         wordAddRequestDto.setWordExample(wordExample);
//         // when
//         validator.validate(wordAddRequestDto, bindingResult);
//         // then
//         assertNotNull(bindingResult.getFieldError());
//     }

//     @AfterAll
//     static void cleanUp(@Autowired WordsRepository wordsRepository, @Autowired UserRepository userRepository) {
//         wordsRepository.deleteAll();
//         userRepository.deleteAll();
//     }

//     private String createString(int length) {
//         return new StringWriter(){{ for(int i=0;i < length;i++) write("a"); }}.toString();
//     }
// }