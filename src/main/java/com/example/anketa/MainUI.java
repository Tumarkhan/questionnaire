package com.example.anketa;

import com.example.anketa.entities.Answers;
import com.example.anketa.entities.Questions;
import com.example.anketa.entities.Results;
import com.vaadin.annotations.Theme;
import com.vaadin.data.HasValue;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Theme("valo")
@SpringUI(path = "")
public class MainUI extends UI {

    private QuestionsRepository repo;
    private AnswersRepository ansRepo;
    private ResultsRepository resRepo;

    @Autowired
    QuestionService service;
    private Map<String, Long> results = new HashMap<>();

    public MainUI(QuestionsRepository repo, AnswersRepository ansRepo, ResultsRepository resRepo) {
        this.repo = repo;
        this.ansRepo = ansRepo;
        this.resRepo = resRepo;
    }

    @Override
    protected void init(VaadinRequest request) {
        List<Label> labelsList = new ArrayList<>();
        for(Questions question : getQuestions()){
            Label label = new Label();
            label.setValue(question.getQuestion());
            labelsList.add(label);
        }
        VerticalLayout mainVerticalLayout  = new VerticalLayout();
        mainVerticalLayout.setSpacing(true);
        List<VerticalLayout> vlList = new ArrayList<>();
        for(Questions question : getQuestions()){
            RadioButtonGroup<String> single = new RadioButtonGroup<>(question.getQuestion());
            single.setItems(answersList(question.getId()));
            single.addValueChangeListener(event -> saveRadio(event, question.getId()));
            vlList.add(new VerticalLayout(single));
        }

        for(VerticalLayout vLayout : vlList){
            mainVerticalLayout.addComponent(vLayout);
        }
        Button saveButton = new Button("Save Results");
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                saveResults();
            }
        });
        mainVerticalLayout.addComponent(saveButton);

        setContent(mainVerticalLayout);
    }

    private void saveRadio(HasValue.ValueChangeEvent<String> event, Long ansId) {
        results.put(event.getValue(), ansId);
    }

    private void saveResults() {
        for (Map.Entry<String, Long> entry : results.entrySet()){
            Results entity = new Results();
            entity.setResult(entry.getKey());
            entity.setAnswerId(entry.getValue());
            entity.setCorrectResult(correctRes(entry.getValue()));
            if(isExist(entry.getKey())){
                continue;
            } else {
                resRepo.save(entity);
            }
        }
    }

    private String correctRes(Long value) {
        return resRepo.findTrueResult(value);
    }

    private boolean isExist(String res) {
        String result = resRepo.findByResult(res);
        if(result == null){
            return false;
        } else {
            return true;
        }
    }

    private List<String> answersList(Long id) {
        List<Answers> ansList = ansRepo.findAllById(Collections.singleton(id));//ansRepo.findAnswersById(id);
        List<String> resList = new ArrayList<>();
        for(Answers ans: ansList) {
            resList.add(ans.getTrueAnswer());
            resList.add(ans.getFalseAnswer1());
            resList.add(ans.getFalseAnswer2());
            resList.add(ans.getFalseAnswer3());
        }
        return resList;
    }

    private List<Questions> getQuestions(){
        return repo.findAll();
    }
}
