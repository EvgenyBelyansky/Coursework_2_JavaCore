package org.grtgb.coursework_2_javacore.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.grtgb.coursework_2_javacore.exception.QuestionNotFoundException;
import org.grtgb.coursework_2_javacore.exception.QuestionSetIsEmptyException;
import org.grtgb.coursework_2_javacore.qestion.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Service
public class JavaQestionService implements QuestionService{

    private final Set<Question> questionSet;

    @Override
    public void addQuestion(String question, String answer) {
        if (question.isBlank() || answer.isBlank()) {
            throw new RuntimeException("Вопрос и ответ должны быть заполнены!!!");
        }

        final Question q = new Question(question, answer);
        addQuestion(q);

    }

    @Override
    public void addQuestion(Question question) {
        checkQuestionIsNotNull(question);

        questionSet.add(question);
    }

    @Override
    public void removeQuestion(Question question) {
        checkQuestionIsNotNull(question);

        questionSet.remove(question);

    }

    @Override
    public Collection<Question> getAllQuestion() {
        checkQuestionSetIsEmpty();

        return questionSet.stream().toList();
    }

    @Override
    public Question getRandomQuestion() {
        checkQuestionSetIsEmpty();

        Question randomQuestion = null;

        Random random = new Random();
        int randomInt = random.nextInt(questionSet.size());

        Iterator<Question> iterator = questionSet.iterator();

        for (int i = 0; i <= randomInt; i++) {
            randomQuestion = iterator.next();
        }

        return randomQuestion;
    }

    @Override
    public int getQuestionSetSize() {
        checkQuestionSetIsEmpty();

        int size = questionSet.size();

        return size;
    }


    private void checkQuestionIsNotNull(Question question) {
        checkQuestionSetIsEmpty();

        if (question == null) {
            throw new QuestionNotFoundException();
        }
    }

    private void checkQuestionSetIsEmpty() {
        if (questionSet.isEmpty()) {
            throw new QuestionSetIsEmptyException();
        }
    }

}
