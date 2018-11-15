package app.controller;

import app.controller.User;

import java.util.ArrayList;

/*
description: the class "User" manages the list of users
*/
public class UsersManager {

    private ArrayList<User> _users;

    /*
    description: constructor of the class, initalises the list of users
    return: nothing
    params: nothing
    */
    public UsersManager() {
        _users = new ArrayList<>();
    }

    /*
    description: add a user to the list
    return: nothing
    params: User - user to add
    */
    public void addUser(User user) {
        _users.add(user);
    }

    /*
    description: remove a user from the list
    return: nothing
    params: User - user to remove
    */
    public void deleteUser(User user) {
        _users.remove(user);
    }

    /*
    description: get the number of user
    return: Integer - number of users
    params: nothing
    */
    public int countUsers() {
        return _users.size();
    }

    /*
    description: get the list of users
    return: ArrayList<User> - list of users
    params: nothing
    */
    public ArrayList<User> getUsers() {
        return _users;
    }
}
