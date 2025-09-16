package org.grtgb.coursework_2_javacore.service;


import lombok.RequiredArgsConstructor;
import org.grtgb.coursework_2_javacore.qestion.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;


    @Override
    public Collection<Question> getQuestions(int amount) {
        final Collection<Question> randomQuestion = questionService.getRandomQuestion(amount);

        return randomQuestion;
    }
}
