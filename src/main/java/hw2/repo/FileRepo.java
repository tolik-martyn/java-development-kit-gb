package hw2.repo;

import java.io.*;

public class FileRepo implements IRepo {
    private static final String LOG_PATH = "src/main/java/hw2/repo/chat_history.txt";

    @Override
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

    @Override
    public void saveInLog(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_PATH, true))) {
            writer.write(text);
        } catch (IOException e) {
            System.err.println("Ошибка при записи данных в файл");
        }
    }
}
