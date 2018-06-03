package com.example.anketa.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity(name = "Questions")
public class Questions extends AbstractEntity {

    @NotNull
    @Column(name = "question")
    private String question;

    @Column(name = "topic")
    private String topic;

    public Questions() {
        // An empty constructor is needed for all beans
    }

    public Questions(String question) {
        Objects.requireNonNull(question);
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}


