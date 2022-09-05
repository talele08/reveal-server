package com.revealprecision.revealserver.api.v1.dto.response;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResourcePlanningQuestionResponse {

  private List<SecondStepQuestionsResponse> secondStepQuestions = new ArrayList<>();
  private List<FormulaResponse> bufferQuestions = new ArrayList<>();
}
