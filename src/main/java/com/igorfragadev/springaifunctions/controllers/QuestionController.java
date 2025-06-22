package com.igorfragadev.springaifunctions.controllers;

import com.igorfragadev.springaiintro.model.*;
import com.igorfragadev.springaiintro.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private final OpenAIService openAIService;

    public QuestionController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }

    @PostMapping("/capital")
    public Answer getCapital(@RequestBody GetCapitalRequest stateOrCountry) {
        return openAIService.getCapital(stateOrCountry);
    }

    @PostMapping("/capitalWithInfo")
    public Answer getCapitalWithInfo(@RequestBody GetCapitalRequest stateOrCountry) {
        return openAIService.getCapitalWithInfo(stateOrCountry);
    }

    @PostMapping("/capitalJSON")
    public GetCapitalResponse getCapitalJSON(@RequestBody GetCapitalRequest stateOrCountry) {
        return openAIService.getCapitalJSON(stateOrCountry);
    }

    @PostMapping("/capitalWithInfoJSON")
    public GetCapitalWithInfoJSONResponse getcapitalWithInfoJSON(@RequestBody GetCapitalRequest stateOrCountry) {
        return openAIService.getCapitalWithInfoJSON(stateOrCountry);
    }
}
