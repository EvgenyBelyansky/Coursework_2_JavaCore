package org.grtgb.coursework_2_javacore.service;

import lombok.RequiredArgsConstructor;
import org.grtgb.coursework_2_javacore.exception.*;
import org.grtgb.coursework_2_javacore.qestion.Question;
import org.grtgb.coursework_2_javacore.repository.JavaQuestionRepositiry;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class JavaQestionService implements QuestionService {

    private final JavaQuestionRepositiry javaQuestionRepositiry;


    @Override
    public void addQuestion(String question, String answer) {

        final Question q = new Question(question, answer);
        addQuestion(q);

    }

    @Override
    public void addQuestion(Question question) {
        checkQuestionIsNotNull(question);
        checkQuestionIsNotDouble(question);

        javaQuestionRepositiry.addQuestion(question);
    }

    @Override
    public void removeQuestion(Question question) {
        checkQuestionIsNotNull(question);
        checkQuestionNotFoundInSet(question);

        javaQuestionRepositiry.removeQuestion(question);

    }

    @Override
    public Collection<Question> getAllQuestion() {
        checkQuestionSetIsEmpty();

        return javaQuestionRepositiry.getAll();
    }

    @Override
    public Collection<Question> getRandomQuestion(int amount) {
        checkQuestionSetIsEmpty();

        if (amount < 0) {
            throw new ArgumentLessZeroException(amount, getQuestionSetSize());
        } else if (amount > javaQuestionRepositiry.getQuestionSetSize()) {
            throw new TooManyRequestedQuestionsExceptions(amount, getQuestionSetSize());
        } else if (amount == javaQuestionRepositiry.getQuestionSetSize()) {
            return getAllQuestion();
        }

        final List<Question> questionList = new ArrayList<>(javaQuestionRepositiry.getAll());

        Collections.shuffle(questionList);

        final HashSet<Question> questions = new HashSet<>(questionList.subList(0, amount));
        return questions;

    }

    @Override
    public int getQuestionSetSize() {
        return javaQuestionRepositiry.getQuestionSetSize();
    }

    private void checkQuestionIsNotNull(Question question) {
        if (question == null) {
            throw new QuestionIsNullException();
        }
    }

    private void checkQuestionIsNotDouble(Question question) {
        if (javaQuestionRepositiry.getAll().contains(question)) {
            throw new QuestionIsDoubleException(question);
        }
    }

    private void checkQuestionSetIsEmpty() {
        if (javaQuestionRepositiry.getAll().isEmpty()) {
            throw new QuestionSetIsEmptyException();
        }
    }

    private void checkQuestionNotFoundInSet(Question question) {
        if (!javaQuestionRepositiry.getAll().contains(question)) {
            throw new QuestionNotFoundException(question);
        }
    }

}
