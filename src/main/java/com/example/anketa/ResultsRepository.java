package com.example.anketa;

import com.example.anketa.entities.Answers;
import com.example.anketa.entities.Results;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResultsRepository extends JpaRepository<Results, Long> {
    public static final String FIND_RESULT = "SELECT result FROM results WHERE results.result = :res";
    public static final String FIND_TRUE = "SELECT true_answer FROM answers WHERE answers.id = :id";

    @Query(value = FIND_RESULT, nativeQuery = true)
    public String findByResult (@Param("res") String res);

    @Query(value = FIND_TRUE, nativeQuery = true)
    public String findTrueResult(@Param("id") Long value);
}
