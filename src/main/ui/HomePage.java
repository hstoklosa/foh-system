package main.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomePage extends JPanel{

    public HomePage(){
        setLayout(new BorderLayout());

        this.setLayout(new BorderLayout());


    }

    protected JPanel createContentPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Front of House System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        panel.add(welcomeLabel, BorderLayout.CENTER);

        return panel;
    }

}
