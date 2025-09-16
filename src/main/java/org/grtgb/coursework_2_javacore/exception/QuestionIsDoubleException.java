package org.grtgb.coursework_2_javacore.exception;

import org.grtgb.coursework_2_javacore.qestion.Question;
import org.springframework.http.HttpStatus;

public class QuestionIsDoubleException extends ExamException {
    public QuestionIsDoubleException(Question question) {
        super(ExamErrorCode.QUESTION_IS_DOUBLE,
                "Вопрос [%s] уже есть в наборе, введите другой вопрос!".formatted(question),
                HttpStatus.BAD_REQUEST);
    }
}
