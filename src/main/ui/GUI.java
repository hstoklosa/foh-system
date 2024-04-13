package main.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GUI extends JFrame {
    private JLabel dateTimeLabel;
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardsPanel = new JPanel(cardLayout);
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

        HomePage homePage = new HomePage();


        TablesPage tablesPage = new TablesPage(this);
        BookingsPage bookingsPage = new BookingsPage(this);
        OrdersPage ordersPage = new OrdersPage(this);
        PaymentPage paymentPage = new PaymentPage(this);

        cardsPanel.add(homePage, "HomePage");
        cardsPanel.add(tablesPage, "TablesPage");
        cardsPanel.add(bookingsPage, "BookingsPage");
        cardsPanel.add(ordersPage, "OrdersPage");
        cardsPanel.add(paymentPage, "PaymentPage");

        this.add(cardsPanel, BorderLayout.CENTER);

        add(createHeaderPanel(), BorderLayout.NORTH);

        createMenuBar();
        this.add(createHeaderPanel(), BorderLayout.NORTH);
        this.add(createNavigationBar(), BorderLayout.SOUTH);

        showCard("HomePage");

        pack(); // size the frame to fit the view
        setVisible(true);
    }

    public void showCard(String cardName){
        cardLayout.show(cardsPanel, cardName);
    }

    protected JPanel createNavigationBar(){
        JPanel navBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton tablesButton = new JButton("Tables");
        JButton bookingsButton = new JButton("Bookings");
        JButton ordersButton = new JButton("Orders");
        JButton paymentButton = new JButton("Payment");

        tablesButton.addActionListener(e -> showCard("TablesPage"));
        bookingsButton.addActionListener(e -> showCard("BookingsPage"));
        ordersButton.addActionListener(e -> showCard("OrdersPage"));
        paymentButton.addActionListener(e -> showCard("PaymentPage"));

        tablesButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        bookingsButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        ordersButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        paymentButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));

        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(e -> showCard("ContentPanel"));

        navBarPanel.add(tablesButton);
        navBarPanel.add(bookingsButton);
        navBarPanel.add(ordersButton);
        navBarPanel.add(paymentButton);

        return navBarPanel;
    }

    protected JPanel createHeaderPanel(){
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(800, 50));

        JLabel nameLabel = new JLabel("Lancaster's");
        nameLabel.setFont(new Font("Open Sans", Font.BOLD, 20).deriveFont(20f));

        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        dateTimeLabel = new JLabel();
        dateTimeLabel.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        updateDateTime();

        dateTimeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        headerPanel.add(nameLabel, BorderLayout.WEST);
        headerPanel.add(dateTimeLabel, BorderLayout.EAST);

        new Timer(100, e -> updateDateTime()).start();

        return headerPanel;
    }

    private void updateDateTime(){
        String format = "EEEE, MMMM dd, yyyy HH:mm:ss";
        SimpleDateFormat date = new SimpleDateFormat(format);

        dateTimeLabel.setText(date.format(Calendar.getInstance().getTime()));
    }

    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenuItem fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        return menuBar;
    }
}
