package org.grtgb.coursework_2_javacore.service;

import org.grtgb.coursework_2_javacore.qestion.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}
