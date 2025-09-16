package org.grtgb.coursework_2_javacore.exception;

import org.springframework.http.HttpStatus;

public class ArgumentLessZeroException extends ExamException {
    public ArgumentLessZeroException(int argument, int setSize) {
        super(ExamErrorCode.REQUESTED_ARGUMENT_LESS_ZERO,
                "Запрашиваемое число вопросов [%s] меньше 0, допустимый для запроса диапазон [0 - %s]".formatted(argument, setSize),
                HttpStatus.BAD_REQUEST);
    }
}
