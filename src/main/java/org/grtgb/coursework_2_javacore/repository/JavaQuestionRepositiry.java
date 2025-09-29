package org.grtgb.coursework_2_javacore.repository;

import lombok.RequiredArgsConstructor;
import org.grtgb.coursework_2_javacore.qestion.Question;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class JavaQuestionRepositiry implements QuestionRepository{

    private final HashSet<Question> javaQuestionSet;

    @Override
    public void addQuestion(Question question) {
        javaQuestionSet.add(question);
    }

    @Override
    public void removeQuestion(Question question) {
        javaQuestionSet.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(javaQuestionSet);
    }

    @Override
    public int getQuestionSetSize() {
        final int size = javaQuestionSet.size();

        return size;
    }
}
