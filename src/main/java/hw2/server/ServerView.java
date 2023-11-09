package hw2.server;

public interface ServerView {

    /**
     * Метод для отправки сообщения в окно сервера.
     * @param message отправляемое сообщение.
     */
    void sendMessage(String message);

    /**
     * Метод для запуска сервера.
     */
    void startServer();

    /**
     * Метод для остановки сервера.
     */
    void stopServer();
}
