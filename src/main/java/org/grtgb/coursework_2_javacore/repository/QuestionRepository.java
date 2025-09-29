package org.grtgb.coursework_2_javacore.repository;

import org.grtgb.coursework_2_javacore.qestion.Question;

import java.util.Collection;


public interface QuestionRepository {

    void addQuestion(Question question);

    void removeQuestion(Question question);

    Collection<Question> getAll();

    int getQuestionSetSize();


//    public int getQuestionSetSize() {
//        checkQuestionSetIsEmpty();
//
//        int size = questionSet.size();
//
//        return size;
//    }



}
