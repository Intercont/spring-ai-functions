package com.igorfragadev.springaifunctions.services;

import com.igorfragadev.springaifunctions.model.Answer;
import com.igorfragadev.springaifunctions.model.Question;

public interface OpenAIService {

    Answer getAnswer(Question question);

}
