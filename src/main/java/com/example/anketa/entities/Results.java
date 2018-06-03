package com.example.anketa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity(name = "Results")
public class Results extends AbstractEntity {

    @NotNull
    @Column(name = "result")
    private String result;

    @Column(name = "correct_result")
    private String correctResult;

    @Column(name = "answer_id")
    private Long answerId;

    public Results() {
        // An empty constructor is needed for all beans
    }

    public Results(String result) {
        Objects.requireNonNull(result);
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCorrectResult() {
        return correctResult;
    }

    public void setCorrectResult(String correctResult) {
        this.correctResult = correctResult;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}


