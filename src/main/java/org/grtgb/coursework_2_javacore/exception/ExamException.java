package org.grtgb.coursework_2_javacore.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Getter
public abstract class ExamException extends RuntimeException {

    private final ExamErrorCode code;
    private final HttpStatus httpStatus;

    public ExamException(ExamErrorCode code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = Optional.ofNullable(httpStatus).orElse(HttpStatus.I_AM_A_TEAPOT);
    }
}
