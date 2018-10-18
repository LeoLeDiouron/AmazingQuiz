package app.controller;

public class User {

    private String _name;
    private int _score;
    private int _max_score;

    public User(String name, int max_score) {
        _name = name;
        _max_score = max_score;
        _score = 0;
    }

    public String getName() {
        return _name;
    }

    public int getScore() {
        return _score;
    }

    public void setScore(int score) {
        _score = score;
    }

    public void setMaxScore(int max_score) { _max_score = max_score;}

    public int getMaxScore() {return _max_score;}

    public void increaseScore() {_score++;}
}
