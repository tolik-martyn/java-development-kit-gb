package hw2.repo;

public interface IRepo {

    /**
     * Метод для получения данных из истории сообщений.
     *
     * @return История чата в виде строки.
     */
    String getHistory();

    /**
     * Метод для сохранения данных.
     *
     * @param text - данные, которые нужно сохранить.
     */
    void saveInLog(String text);
}
