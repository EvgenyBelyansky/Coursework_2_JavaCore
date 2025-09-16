package org.grtgb.coursework_2_javacore.exception;

import org.springframework.http.HttpStatus;


public class TooManyRequestedQuestionsExceptions extends ExamException {

    public TooManyRequestedQuestionsExceptions(int requested, int available) {
        super(ExamErrorCode.TO_MANY_REQUESTED_QUESTIONS,
                "Запрошенное количество вопросов [%s] больше количества доступных вопросов. Вопросов доступно [%s]"
                        .formatted(requested, available),
                HttpStatus.BAD_REQUEST
        );
    }
}
