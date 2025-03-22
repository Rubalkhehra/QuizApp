package com.sfa.quizapp2.controller;



@RestController
@RequestMapping("question")
public class QuestionController {
    @GetMapping("allQuestions")
        return questionService.getAllQuestions();
    }
}
