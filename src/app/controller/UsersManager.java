package app.controller;

import app.controller.User;

import java.util.ArrayList;

public class UsersManager {

    private ArrayList<User> _users;

    public UsersManager() {
        _users = new ArrayList<>();
    }

    public void addUser(User user) {
        _users.add(user);
    }

    public void deleteUser(User user) {
        _users.remove(user);
    }

    public int countUsers() {
        return _users.size();
    }

    public ArrayList<User> getUsers() {
        return _users;
    }
}
