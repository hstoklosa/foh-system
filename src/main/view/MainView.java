package main.view;

import main.controller.FOHController;
import main.view.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainView extends JFrame {
    private final int DEFAULT_WIDTH;
    private final int DEFAULT_HEIGHT;

    private FOHController controller;

    private Color lancasterColor = new Color(43, 51, 54);
    private Color fontColor = new Color(169, 168, 166);
    private Color buttonColor = new Color(208, 207, 207);

    private CardLayout cardLayout = new CardLayout();
    private JPanel cardsPanel = new JPanel(cardLayout);
    private JLabel dateTimeLabel;

    public MainView(int defaultWidth, int defaultHeight, FOHController controller) {
        super("Lancaster's Restaurant - Front of House");
        this.DEFAULT_WIDTH = defaultWidth;
        this.DEFAULT_HEIGHT = defaultHeight;
        this.controller = controller;

        configureUI();
    }

    private void configureUI() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        for (Component comp : cardsPanel.getComponents()) {
            comp.setBackground(lancasterColor);
        }
    }

    static {
        try {
            // SEE: https://stackoverflow.com/questions/5652344/how-can-i-use-a-custom-font-in-java
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();

            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/fonts/OpenSans-Regular.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/fonts/OpenSans-Medium.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/fonts/OpenSans-SemiBold.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/fonts/OpenSans-Bold.ttf")));
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allows frame to exit the app
        setLocationByPlatform(true);
        setResizable(false); // don't let the frame be resized

        TableSelectorPage tablesPage = new TableSelectorPage(this, controller);
        HomePage homePage = new HomePage(controller);

        BookingsPage bookingsPage = new BookingsPage(this, controller);
        PaymentPage paymentPage = new PaymentPage(this);
        InfoPage infoPage = new InfoPage(this);

        homePage.setBackground(lancasterColor);
//        tablesPage.setBackground(lancasterColor);
//        bookingsPage.setBackground(lancasterColor);
//        ordersPage.setBackground(lancasterColor);
//        paymentPage.setBackground(lancasterColor);

        cardsPanel.add(homePage, "HomePage");
        cardsPanel.add(tablesPage, "TablesPage");
        cardsPanel.add(bookingsPage, "BookingsPage");
        cardsPanel.add(paymentPage, "PaymentPage");
        cardsPanel.add(infoPage, "InfoPage");

        this.add(cardsPanel, BorderLayout.CENTER);
        this.add(createHeaderPanel(), BorderLayout.NORTH);
        this.add(createNavigationBar(), BorderLayout.SOUTH);

        showCard("HomePage");

        pack(); // size the frame to fit the view
        setVisible(true);
    }

    public void showCard(String cardName) {
        cardLayout.show(cardsPanel, cardName);
    }

    public void addCard(Component comp, String cardName) {
        cardsPanel.add(comp, cardName);
    }

    protected JPanel createNavigationBar() {
        JPanel navBarPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 0));

        JButton tablesButton = new JButton("Tables");
        JButton bookingsButton = new JButton("Bookings");
        JButton ordersButton = new JButton("Orders");
        JButton paymentButton = new JButton("Payment");
        JButton infoButton = new JButton("Info");

        tablesButton.addActionListener(e -> showCard("TablesPage"));
        bookingsButton.addActionListener(e -> showCard("BookingsPage"));
        ordersButton.addActionListener(e -> showCard("OrdersPage"));
        paymentButton.addActionListener(e -> showCard("PaymentPage"));
        infoButton.addActionListener(e -> showCard("InfoPage"));

        tablesButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        bookingsButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        ordersButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        paymentButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        infoButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));

        tablesButton.setBackground(buttonColor);
        bookingsButton.setBackground(buttonColor);
        ordersButton.setBackground(buttonColor);
        paymentButton.setBackground(buttonColor);
        infoButton.setBackground(buttonColor);

        JButton homeButton = new JButton("Home");
        homeButton.setBackground(new Color(77,170,87));
        homeButton.addActionListener(e -> showCard("HomePage"));

        buttonPanel.add(tablesButton);
        buttonPanel.add(bookingsButton);
        buttonPanel.add(ordersButton);
        buttonPanel.add(paymentButton);
        buttonPanel.add(infoButton);

        buttonPanel.setBackground(lancasterColor);

        navBarPanel.add(buttonPanel, BorderLayout.CENTER);
        navBarPanel.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.GRAY));

        return navBarPanel;
    }

    protected JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(800, 50));

        ImageIcon logo = new ImageIcon("src/resources/images/logo.jpeg");
        Image image = logo.getImage();
        Image newImg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logo = new ImageIcon(newImg);

        JLabel logoLabel = new JLabel(logo);
        headerPanel.add(logoLabel, BorderLayout.WEST);

        JLabel nameLabel = new JLabel("Lancaster's ");
        nameLabel.setFont(new Font("Open Sans", Font.BOLD, 20).deriveFont(20f));
        nameLabel.setForeground(new Color(169, 168, 166));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        headerPanel.add(nameLabel, BorderLayout.CENTER);

        dateTimeLabel = new JLabel();
        dateTimeLabel.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        dateTimeLabel.setForeground(new Color(169, 168, 166));
        dateTimeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        updateDateTime();

        headerPanel.add(dateTimeLabel, BorderLayout.EAST);
        new Timer(100, e -> updateDateTime()).start();

        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.GRAY));
        headerPanel.setBackground(lancasterColor);

        return headerPanel;
    }

    private void updateDateTime() {
        String format = "EEEE, MMMM dd, yyyy HH:mm:ss";
        SimpleDateFormat date = new SimpleDateFormat(format);
        dateTimeLabel.setText(date.format(Calendar.getInstance().getTime()));
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenuItem fileMenu = new JMenu("File");
        JMenuItem homeMenuItem = new JMenuItem("Home");
        homeMenuItem.addActionListener(e -> System.exit(0));

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(homeMenuItem);
        fileMenu.add(exitItem);

        JMenuItem menuMenu = new JMenu("Menu");
        JMenuItem printMenuItem = new JMenuItem("Print");
        printMenuItem.addActionListener(e -> System.exit(0));
        menuMenu.add(printMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(menuMenu);

        return menuBar;
    }

    public FOHController getController() {
        return controller;
    }

    public void setController(FOHController controller) {
        this.controller = controller;
    }
}
