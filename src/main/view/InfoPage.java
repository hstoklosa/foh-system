package main.view;

import main.view.MainView;

import javax.swing.*;
import java.awt.*;

public class InfoPage extends JPanel{
    private MainView parentFrame;

    public InfoPage(MainView parentFrame){
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255));
        createUIElements();
    }

    private void createUIElements(){
        JPanel jp = new JPanel();

        JTextPane infoTextPane = new JTextPane();
        infoTextPane.setEditable(false);
        infoTextPane.setFont(new Font("Open Sans", Font.PLAIN, 14));
        infoTextPane.setContentType("text/html");
        infoTextPane.setText(
                "<html><body style='text-align:center; font-family:Open Sans; font-size:14px; color: #333; background-color: rgb(214, 217, 223);'>" +
                        "<strong>Lancaster's Restaurant Front of House System</strong><br>" +
                        "Version: 1.0<br>" +
                        "Developed by: <strong>Greatest Programming Team (GPT)</strong><br>" +
                        "Created in Collaboration with <strong>Lancaster's Restaurant</strong><br>" +
                        "</body></html>"
        );
        infoTextPane.setBorder(null);
        infoTextPane.setBackground(new Color(214, 217, 223));

        JScrollPane scrollPane = new JScrollPane(infoTextPane);
        scrollPane.setBorder(null);
        scrollPane.setBackground(null);
        jp.add(scrollPane);

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
        xLabel.setFont(new Font("Open Sans", Font.BOLD, 20));

        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        imagePanel.add(logoLabel);
        imagePanel.add(xLabel);
        imagePanel.add(teamLabel);

        jp.add(imagePanel);

        JButton backButton = new JButton("Home");
        backButton.setBackground(new Color(208, 207, 207));
        backButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        backButton.addActionListener(e -> parentFrame.showCard("HomePage"));
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        add(jp, BorderLayout.CENTER);
    }
}
