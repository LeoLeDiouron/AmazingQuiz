package app.controller;

import java.util.ArrayList;

/*
description: the class "Question" manages everything about the characteristics and the actions of a question
*/
public class Question {

    private String _author;
    private String _content;
    private ArrayList<String> _answers;
    private Integer[] _good_answer;
    private Integer _type_quiz;

    /*
    description: constructor of the class, initialises the attributes
    return: nothing
    params: String author - the name of the author of the question
    params: String content - the question
    params: ArrayList<String> answers - the avaible answers for the question
    params: Integer[] good_answers - list of index of the good answer(s)
    params: Integer type_quiz - the index of the type of question
    */
    public Question(String author, String content, ArrayList<String> answers, Integer[] good_answer, Integer type_quiz) {
        _author = author;
        _content = content;
        _answers = answers;
        _good_answer = good_answer;
        _type_quiz = type_quiz;
    }

    /*
    description: get the name of the author of the question
    return: String - the name of the author of the question
    params: nothing
    */
    public String getAuthor() {
        return _author;
    }

    /*
    description: get the content of the question
    return: String - the content of the question
    params: nothing
    */
    public String getContent() {
        return _content;
    }

    /*
    description: get the good answer of the question
    return: String - the good answer of the question
    params: nothing
    */
    public String getGoodAnswer() {
        if (_type_quiz == 3 && _good_answer.length > 1) {
            String result = "";
            for (int i = 0; i<_good_answer.length;i++) {
                result += _answers.get(_good_answer[i]) + ";";
            }
            return result.substring(0, result.length()-1);
        }
        return _answers.get(_good_answer[0]);
    }

    /*
    description: get the list of the index of the good answers of the question
    return: Integer[] - the list of the index of the good answers
    params: nothing
    */
    public Integer[] getIndexGoodAnswer() {
        return _good_answer;
    }

    /*
    description: get the list of answers of the question
    return: ArrayList<String> - the list of answers of the question
    params: nothing
    */
    public ArrayList<String> getAnswers() {
        return _answers;
    }

    /*
    description: get the type of the question
    return: Integer - the type of the question
    params: nothing
    */
    public Integer getTypeQuiz() {
        return _type_quiz;
    }

    /*
    description: check if the given answer is the good one
    return: boolean - result of the check
    params: String - answer giver by the user
    */
    public boolean checkGoodAnswers(String answer) {
        if (_type_quiz == 3)
            return checkAnswersCheckBox(answer);
        return _answers.get(_good_answer[0]).equals(answer);
    }

    /*
    description: check if the given answer(s) is(are) the good one(s) (used only in case of checkbox question)
    return: boolean - result of the check
    params: String - answer giver by the user
    */
    public boolean checkAnswersCheckBox(String answer) {
        String[] answers = answer.split(";");

        for (int i = 0; i<_good_answer.length;i++) {
            boolean is_present = false;
            for (int j = 0; j<answers.length;j++) {
                if (_answers.get(_good_answer[i]).equals(answers[j])) {
                    is_present = true;
                }
            }
            if (is_present == false)
                return false;
        }
        return true;
    }
}
