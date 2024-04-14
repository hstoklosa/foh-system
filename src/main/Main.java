package main;

import main.controller.FOHController;
import main.view.MainView;

public class Main {

    public static void main(String[] args) {
        FOHController foh = new FOHController();
        MainView gui = new MainView(800, 600, foh);
        gui.start();
    }
}
