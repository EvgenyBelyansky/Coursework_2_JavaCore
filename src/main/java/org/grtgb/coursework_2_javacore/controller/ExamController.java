package org.grtgb.coursework_2_javacore.controller;

import lombok.RequiredArgsConstructor;
import org.grtgb.coursework_2_javacore.qestion.Question;
import org.grtgb.coursework_2_javacore.service.ExaminerServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController("/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExaminerServiceImpl examinerService;

    @GetMapping("/get/{amount}")
    public Collection<Question>getQuestions(@PathVariable Integer amount) {

        return examinerService.getQuestions(amount);
    }

}
