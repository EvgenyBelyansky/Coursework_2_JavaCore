package org.grtgb.coursework_2_javacore.service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.grtgb.coursework_2_javacore.exception.*;
import org.grtgb.coursework_2_javacore.qestion.Question;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JavaQestionService implements QuestionService{

    private final Set<Question> questionSet;



    @Override
    public void addQuestion(String question, String answer) {

        final Question q = new Question(question, answer);
        addQuestion(q);

    }

    @Override
    public void addQuestion(Question question) {
        checkQuestionIsNotNull(question);
        checkQuestionIsNotDouble(question);

        questionSet.add(question);
    }

    @Override
    public void removeQuestion(Question question) {
        checkQuestionIsNotNull(question);
        checkQuestionSetIsNotFound(question);

        questionSet.remove(question);

    }

    @Override
    public Collection<Question> getAllQuestion() {
        checkQuestionSetIsEmpty();

        return Collections.unmodifiableSet(questionSet);
    }

    @Override
    public Collection<Question> getRandomQuestion(int amount) {
        checkQuestionSetIsEmpty();

        if (amount <= 0) {
            return Collections.emptySet();
        } else if (amount > questionSet.size()) {
            throw new TooManyRequestedQuestionsExceptions(amount, questionSet.size());
        } else if (amount == questionSet.size()) {
            return getAllQuestion();
        }

        final List<Question> questionList = new ArrayList<>(questionSet);

        Collections.shuffle(questionList);

        final HashSet<Question> questions = new HashSet<>(questionList.subList(0, amount));
        return questions;

    }

    @Override
    public int getQuestionSetSize() {
        checkQuestionSetIsEmpty();

        int size = questionSet.size();

        return size;
    }


    private void checkQuestionIsNotNull(Question question) {
        if (question == null) {
            throw new QuestionIsNullException();
        }
    }

    private void checkQuestionIsNotDouble(Question question) {
        if (questionSet.contains(question)) {
            throw new QuestionIsDoubleException(question);
        }
    }

    private void checkQuestionSetIsEmpty() {
        if (questionSet.isEmpty()) {
            throw new QuestionSetIsEmptyException();
        }
    }

    private void checkQuestionSetIsNotFound(Question question) {
        if (!questionSet.contains(question)) {
            throw new QuestionNotFoundException(question);
        }
    }

}
