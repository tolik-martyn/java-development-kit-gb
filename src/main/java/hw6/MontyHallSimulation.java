package hw6;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MontyHallSimulation {
    public static void main(String[] args) {
        int totalGames = 1000;
        Map<Integer, Boolean> results = new HashMap<>();

        for (int i = 1; i <= totalGames; i++) {
            boolean win = playMontyHall();
            results.put(i, win);
        }

        // Выводим статистику
        int wins = (int) results
                .values()
                .stream()
                .filter(Boolean::valueOf)
                .count();

        int losses = totalGames - wins;

        System.out.println("Статистика по " + totalGames + " играм:");
        System.out.println("Выигрыши: " + wins);
        System.out.println("Поражения: " + losses);
    }

    private static boolean playMontyHall() {
        Random random = new Random();

        // Генерируем место, где спрятан приз (1, 2 или 3)
        int prizeLocation = random.nextInt(1, 4);

        // Игрок выбирает дверь
        int playerChoice = random.nextInt(1, 4);

        // Ведущий открывает одну из дверей, не выбранных игроком и не содержащую приз
        int doorOpenedByHost;
        do {
            doorOpenedByHost = random.nextInt(1, 4);
        } while (doorOpenedByHost == playerChoice || doorOpenedByHost == prizeLocation);

        // Игрок принимает решение - менять выбор или оставить его
        boolean switchDoor = random.nextBoolean();

        // Если игрок решает поменять дверь, меняем выбор (дверь должна отличаться от ранее выбранной)
        if (switchDoor) {
            for (int i = 1; true; i++) {
                if (playerChoice != i && doorOpenedByHost != i) {
                    playerChoice = i;
                    break;
                }
            }
        }

        // Игрок выигрывает, если его окончательный выбор совпадает с местом, где находится приз
        return playerChoice == prizeLocation;
    }
}