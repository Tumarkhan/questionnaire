package com.example.anketa;

import com.example.anketa.entities.Questions;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private QuestionsRepository repo;

    public List<Questions> findAll() {
        return repo.findAll();
    }
}
