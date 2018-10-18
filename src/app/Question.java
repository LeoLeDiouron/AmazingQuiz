package app;

import java.util.ArrayList;

public class Question {

    private String _author;
    private String _content;
    private ArrayList<String> _answers;
    private Integer[] _good_answer;
    private Integer _type_quiz;

    public Question(String author, String content, ArrayList<String> answers, Integer[] good_answer, Integer type_quiz) {
        _author = author;
        _content = content;
        _answers = answers;
        _good_answer = good_answer;
        _type_quiz = type_quiz;
    }

    public String getAuthor() {
        return _author;
    }

    public String getContent() {
        return _content;
    }

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

    public Integer[] getIndexGoodAnswer() {
        return _good_answer;
    }

    public ArrayList<String> getAnswers() {
        return _answers;
    }

    public Integer getTypeQuiz() {
        return _type_quiz;
    }

    public boolean checkGoodAnswers(String answer) {
        if (_type_quiz == 3)
            return checkAnswersCheckBox(answer);
        return _answers.get(_good_answer[0]).equals(answer);
    }

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
