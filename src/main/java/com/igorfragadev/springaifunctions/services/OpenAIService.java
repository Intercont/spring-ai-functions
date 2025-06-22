package com.igorfragadev.springaifunctions.services;

import com.igorfragadev.springaiintro.model.*;

public interface OpenAIService {

    String getAnswer(String question);

    Answer getAnswer(Question question);

    Answer getCapital(GetCapitalRequest capitalRequest);

    GetCapitalResponse getCapitalJSON(GetCapitalRequest capitalRequest);

    Answer getCapitalWithInfo(GetCapitalRequest capitalRequest);

    GetCapitalWithInfoJSONResponse getCapitalWithInfoJSON(GetCapitalRequest capitalRequest);
}
