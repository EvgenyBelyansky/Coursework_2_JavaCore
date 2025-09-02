package org.grtgb.coursework_2_javacore.service;

import lombok.RequiredArgsConstructor;
import org.grtgb.coursework_2_javacore.exception.TooManyRequestedQuestionsExceptions;
import org.grtgb.coursework_2_javacore.qestion.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@RequiredArgsConstructor
@Service
public class ExaminerServiceImpl implements ExaminerService{

    private final QuestionService questionService;



    @Override
    public Collection<Question> getQuestions(Integer amount) {

        Collection<Question> questions = new HashSet<>();
        int availableQuestions = questionService.getQuestionSetSize();

        if (amount <= 0) {
            return questions;
        } else if (amount > availableQuestions) {
            throw new TooManyRequestedQuestionsExceptions(amount, availableQuestions);
        }


        for (int i = 0; i < amount; i++) {
            questions.add(questionService.getRandomQuestion());
        }

        return  questions;
    }
}
