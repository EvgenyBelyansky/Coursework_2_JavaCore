package org.grtgb.coursework_2_javacore.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ExamError {

    private final ExamErrorCode code;

    private final String message;
}
