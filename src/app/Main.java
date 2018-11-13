package app;

import app.controller.App;

/*
description: The class "Main" is used to launch the program
*/
public class Main {
    /*
    description: the function is used to create an object "app" and launch the project
    return: nothing
    params: nothing
    */
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}
