package com.example.anketa;

import com.example.anketa.entities.Answers;
import com.example.anketa.entities.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswersRepository extends JpaRepository<Answers, Long> {
    public static final String FIND_ANSWERS = "SELECT true_answer, false_answer1, false_answer2, false_answer3" +
            " FROM answers WHERE answers.id = :id";

    @Query(value = FIND_ANSWERS, nativeQuery = true)
    public List<Answers> findAnswersById (@Param("id") Long id);
}
