package app.controller;

/*
description: the class "User" manages everything about the characteristics and the actions of the user
*/
public class User {

    private String _name;
    private int _score;
    private int _max_score;

    /*
    description: constructor of the class, initialises the attributes
    return: nothing
    params: nothing
    */
    public User(String name, int max_score) {
        _name = name;
        _max_score = max_score;
        _score = 0;
    }

    /*
    description: change the name of the user
    return: nothing
    params: String - the new name of the user
    */
    public void setName(String name) {
        _name = name;
    }

    /*
    description: get the name of the user
    return: String - name of the user
    params: nothing
    */
    public String getName() {
        return _name;
    }

    /*
    description: get the current score of the user
    return: Integer - score of the user
    params: nothing
    */
    public int getScore() {
        return _score;
    }

    /*
    description: set score of the user
    return: nothing
    params: Integer - score
    */
    public void setScore(int score) {
        _score = score;
    }

    /*
    description: set best score of the user
    return: nothing
    params: Integer - best score
    */
    public void setMaxScore(int max_score) { _max_score = max_score;}

    /*
    description: get best score of the user
    return: Integer - best score of the user
    params: nothing
    */
    public int getMaxScore() {return _max_score;}

    /*
    description: increases the current score of the user
    return: nothing
    params: nothing
    */
    public void increaseScore() {_score++;}
}
