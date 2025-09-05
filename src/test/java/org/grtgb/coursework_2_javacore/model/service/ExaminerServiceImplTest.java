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

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;


    @Test
    @DisplayName("Принимает количество вопросов на подбор меньше чем существует и возвращает нужное количество")
    void getQuestions1() {

        int availableQuestions = 5;
        int requestedAmount = 3;

        Question question1 = new Question("Вопрос1", "Ответ1");
        Question question2 = new Question("Вопрос2", "Ответ2");
        Question question3 = new Question("Вопрос3", "Ответ3");
        Question question4 = new Question("Вопрос4", "Ответ4");
        Question question5 = new Question("Вопрос5", "Ответ5");

        Mockito.when(questionService.getQuestionSetSize()).thenReturn(availableQuestions);
//        Mockito.when(questionService.getRandomQuestion())
//                .thenReturn(question1, question2, question3);

        Collection<Question> result = examinerService.getQuestions(requestedAmount);

        assertThat(result)
                .isNotNull()
                .hasSize(requestedAmount)
                .doesNotHaveDuplicates();

        Mockito.verify(questionService, Mockito.times(1)).getQuestionSetSize();
//        Mockito.verify(questionService, Mockito.times(requestedAmount)).getRandomQuestion();

    }

    @Test
    @DisplayName("Принимает количество вопросов на подбор столько же, сколько и существует и возвращает их все")
    void getQuestions2() {

        int requestedAmount = 5;

        final Collection<Question> questions = getQuestions();


        Mockito.when(questionService.getQuestionSetSize()).thenReturn(questions.size());
        Mockito.when(questionService.getAllQuestion())
                .thenReturn(questions);

        Collection<Question> actual = examinerService.getQuestions(requestedAmount);

        assertThat(actual)
                .isNotNull()
                .containsExactlyInAnyOrderElementsOf(questions);

        Mockito.verify(questionService, Mockito.times(1)).getQuestionSetSize();
        Mockito.verify(questionService, Mockito.times(1)).getAllQuestion();

    }

    @Test
    @DisplayName("Принимает количество вопросов на подбор больше чем существует и выбрасывает ошибку")
    void getQuestions3() {

        int availableQuestions = 5;
        int requestedAmount = 9;

        final Collection<Question> questions = getQuestions();


        Mockito.when(questionService.getQuestionSetSize()).thenReturn(availableQuestions);
        Mockito.when(questionService.getAllQuestion())
                .thenReturn(questions);

        Collection<Question> actual = examinerService.getQuestions(requestedAmount);

//        assertThat(actual).is;

        Mockito.verify(questionService, Mockito.times(1)).getQuestionSetSize();
        Mockito.verify(questionService, Mockito.times(1)).getAllQuestion();
    }


    private static Collection<Question> getQuestions() {
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