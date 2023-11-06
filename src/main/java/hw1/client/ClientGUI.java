package hw1.client;

import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();
    private final JList<String> userList = new JList<>();

    private final JPanel panelTop = new JPanel(new GridLayout(2, 1));
    private final JPanel topRow1 = new JPanel(new GridLayout(1, 3));
    private final JPanel topRow2 = new JPanel(new GridLayout(1, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField("login");
    private final JPasswordField tfPassword = new JPasswordField("password");
    private final JButton btnLogin = new JButton("Log in");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    public static void main(String[] args) {
        new ClientGUI();
    }

    ClientGUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

        topRow1.add(tfIPAddress);
        topRow1.add(tfPort);
        topRow2.add(tfLogin);
        topRow2.add(tfPassword);
        topRow2.add(btnLogin);

        panelTop.add(topRow1);
        panelTop.add(topRow2);
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        JScrollPane scrollLog = new JScrollPane(log);
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        log.setEditable(false);
        add(scrollLog);

        DefaultListModel<String> userListModel = new DefaultListModel<>();
        userListModel.addElement("George");
        userListModel.addElement("Nicholas");
        userListModel.addElement("Anna");
        userListModel.addElement("Polina");
        userList.setModel(userListModel);

        JScrollPane userListScrollPane = new JScrollPane(userList);
        userListScrollPane.setPreferredSize(new Dimension(100, 0));
        add(userListScrollPane, BorderLayout.WEST);

        setVisible(true);
    }
}
