package org.grtgb.coursework_2_javacore.service;

import lombok.RequiredArgsConstructor;
import org.grtgb.coursework_2_javacore.qestion.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@RequiredArgsConstructor
@Service
public class ExaminerServiceImpl implements ExaminerService{

    private final QuestionService questionService;



    @Override
    public Collection<Question> getQuestions(int amount) {
        long start = System.currentTimeMillis();

        Collection<Question> questions = new HashSet<>();

        final Collection<Question> randomQuestion = questionService.getRandomQuestion(amount);


        System.out.println(System.currentTimeMillis() - start + "ms.");
        System.out.println(randomQuestion.size());


        return randomQuestion;
    }
}
