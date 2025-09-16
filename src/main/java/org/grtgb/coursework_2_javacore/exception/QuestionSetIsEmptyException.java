package org.grtgb.coursework_2_javacore.exception;

import org.springframework.http.HttpStatus;

public class QuestionSetIsEmptyException extends ExamException {

    public QuestionSetIsEmptyException() {
        super(ExamErrorCode.QUESTION_SET_IS_EMPTY,
                "Вопросы отсутствуют, нужно создать новые",
                HttpStatus.NOT_FOUND
        );
    }
}