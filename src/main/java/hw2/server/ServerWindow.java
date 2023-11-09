package hw2.server;

import javax.swing.*;
import java.awt.*;

public class ServerWindow extends JFrame implements ServerView {

    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();

    private static final String SERVER_STOPPED_MESSAGE = "Сервер остановлен.\n";
    private static final String SERVER_NOT_RUNNING_MESSAGE = "Сервер уже был остановлен.\n";
    private static final String SERVER_STARTED_MESSAGE = "Сервер запущен.\n";
    private static final String SERVER_ALREADY_RUNNING_MESSAGE = "Сервер уже был запущен.\n";

    private final Server server;

    public ServerWindow() {
        this.server = new Server(this);
        initializeUI();
    }

    /**
     * Метод для инициализации пользовательского интерфейса сервера.
     */
    private void initializeUI() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        setLayout(new BorderLayout());

        createServerPanel();


        setVisible(true);
    }

    private void createServerPanel() {
        JScrollPane scrollPane = new JScrollPane(log);
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        log.setEditable(false);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(btnStart);
        buttonPanel.add(btnStop);
        add(buttonPanel, BorderLayout.SOUTH);

        btnStart.addActionListener(e -> startServer());
        btnStop.addActionListener(e -> stopServer());
    }

    public Server getServer() {
        return server;
    }

    @Override
    public void sendMessage(String message) {
        log.append(message);
    }

    @Override
    public void startServer() {
        if (server.hasStarted()) {
            log.append(SERVER_STARTED_MESSAGE);
        } else {
            log.append(SERVER_ALREADY_RUNNING_MESSAGE);
        }
    }

    @Override
    public void stopServer() {
        if (server.hasStopped()) {
            log.append(SERVER_STOPPED_MESSAGE);
            server.disconnectAllUser();
        } else {
            log.append(SERVER_NOT_RUNNING_MESSAGE);
        }
    }
}