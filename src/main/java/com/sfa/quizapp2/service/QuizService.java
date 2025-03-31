package com.sfa.quizapp2.service;

import com.sfa.quizapp2.DTO.QuestionDao;
import com.sfa.quizapp2.DTO.QuizDao;
import com.sfa.quizapp2.model.Question;
import com.sfa.quizapp2.model.QuestionWrapper;
import com.sfa.quizapp2.model.Quiz;
import com.sfa.quizapp2.model.Responsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions= questionDao.findRandomQuestionByCategory(category,numQ);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz=quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser=new ArrayList<>();

        for(Question q:questionsFromDB){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(), q.getOption1(), q.getOption2(),
                    q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResults(Integer id, List<Responsed> responses) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int i=0;
        int right=0;
        for(Responsed r:responses){
            if(r.getUserResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }

        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
