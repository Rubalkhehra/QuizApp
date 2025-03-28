package com.sfa.quizapp2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("question")
public class QuestionController {
    @GetMapping("allQuestions")
    public String getAllQuestions(){
        return questionService.getAllQuestions();
    }
}
