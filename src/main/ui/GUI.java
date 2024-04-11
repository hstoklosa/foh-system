package main.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {
    private final int DEFAULT_WIDTH;
    private final int DEFAULT_HEIGHT;

    public GUI(int defaultWidth, int defaultHeight) {
        this.DEFAULT_WIDTH = defaultWidth;
        this.DEFAULT_HEIGHT = defaultHeight;
    }

    static {
        try {
            // SEE: https://stackoverflow.com/questions/5652344/how-can-i-use-a-custom-font-in-java
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();

            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/fonts/OpenSans-Italic.ttf")));
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allows frame to exit the app
        setLocationByPlatform(true);
        setResizable(false); // don't let the frame be resized
        pack(); // size the frame to fit the view
        setVisible(true);
    }
}
