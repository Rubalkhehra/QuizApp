package com.sfa.quizapp2.model;

import lombok.Data;

@Data
public class QuestionWrapper {
    private int id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public QuestionWrapper(int id, String title, String option1, String option2, String option4, String option3) {
        this.id = id;
        this.questionTitle=title;
        this.option1 = option1;
        this.option2 = option2;
        this.option4 = option4;
        this.option3 = option3;
    }
}
