package org.grtgb.coursework_2_javacore.service;

import lombok.RequiredArgsConstructor;
import org.grtgb.coursework_2_javacore.qestion.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MathQuestionService implements QuestionService{
    @Override
    public void addQuestion(String question, String answer) {

    }

    @Override
    public void addQuestion(Question question) {

    }

    @Override
    public void removeQuestion(Question question) {

    }

    @Override
    public Collection<Question> getAllQuestion() {
        return List.of();
    }

    @Override
    public Collection<Question> getRandomQuestion(int amount) {
        return List.of();
    }

    @Override
    public int getQuestionSetSize() {
        return 0;
    }
}
