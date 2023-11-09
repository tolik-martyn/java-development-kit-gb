package hw2.client;

public interface ClientView {

    /**
     * Метод для отправки сообщения.
     *
     * @param message отправляемое сообщение.
     */
    void sendMessage(String message);

    /**
     * Метод для отключения клиента от сервера.
     */
    void disconnectFromServer();

    void updateClientList();
}