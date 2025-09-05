package org.grtgb.coursework_2_javacore.qestion;


import lombok.*;


@Data
@NoArgsConstructor
public class Question {

    @Setter(value = AccessLevel.PRIVATE)
    private String question;

    private String answer;

    public Question(String question, String answer) {
        if (question.isBlank() || answer.isBlank()) {
            throw new RuntimeException("Вопрос и ответ должны быть заполнены!!!");
        }
        this.question = question;
        this.answer = answer;
    }
}


