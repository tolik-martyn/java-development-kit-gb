package hw2.client;

import hw2.server.Server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Client {

    private String name;
    private final ClientView clientView;
    private final Server server;
    private boolean isConnected;

    public Client(ClientView clientView, Server server) {
        this.clientView = clientView;
        this.server = server;
    }

    /**
     * Метод для подключения к серверу.
     *
     * @param login логин пользователя.
     * @return true - при успешном подключении, иначе false.
     */
    public boolean connectToServer(String login) {
        this.name = login;
        if (server.connectClient(this)) {
            isConnected = true;
            printText(getCurrentTime() + "Вы успешно подключились!\n");
            String log = server.getHistory();
            if (log != null) {
                printText(log);
            }
            server.answerAll(this.name + " online...\n");
            server.updateAllClientList();
            return true;
        } else {
            printText(getCurrentTime() + "Подключение не удалось.\n");
            return false;
        }
    }

    /**
     * Метод для отключения клиента от сервера.
     */
    public void disconnect() {
        if (isConnected) {
            isConnected = false;
            server.answerAll(this.name + " offline...\n");
            clientView.disconnectFromServer();
            server.disconnectClient(this);
            printText(getCurrentTime() + "Вы были отключены от сервера!\n");
            server.updateAllClientList();
        }
    }

    /**
     * Метод отправки сообщения клиентом на сервер.
     *
     * @param message отправляемое сообщение.
     */
    public void sendMessage(String message) {
        if (isConnected) {
            if (!message.isEmpty()) {
                server.sendMessage(getCurrentTime() + name + ": " + message + "\n");
            }
        } else {
            printText(getCurrentTime() + "Нет подключения к серверу.\n");
        }
    }

    /**
     * Метод для получения сообщения (ответа) от сервера
     *
     * @param answer ответ от сервера
     */
    public void serverAnswer(String answer) {
        printText(answer);
    }

    /**
     * Метод для вывода сообщения (текста).
     *
     * @param text выводимый текст.
     */
    private void printText(String text) {
        clientView.sendMessage(text);
    }

    /**
     * Возвращает текущее время в формате "dd.MM.yyyy HH:mm:ss, ".
     *
     * @return Текущее время в виде строки.
     */
    private String getCurrentTime() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return dateFormat.format(currentDate) + ", ";
    }

    public List<Client> getRegisteredClient() {
        return server.getRegisteredClient();
    }

    public String getName() {
        return name;
    }

    public void updateClientList() {
        clientView.updateClientList();
    }
}