package com.example.anketa;

import com.example.anketa.entities.Questions;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;

@Theme("valo")
@SpringUI(path = "/questions")
public class AddQuestionsUI extends UI {
    private final QuestionsRepository repo;
    final Grid<Questions> grid;

    public AddQuestionsUI(QuestionsRepository repo) {
        this.repo = repo;
        this.grid = new Grid<>(Questions.class);
    }

    @Override
    protected void init(VaadinRequest request) {
        Label questionLabel = new Label("Add your question here");
        TextField questionTF = new TextField();
        questionTF.setSizeFull();

        Button add = new Button("Add");
        add.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                repo.save(generateNewQuestion(questionTF.getValue()));
                questionTF.setValue("");
                listCustomers();
            }
        });

        setContent(new FormLayout(questionLabel, questionTF, add, grid));
        listCustomers();
    }

    private Questions generateNewQuestion(String question) {
        Questions entity = new Questions(question);
        entity.setTopic("math");
        return entity;
    }

    private void listCustomers() {
        grid.setItems(repo.findAll());
    }
}
