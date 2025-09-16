package org.grtgb.coursework_2_javacore.model.service;

import org.grtgb.coursework_2_javacore.qestion.Question;
import org.grtgb.coursework_2_javacore.service.ExaminerServiceImpl;
import org.grtgb.coursework_2_javacore.service.QuestionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;


    @Test
    @DisplayName("Принимает количество вопросов на подбор и получает в ответ коллекцию с запрошенным количеством уникальных вопросов ")
    void getQuestions1() {

        int requestedAmount = 3;

        Collection<Question> result = new HashSet<>(getTestQuestions().stream()
                .limit(requestedAmount)
                .collect(Collectors.toSet())
        );

        Mockito.when(questionService.getRandomQuestion(requestedAmount))
                .thenReturn(result);

        Collection<Question> actual = examinerService.getQuestions(requestedAmount);

        assertThat(actual)
                .isNotNull()
                .hasSize(requestedAmount)
                .doesNotHaveDuplicates()
                .isEqualTo(result);

        Mockito.verify(questionService, Mockito.times(1
        )).getRandomQuestion(requestedAmount);
    }


    private static Collection<Question> getTestQuestions() {
        Collection<Question> questions = new HashSet<>();
        Question question1 = new Question("Вопрос1", "Ответ1");
        Question question2 = new Question("Вопрос2", "Ответ2");
        Question question3 = new Question("Вопрос3", "Ответ3");
        Question question4 = new Question("Вопрос4", "Ответ4");
        Question question5 = new Question("Вопрос5", "Ответ5");
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        return questions;
    }


}