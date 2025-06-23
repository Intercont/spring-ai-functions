package com.igorfragadev.springaifunctions.controllers;

import com.igorfragadev.springaifunctions.model.Answer;
import com.igorfragadev.springaifunctions.model.Question;
import com.igorfragadev.springaifunctions.services.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuestionController {

    @Autowired
    OpenAIService openAIService;

    @PostMapping("/weather")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }

}
