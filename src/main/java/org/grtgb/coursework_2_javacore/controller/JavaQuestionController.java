package org.grtgb.coursework_2_javacore.controller;

import lombok.RequiredArgsConstructor;
import org.grtgb.coursework_2_javacore.qestion.Question;
import org.grtgb.coursework_2_javacore.service.JavaQestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam/java")
public class JavaQuestionController {

    private final JavaQestionService javaQestionService;

    @PostMapping("/add")
    public void addQuestion(@RequestParam String question,
                                              @RequestParam String answer) {
        javaQestionService.addQuestion(question, answer);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeQuestion(@RequestBody Question question) {
        javaQestionService.removeQuestion(question);

        return ResponseEntity.ok("Вопрос удален");
    }

    @GetMapping("/all")
    public Collection<Question> getAllQuestion() {
        return javaQestionService.getAllQuestion();
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public Collection<Question> getRandomQuestion() {
        return javaQestionService.getRandomQuestion(1);
    }
}
