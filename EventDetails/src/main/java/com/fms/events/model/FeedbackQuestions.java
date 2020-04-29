package com.fms.events.model;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * FeedbackQuestions
 */
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeedbackQuestions   {
  private String questions ;

  private Integer totalAnswers ;

  private String feedBackType ;
}
