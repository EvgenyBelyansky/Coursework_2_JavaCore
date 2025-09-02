package org.grtgb.coursework_2_javacore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class QuestionNotFoundException extends ExamException {

    public QuestionNotFoundException() {
        super(ExamErrorCode.QUESTION_NOT_FOUND,
                "Запрашиваемый вопрос не найден!!",
                HttpStatus.NOT_FOUND
        );
    }
}
