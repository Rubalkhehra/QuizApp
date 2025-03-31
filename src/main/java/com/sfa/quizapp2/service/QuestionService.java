package com.sfa.quizapp2.service;

import com.sfa.quizapp2.DTO.QuestionDao;
import com.sfa.quizapp2.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category){
        Optional<List<Question>> optionalQuestion= Optional.ofNullable(questionDao.findByCategoryIgnoreCase(category));
        if(optionalQuestion.isPresent()){
            return new ResponseEntity<>(optionalQuestion.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try{
            questionDao.save(question);
            return new ResponseEntity<>("Question added successfully",HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Error adding question"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
