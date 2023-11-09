package hw2.repo;

import java.io.*;

public class FileRepo implements IRepo {
    private static final String LOG_PATH = "src/main/java/hw2/repo/chat_history.txt";

    @Override
    public String getHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_PATH))) {
            StringBuilder chatHistory = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                chatHistory.append(line).append("\n");
            }
            if (!chatHistory.isEmpty()) {
                return chatHistory.toString();
            } else {
                return "История чатов пустая.\n";
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении данных из файла.");
            return "Произошла ошибка при чтении данных из истории.\n";
        }
    }

    @Override
    public void saveInLog(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_PATH, true))) {
            writer.write(text);
        } catch (IOException e) {
            System.err.println("Ошибка при записи данных в файл");
        }
    }
}
