package hw1.client;

import hw1.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();
    private final JList<String> userList = new JList<>();

    private final JPanel panelTop = new JPanel(new GridLayout(2, 1));
    private final JPanel topRow1 = new JPanel(new GridLayout(1, 3));
    private final JPanel topRow2 = new JPanel(new GridLayout(1, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin;
    private final JPasswordField tfPassword;
    private final JButton btnLogin = new JButton("Log in");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    private final ServerWindow serverWindow;
    private boolean isAuthenticated = false;
    DefaultListModel<String> userListModel = new DefaultListModel<>();

    private static final String SUCCESSFUL_CONNECTION = "You have successfully connected!\n";
    private static final String FAILED_CONNECTION = "Connection failed. Server is not running.\n";
    private static final String SUCCESSFUL_LOGGED = "You have successfully logged in!\n";
    private static final String FAILED_LOGGED = "Authentication failed. Check your credentials.\n";
    private static final String SERVER_IS_NOT_RUNNING = "Server is not running. Cannot send the message.\n";

    public ClientGUI(ServerWindow serverWindow, User user) {
        this.serverWindow = serverWindow;
        tfLogin = new JTextField(user.username());
        tfPassword = new JPasswordField(user.password());

        btnLogin.addActionListener(e -> {
            String username = tfLogin.getText();
            String password = new String(tfPassword.getPassword());

            if (serverWindow.authenticateUser(username, password)) {
                updateChatHistory();
                log.append(getCurrentTime() + SUCCESSFUL_LOGGED);
                isAuthenticated = true;
                userListModel.addElement(username);
            } else {
                log.append(getCurrentTime() + FAILED_LOGGED);
            }
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

        createTopPanel();
        createBottomPanel();
        createLogPanel();
        createUserListPanel();

        setVisible(true);


        btnSend.addActionListener(e -> {
            if (isAuthenticated) {
                String message = tfLogin.getText() + ": " + tfMessage.getText();
                log.append(getCurrentTime() + message + "\n");
                sendMessage(message, SERVER_IS_NOT_RUNNING);
                tfMessage.setText("");
            }
        });

        tfMessage.addActionListener(e -> {
            if (isAuthenticated) {
                String message = tfLogin.getText() + ": " + tfMessage.getText();
                log.append(getCurrentTime() + message + "\n");
                sendMessage(message, SERVER_IS_NOT_RUNNING);
                tfMessage.setText("");
            }
        });

    }

    private void createTopPanel() {
        topRow1.add(tfIPAddress);
        topRow1.add(tfPort);
        topRow2.add(tfLogin);
        topRow2.add(tfPassword);
        topRow2.add(btnLogin);

        panelTop.add(topRow1);
        panelTop.add(topRow2);
        add(panelTop, BorderLayout.NORTH);
    }

    private void createBottomPanel() {
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);
    }

    private void createLogPanel() {
        JScrollPane scrollLog = new JScrollPane(log);
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        log.setEditable(false);
        add(scrollLog);
    }

    private void createUserListPanel() {
        userListModel.addElement("George");
        userListModel.addElement("Nicholas");
        userListModel.addElement("Anna");
        userListModel.addElement("Polina");
        userList.setModel(userListModel);

        JScrollPane userListScrollPane = new JScrollPane(userList);
        userListScrollPane.setPreferredSize(new Dimension(100, 0));
        add(userListScrollPane, BorderLayout.WEST);
    }

    public void sendMessage(String successMessage, String failedMessage) {
        String currentTime = getCurrentTime();
        if (!serverWindow.isSendMessage(successMessage, failedMessage, currentTime)) {
            log.append(currentTime + failedMessage);
        }
    }

    public void updateChatHistory() {
        log.setText(serverWindow.readChatHistory());
    }

    public String getCurrentTime() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return dateFormat.format(currentDate) + ", ";
    }
}
