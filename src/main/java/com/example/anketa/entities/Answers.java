package com.example.anketa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity(name = "Answers")
public class Answers extends AbstractEntity {

    @NotNull
    @Column(name = "true_answer")
    private String trueAnswer;

    @Column(name = "false_answer1")
    private String falseAnswer1;

    @Column(name = "false_answer2")
    private String falseAnswer2;

    @Column(name = "false_answer3")
    private String falseAnswer3;

    public Answers() {
        // An empty constructor is needed for all beans
    }

    public Answers(String question) {
        Objects.requireNonNull(question);
        this.trueAnswer = question;
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public String getFalseAnswer1() {
        return falseAnswer1;
    }

    public void setFalseAnswer1(String falseAnswer1) {
        this.falseAnswer1 = falseAnswer1;
    }

    public String getFalseAnswer2() {
        return falseAnswer2;
    }

    public void setFalseAnswer2(String falseAnswer2) {
        this.falseAnswer2 = falseAnswer2;
    }

    public String getFalseAnswer3() {
        return falseAnswer3;
    }

    public void setFalseAnswer3(String falseAnswer3) {
        this.falseAnswer3 = falseAnswer3;
    }

}


