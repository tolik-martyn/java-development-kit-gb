package hw1;

import hw1.client.ClientGUI;
import hw1.client.User;
import hw1.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        ClientGUI user1 = new ClientGUI(serverWindow, new User("user1", "password1"));
        ClientGUI user2 = new ClientGUI(serverWindow, new User("user2", "password2"));
    }
}