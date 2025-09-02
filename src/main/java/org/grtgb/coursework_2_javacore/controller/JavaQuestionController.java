package org.grtgb.coursework_2_javacore.controller;

import lombok.RequiredArgsConstructor;
import org.grtgb.coursework_2_javacore.qestion.Question;
import org.grtgb.coursework_2_javacore.service.JavaQestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/exam/java")
@RequiredArgsConstructor
public class JavaQuestionController {

    private final JavaQestionService javaQestionService;

    @PostMapping("/new")
    public ResponseEntity<String> addQuestion(@RequestParam String question,
                                              @RequestParam String answer) {
        javaQestionService.addQuestion(question, answer);

        return ResponseEntity.ok("Вопрос добавлен");
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeQuestion(@RequestBody Question question) {
        javaQestionService.removeQuestion(question);

        return ResponseEntity.ok("Вопрос удален");
    }

    @RequestMapping("/all")
    public void getAllQuestion() {
        javaQestionService.getAllQuestion();
    }

    @RequestMapping("/random")
    public void getRandomQuestion() {
        javaQestionService.getRandomQuestion();
    }
}
