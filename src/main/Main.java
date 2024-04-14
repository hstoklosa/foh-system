package main;

import main.controller.FOHController;
import main.ui.GUI;

public class Main {

    public static void main(String[] args) {
        FOHController foh = new FOHController();
        GUI gui = new GUI(800, 600, foh);
        gui.start();
    }
}
