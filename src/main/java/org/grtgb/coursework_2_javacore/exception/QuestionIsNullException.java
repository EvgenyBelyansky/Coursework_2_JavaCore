package org.grtgb.coursework_2_javacore.exception;

import org.springframework.http.HttpStatus;

public class QuestionIsNullException extends ExamException {
    public QuestionIsNullException() {
        super(ExamErrorCode.QUESTION_IS_NULL,
                "Передан нулевой параметр, пожалуйста введите корректные данные!!",
                HttpStatus.BAD_REQUEST);
    }
}
