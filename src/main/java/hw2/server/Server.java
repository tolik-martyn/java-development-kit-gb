package hw2.server;

import hw2.client.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server {


    private final ServerView serverView;
    private final List<Client> registeredClient = new ArrayList<>();
    private static final String LOG_PATH = "src/main/java/hw2/chat_history.txt";
    private boolean isServerWorking;

    public Server(ServerView serverView) {
        this.serverView = serverView;
    }

    public boolean connectClient(Client client) {
        if (!isServerWorking) return false;
        registeredClient.add(client);
        return true;
    }

    /**
     * Метод для чтения истории чата из файла.
     *
     * @return История чата в виде строки.
     */
    public String getHistory() {
        StringWriter chatHistory = new StringWriter();
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                chatHistory.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении данных из файла.");
        }
        String result = chatHistory.toString();
        if (result.isEmpty()) return "История чатов пустая.\n";
        return result;
    }

    public void disconnectClient(Client client) {
        registeredClient.remove(client);
        if (client != null) {
            client.disconnect();
        }
    }

    public void disconnectAllUser() {
        while (!registeredClient.isEmpty()) {
            disconnectClient(registeredClient.get(0));
        }
    }

    public void sendMessage(String text) {
        if (!isServerWorking) {
            return;
        }
        serverView.sendMessage(text);
        answerAll(text);
        saveInLog(text);
    }

    public void answerAll(String text) {
        for (Client client : registeredClient) {
            client.serverAnswer(text);
        }
    }

    /**
     * Метод для записи сообщения в файл.
     *
     * @param text - данные, которые нужно записать.
     */
    private void saveInLog(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_PATH, true))) {
            writer.write(text);
        } catch (IOException e) {
            System.err.println("Ошибка при записи данных в файл");
        }
    }

    public boolean hasStarted() {
        if (!isServerWorking) {
            isServerWorking = true;
            return true;
        }
        return false;
    }

    public boolean hasStopped() {
        if (isServerWorking) {
            isServerWorking = false;
            return true;
        }
        return false;
    }

    public List<Client> getRegisteredClient() {
        return registeredClient;
    }

    public void updateAllClientList() {
        for (Client client : registeredClient) {
            client.updateClientList();
        }
    }
}