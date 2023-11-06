package hw1.server;

import hw1.client.User;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame {

    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    private boolean isServerWorking;

    private static final String SERVER_STOPPED_MESSAGE = "Server stopped.\n";
    private static final String SERVER_NOT_RUNNING_MESSAGE = "Server is not running.\n";
    private static final String SERVER_STARTED_MESSAGE = "Server started.\n";
    private static final String SERVER_ALREADY_RUNNING_MESSAGE = "Server is already running.\n";

    private final List<User> registeredUsers;

    public ServerWindow() {
        initializeUI();
        registeredUsers = new ArrayList<>();
        registerUser(new User("user1", "password1"));
        registerUser(new User("user2", "password2"));
    }

    private void initializeUI() {
        isServerWorking = false;

        btnStop.addActionListener(e -> stopServer());

        btnStart.addActionListener(e -> startServer());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(log);
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        log.setEditable(false);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(btnStart);
        buttonPanel.add(btnStop);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void startServer() {
        if (!isServerWorking) {
            isServerWorking = true;
            log.append(SERVER_STARTED_MESSAGE);
            // System.out.print(SERVER_STARTED_MESSAGE);
        } else {
            log.append(SERVER_ALREADY_RUNNING_MESSAGE);
            // System.out.print(SERVER_ALREADY_RUNNING_MESSAGE);
        }
    }

    private void stopServer() {
        if (isServerWorking) {
            isServerWorking = false;
            log.append(SERVER_STOPPED_MESSAGE);
            // System.out.print(SERVER_STOPPED_MESSAGE);
        } else {
            log.append(SERVER_NOT_RUNNING_MESSAGE);
            // System.out.print(SERVER_NOT_RUNNING_MESSAGE);
        }
    }

    public void writeToChatHistory(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/java/hw1/chat_history.txt", true))) {
            writer.write(message + "\n");
        } catch (IOException e) {
            System.err.println("Error when writing a message to a file");
        }
    }

    public String readChatHistory() {
        StringWriter chatHistory = new StringWriter();

        try (BufferedReader reader = new BufferedReader(new FileReader("./src/main/java/hw1/chat_history.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                chatHistory.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error when reading data from a file");
        }

        return chatHistory.toString();
    }

    public boolean isSendMessage(String successMessage, String failedMessage, String currentTime) {
        if (isServerWorking) {
            // TODO: 06.11.2023 Реализовать показ сообщения всем клиентам.
            log.append(currentTime + successMessage + "\n");
            writeToChatHistory(currentTime + successMessage);
            return true;
        } else {
            log.append(currentTime + failedMessage);
            return false;
        }
    }

    public void registerUser(User user) {
        registeredUsers.add(user);
    }

    public boolean authenticateUser(String username, String password) {
        for (User user : registeredUsers) {
            if (user.username().equals(username) && user.password().equals(password)) {
                return true;
            }
        }
        return false;
    }
}