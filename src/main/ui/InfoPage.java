package main.ui;

import javax.swing.*;
import java.awt.*;

public class InfoPage extends JPanel{
    private GUI parentFrame;

    public InfoPage(GUI parentFrame){
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUIElements();
    }

    private void createUIElements(){
        JTextArea infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        infoTextArea.setFont(new Font("Open Sans", Font.PLAIN, 14));
        infoTextArea.setText(
                "Lancaster's Restaurant Front of House System\n" +
                        "Version: 1.0\n" +
                        "Developed by: Greatest Programming Team (GPT)\n" +
                        "Created in Collaboration with Lancaster's Restaurant\n"
        );

        ImageIcon lanLogo = new ImageIcon("src/resources/images/logo.jpeg");
        Image image = lanLogo.getImage();
        Image newImg = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        lanLogo = new ImageIcon(newImg);

        ImageIcon gptLogo = new ImageIcon("src/resources/images/gptlogo.png");
        Image imageG = gptLogo.getImage();
        Image newImgG = imageG.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        gptLogo = new ImageIcon(newImgG);

        JLabel logoLabel = new JLabel(lanLogo);
        JLabel teamLabel = new JLabel(gptLogo);
        JLabel xLabel = new JLabel(" X ", JLabel.CENTER);

        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        imagePanel.add(logoLabel);
        imagePanel.add(xLabel);
        imagePanel.add(teamLabel);

        JScrollPane scrollPane = new JScrollPane(infoTextArea);
        add(scrollPane, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.SOUTH);

        JButton backButton = new JButton("Home");
        backButton.setBackground(new Color(208, 207, 207));
        backButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        backButton.addActionListener(e -> parentFrame.showCard("HomePage"));
        add(backButton, BorderLayout.NORTH);
    }
}
