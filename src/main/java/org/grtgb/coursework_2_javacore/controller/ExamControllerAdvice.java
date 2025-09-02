package org.grtgb.coursework_2_javacore.controller;

import org.grtgb.coursework_2_javacore.exception.ExamError;
import org.grtgb.coursework_2_javacore.exception.ExamException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExamControllerAdvice {

    @ExceptionHandler(ExamException.class)
    public ResponseEntity<ExamError> handleExamException(ExamException e) {
        ExamError error = new ExamError(
                e.getCode(),
                e.getMessage()
        );

        return ResponseEntity
                .status(e.getHttpStatus())
                .body(error);
    }


}
