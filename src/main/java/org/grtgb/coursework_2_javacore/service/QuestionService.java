package org.grtgb.coursework_2_javacore.service;

import org.grtgb.coursework_2_javacore.qestion.Question;

import java.util.Collection;


public interface QuestionService {

     void addQuestion(String question, String answer);

     void addQuestion(Question question);

     void removeQuestion(Question question);

     Collection<Question> getAllQuestion();

     Collection<Question> getRandomQuestion(int amount);

     int getQuestionSetSize();

}
