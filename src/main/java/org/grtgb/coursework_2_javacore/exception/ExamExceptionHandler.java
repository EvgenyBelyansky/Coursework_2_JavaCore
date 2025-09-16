package org.grtgb.coursework_2_javacore.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExamExceptionHandler {

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
