package hw2.server;

import hw2.client.Client;
import hw2.repo.IRepo;

import java.util.ArrayList;
import java.util.List;

public class Server {


    private final ServerView serverView;
    private final IRepo iRepo;
    private final List<Client> registeredClient = new ArrayList<>();
    private boolean isServerWorking;

    public Server(ServerView serverView, IRepo iRepo) {
        this.serverView = serverView;
        this.iRepo = iRepo;
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
        return iRepo.getHistory();
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
        iRepo.saveInLog(text);
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