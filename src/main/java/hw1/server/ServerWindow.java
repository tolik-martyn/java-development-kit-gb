package hw1.server;

import javax.swing.*;
import java.awt.*;

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

    public static void main(String[] args) {
        new ServerWindow();
    }

    ServerWindow() {
        initializeUI();
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
}