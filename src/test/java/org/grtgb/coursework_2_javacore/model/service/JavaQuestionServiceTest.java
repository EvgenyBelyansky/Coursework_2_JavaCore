package org.grtgb.coursework_2_javacore.model.service;

import org.grtgb.coursework_2_javacore.exception.*;
import org.grtgb.coursework_2_javacore.qestion.Question;
import org.grtgb.coursework_2_javacore.service.JavaQestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    private JavaQestionService javaQuestionService;

    private Question question1;
    private Question question2;
    private Question question3;

    @BeforeEach
    void setJavaQuestionService() {

        javaQuestionService = new JavaQestionService(new HashSet<>());

        question1 = new Question("Что такое Java?", "Язык программирования");
        question2 = new Question("Что такое Spring?", "Фреймворк");
        question3 = new Question("Что такое JUnit?", "Фреймворк для тестирования");
    }


    @Test
    @DisplayName("Принимает заполненные строки с вопросом и ответом и создает объект")
    void addQuestionTest1() {

        final String questionText = "q1";
        final String answerText = "a1";

        Question testQuestion = new Question(questionText, answerText);

        javaQuestionService.addQuestion(questionText, answerText);

        assertThat(javaQuestionService.getAllQuestion().contains(testQuestion));

    }

    @Test
    @DisplayName("Принимает неповторяющийся и не пустой вопрос и добавляет его в сет")
    void addQuestionTest2() {

        Question testQuestion = new Question("Yes?", "No");

        javaQuestionService.addQuestion(testQuestion);

        assertThat(javaQuestionService.getAllQuestion().contains(testQuestion));
    }

    @Test
    @DisplayName("Принимает повторяющийся не пустой вопрос и выбрасывает ошибку")
    void addQuestionTest3() {

        Question testQuestion = new Question("Что такое Spring?", "Фреймворк");

        javaQuestionService.addQuestion(testQuestion);

        assertThatThrownBy(() -> javaQuestionService.addQuestion(testQuestion))
                .isInstanceOf(QuestionIsDoubleException.class)
                .hasMessageContaining(testQuestion.getQuestion());
    }

    @Test
    @DisplayName("Принимает null вопрос и выбрасывает ошибку")
    void addQuestionTest4() {

        Question testQuestion = null;

        assertThatThrownBy(() -> javaQuestionService.addQuestion(testQuestion))
                .isInstanceOf(QuestionIsNullException.class);
    }

    @Test
    @DisplayName("Принимает существующий, неповторяющийся вопрос и удаляет его")
    void removeQuestionTest1() {

        addQuestionsInSet();

        javaQuestionService.removeQuestion(question1);

        assertThat(javaQuestionService.getAllQuestion()).doesNotContain(question1);
    }

    @Test
    @DisplayName("Принимает вопрос, которого нет в сете и выбрасывает ошибку")
    void removeQuestionTest2() {

        javaQuestionService.addQuestion(question1);
        javaQuestionService.addQuestion(question2);

        assertThat(javaQuestionService.getAllQuestion()).doesNotContain(question3);
        assertThatThrownBy(() -> javaQuestionService.removeQuestion(question3))
                .isInstanceOf(QuestionNotFoundException.class);
        assertThat(javaQuestionService.getAllQuestion())
                .hasSize(javaQuestionService.getAllQuestion().size())
                .contains(question1, question2)
                .doesNotContain(question3);
    }

    @Test
    @DisplayName("Принимает null вопрос и выкидывает ошибку")
    void removeQuestionTest3() {

        Question testQuestion = null;

        assertThatThrownBy(() -> javaQuestionService.removeQuestion(testQuestion))
                .isInstanceOf(QuestionIsNullException.class);
    }

    @Test
    @DisplayName("Если сет вопросов содержит вопросы возвращает неизменяемую копию коллекции")
    void getAllQuestion1() {

        addQuestionsInSet();


        assertThat(javaQuestionService.getAllQuestion())
                .hasSize(3)
                .containsExactlyInAnyOrder(question1, question2, question3);
        assertThatThrownBy(() -> javaQuestionService.getAllQuestion().clear()) // Пытаемся очистить коллекцию
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("Если сет вопросов не содержит вопросы выбрасывает ошибку")
    void getAllQuestion2() {
        assertThatThrownBy(() -> javaQuestionService.getAllQuestion())
                .isInstanceOf(QuestionSetIsEmptyException.class);
    }

    @Test
    @DisplayName("Принимает в аргумент 0 и отправляет пустую коллекцию")
    void getRandomQuestionTest1() {

        addQuestionsInSet();

        final Collection<Question> actual = javaQuestionService.getRandomQuestion(0);

        assertThat(actual).isEmpty();
    }

    @Test
    @DisplayName("Принимает в аргумент число больше чем есть вопросов в сете и выдает ошибку")
    void getRandomQuestionTest2() {

        addQuestionsInSet();

        assertThatThrownBy(() -> javaQuestionService.getRandomQuestion(4))
                .isInstanceOf(TooManyRequestedQuestionsExceptions.class);
    }

    @Test
    @DisplayName("Принимает в аргумент число равное количеству вопросов в сете и выдает все вопросы")
    void getRandomQuestionTest3() {

        addQuestionsInSet();

        final Collection<Question> actual = javaQuestionService.getRandomQuestion(3);
        final Collection<Question> allQuestion = javaQuestionService.getAllQuestion();

        assertThat(actual).containsExactlyInAnyOrderElementsOf(allQuestion)
                .doesNotHaveDuplicates();
    }

    @Test
    @DisplayName("Принимает в аргумент число меньше чем количество вопросов в сете и возвращает запрашиваемое количество вопросов")
    void getRandomQuestionTest4() {

        addQuestionsInSet();

        int argument = 2;

        final Collection<Question> actual = javaQuestionService.getRandomQuestion(argument);
        final Collection<Question> allQuestion = javaQuestionService.getAllQuestion();

        assertThat(actual).isSubsetOf(allQuestion)
                .hasSize(argument)
                .doesNotHaveDuplicates();
    }

    @Test
    @DisplayName("Принимает в аргумент число меньше 0 и выдает ошибку")
    void getRandomQuestionTest5() {

        addQuestionsInSet();

        assertThatThrownBy(() -> javaQuestionService.getRandomQuestion(-3))
                .isInstanceOf(ArgumentLessZeroException.class);
    }

    @Test
    @DisplayName("Проверка на дубликаты в возвращаемой коллекции")
    void getRandomQuestionTest6() {

        addQuestionsInSet();

        int argument = 2;

        final Collection<Question> actual = javaQuestionService.getRandomQuestion(argument);
        final Collection<Question> allQuestion = javaQuestionService.getAllQuestion();

        assertThat(actual).doesNotHaveDuplicates();
        assertThat(allQuestion).doesNotHaveDuplicates();
    }

    @Test
    @DisplayName("Принимает в аргумент число 1 и возвращает новый сет только из 1 вопроса")
    void getRandomQuestionTest7() {

        addQuestionsInSet();

        int argument = 1;

        final Collection<Question> actual = javaQuestionService.getRandomQuestion(1);
        final Collection<Question> allQuestion = javaQuestionService.getAllQuestion();

        assertThat(actual).hasSize(argument)
                .isSubsetOf(allQuestion);
    }

    @Test
    @DisplayName("Возвращает действительный размер сета вопросов")
    void getQuestionSetSizeTest1() {

        addQuestionsInSet();

        final int questionSetSize = javaQuestionService.getQuestionSetSize();

        assertThat(questionSetSize).isEqualTo(3);
    }

    @Test
    @DisplayName("При пустом сете выбрасывает ошибку")
    void getQuestionSetSizeTest2() {
        assertThatThrownBy(() -> javaQuestionService.getQuestionSetSize())
                .isInstanceOf(QuestionSetIsEmptyException.class);
    }



    private void addQuestionsInSet() {
        javaQuestionService.addQuestion(question1);
        javaQuestionService.addQuestion(question2);
        javaQuestionService.addQuestion(question3);
    }
}