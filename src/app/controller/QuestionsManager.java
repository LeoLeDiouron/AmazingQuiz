package app.controller;

import app.controller.Question;

import java.util.ArrayList;

/*
description: the class "QuestionManager" manages the questions selected for the quiz
*/
public class QuestionsManager {

    private ArrayList<Question> _questions;
    private Integer _idx_question;

    /*
    description: constructor of the class, initalises the list of questions
    return: nothing
    params: nothing
    */
    public QuestionsManager() {
        _questions = new ArrayList<>();
        _idx_question = 0;
    }

    /*
    description: adds a question
    return: nothing
    params: Question - question to add to the list
    */
    public void addQuestion(Question question) {
        _questions.add(question);
    }

    /*
    description: increases the index of the list of question (which allow to know the progression of the user in the game)
    return: nothing
    params: nothing
    */
    public void increaseIndexQuestion() {
        _idx_question++;
    }

    /*
    description: get the index of the current question (which allow to know the progression of the user in the game)
    return: Integer - index of the current question
    params: nothing
    */
    public Integer getIndexQuestion() {
        return _idx_question;
    }

    /*
    description: reset the index of the current question (which allow to know the progression of the user in the game)
    return: nothing
    params: nothing
    */
    public void resetIndexQuestion() {_idx_question=0;}

    /*
    description: get the current question
    return: Question - current question
    params: nothing
    */
    public Question getQuestion() {
        return _questions.get(_idx_question);
    }

    /*
    description: get the size of the list of questions
    return: Integer - size of the list of questions
    params: nothing
    */
    public Integer getNumberQuestions() { return _questions.size(); }

    /*
    description: delete all the questions of the list
    return: nothing
    params: nothing
    */
    public void clearQuestion() {
        _questions.removeAll(_questions);
    }
}
