package hw2.client;

import hw2.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.List;

public class ClientGUI extends JFrame implements ClientView {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 1));
    private final JPanel topRow1 = new JPanel(new GridLayout(1, 3));
    private final JPanel topRow2 = new JPanel(new GridLayout(1, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField("Login");
    private final JPasswordField tfPassword = new JPasswordField("password");
    private final JButton btnLogin = new JButton("Log in");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    DefaultListModel<String> userListModel = new DefaultListModel<>();
    private final JList<String> userList = new JList<>();

    private final Client client;

    public ClientGUI(ServerWindow serverWindow) {
        client = new Client(this, serverWindow.getServer());

        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat Client");

        createTopPanel();
        createBottomPanel();
        createLogPanel();
        createUserListPanel();

        setVisible(true);
    }

    private void connectToServer() {
        if (client.connectToServer(tfLogin.getText())) {
            panelTop.setVisible(false);
        }
    }

    public void sendMessage() {
        client.sendMessage(tfMessage.getText());
        tfMessage.setText("");
    }

    @Override
    public void sendMessage(String message) {
        log.append(message);
    }

    @Override
    public void disconnectFromServer() {
        panelTop.setVisible(true);
        client.disconnect();
    }

    /**
     * Метод отключения при закрытии пользователем окна (через крестик).
     *
     * @param e the window event
     */
    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            disconnectFromServer();
        }
    }

    /**
     * Метод, который создает верхнюю панель интерфейса с полями ввода адреса сервера, порта, логина и пароля.
     */
    private void createTopPanel() {
        btnLogin.addActionListener(e -> connectToServer());

        topRow1.add(tfIPAddress);
        topRow1.add(tfPort);
        topRow2.add(tfLogin);
        topRow2.add(tfPassword);
        topRow2.add(btnLogin);

        panelTop.add(topRow1);
        panelTop.add(topRow2);
        add(panelTop, BorderLayout.NORTH);
    }

    /**
     * Метод, который создает нижнюю панель интерфейса с полем для ввода сообщения и кнопкой отправки.
     */
    private void createBottomPanel() {
        tfMessage.addActionListener(e -> sendMessage());
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        btnSend.addActionListener(e -> sendMessage());
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);
    }

    /**
     * Метод, который создает панель для вывода сообщений чата.
     */
    private void createLogPanel() {
        JScrollPane scrollLog = new JScrollPane(log);
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        log.setEditable(false);
        add(scrollLog);
    }

    /**
     * Метод, который создает панель для списка пользователей чата.
     */
    private void createUserListPanel() {
        userList.setModel(userListModel);
        JScrollPane userListScrollPane = new JScrollPane(userList);
        userListScrollPane.setPreferredSize(new Dimension(100, 0));
        add(userListScrollPane, BorderLayout.WEST);
    }

    public void updateClientList() {
        userListModel.clear();
        List<Client> users = client.getRegisteredClient();
        for (Client user : users) {
            if (user == this.client) {
                userListModel.addElement(user.getName() + " (Вы)");
            } else {
                userListModel.addElement(user.getName());
            }
        }
    }
}