package org.grtgb.coursework_2_javacore.controller;

import lombok.RequiredArgsConstructor;
import org.grtgb.coursework_2_javacore.qestion.Question;
import org.grtgb.coursework_2_javacore.service.ExaminerServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam")
public class ExamController {

    private final ExaminerServiceImpl examinerService;

    @GetMapping("/get")
    public Collection<Question>getQuestions(@RequestParam int amount) {

        return examinerService.getQuestions(amount);
    }

}
