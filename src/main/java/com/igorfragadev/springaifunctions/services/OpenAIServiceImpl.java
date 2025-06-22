package com.igorfragadev.springaifunctions.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.igorfragadev.springaiintro.model.*;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatModel chatModel;
    ObjectMapper objectMapper;

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/get-capital-json-response-prompt.st")
    private Resource getCapitalJSONPrompt;

    @Value("classpath:templates/get-capital-with-info-prompt.st")
    private Resource getCapitalWithInfoPrompt;

    public OpenAIServiceImpl(ChatModel chatModel, ObjectMapper objectMapper) {
        this.chatModel = chatModel;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();

        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getText();
    }

    @Override
    public Answer getAnswer(Question question) {
        System.out.println("Received question: " + question.question()); // Log the question for debugging purposes only
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();

        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getText());
    }

    @Override
    public Answer getCapital(GetCapitalRequest capitalRequest) {
//        PromptTemplate promptTemplate = new PromptTemplate("What is the capital of " + capitalRequest.capitalRequest() + "?");
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capitalRequest.stateOrCountry())); //map the variable from the template into the prompt with the desired received value

        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getText());
    }

    @Override
    public GetCapitalResponse getCapitalJSON(GetCapitalRequest capitalRequest) {
        BeanOutputConverter<GetCapitalResponse> converter = new BeanOutputConverter<>(GetCapitalResponse.class); //generates the whole expected format of JSON that is passed for OpenAI
        String format = converter.getFormat();
        System.out.println("Format for JSON response: " + format);

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalJSONPrompt);
        Prompt prompt = promptTemplate.create(Map.of(
                "stateOrCountry", capitalRequest.stateOrCountry(),
                "format", format
                )
        );

        ChatResponse response = chatModel.call(prompt);

        System.out.println(response.getResult().getOutput().getText());
        return converter.convert(response.getResult().getOutput().getText());
    }

    @Override
    public Answer getCapitalWithInfo(GetCapitalRequest capitalRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalWithInfoPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capitalRequest.stateOrCountry()));

        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getText());
    }

    @Override
    public GetCapitalWithInfoJSONResponse getCapitalWithInfoJSON(GetCapitalRequest capitalRequest) {
        BeanOutputConverter<GetCapitalWithInfoJSONResponse> converter = new BeanOutputConverter<>(GetCapitalWithInfoJSONResponse.class);
        String format = converter.getFormat();
        System.out.println(format);

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalJSONPrompt);
        Prompt prompt = promptTemplate.create(Map.of(
                        "stateOrCountry", capitalRequest.stateOrCountry(),
                        "format", format
                )
        );

        ChatResponse response = chatModel.call(prompt);
        return converter.convert(response.getResult().getOutput().getText());
    }
}
