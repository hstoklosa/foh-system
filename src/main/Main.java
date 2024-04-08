package main;

import javax.swing.*;

public class Main { //This is all just filler for right now
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            HomePage homePage = new HomePage();
            homePage.setVisible(true);
        });
    }
}