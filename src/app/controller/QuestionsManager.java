package app.controller;

import app.controller.Question;

import java.util.ArrayList;

public class QuestionsManager {

    private ArrayList<Question> _questions;
    private Integer _idx_question;

    public QuestionsManager() {
        _questions = new ArrayList<>();
        _idx_question = 0;
    }

    public void addQuestion(Question question) {
        _questions.add(question);
    }

    public void increaseIndexQuestion() {
        _idx_question++;
    }

    public Integer getIndexQuestion() {
        return _idx_question;
    }

    public void resetIndexQuestion() {_idx_question=0;}

    public Question getQuestion() {
        return _questions.get(_idx_question);
    }

    public Integer getNumberQuestions() { return _questions.size(); }

    public void clearQuestion() {
        _questions.removeAll(_questions);
    }
}
