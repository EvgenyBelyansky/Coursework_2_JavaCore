package org.grtgb.coursework_2_javacore.exception;

import org.grtgb.coursework_2_javacore.qestion.Question;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class QuestionNotFoundException extends ExamException {

    public QuestionNotFoundException(Question question) {
        super(ExamErrorCode.QUESTION_NOT_FOUND,
                "Запрашиваемый вопрос [%s] не найден!!".formatted(question),
                HttpStatus.NOT_FOUND
        );
    }
}
