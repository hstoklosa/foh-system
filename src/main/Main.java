package main;

import main.ui.GUI;
import main.ui.HomePage;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomePage::new);
    }
}
