package hw5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс, реализующий задачу обедающих философов.
 * Одновременно могут есть один или два философа (которые используют непересекающиеся вилки).
 */
public class DiningPhilosophers2 {

    private static final int NUM_PHILOSOPHERS = 5;
    private static final int EAT_COUNT = 3;

    public static void main(String[] args) {
        Object[] forks = new Object[NUM_PHILOSOPHERS];
        Lock[] philosopherLocks = new ReentrantLock[NUM_PHILOSOPHERS];

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Object();
            philosopherLocks[i] = new ReentrantLock();
        }

        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % NUM_PHILOSOPHERS], philosopherLocks[i]);
            new Thread(philosophers[i]).start();
        }
    }

    /**
     * Внутренний класс, представляющий философа.
     */
    private static class Philosopher implements Runnable {
        private final int id;
        private final Object leftFork;
        private final Object rightFork;
        private final Lock philosopherLock;
        private int eatCount = 0;

        /**
         * Конструктор класса Philosopher.
         *
         * @param id              уникальный идентификатор философа
         * @param leftFork        левая вилка
         * @param rightFork       правая вилка
         * @param philosopherLock блокировка для безопасного взаимодействия с общими ресурсами
         */
        public Philosopher(int id, Object leftFork, Object rightFork, Lock philosopherLock) {
            this.id = id;
            this.leftFork = leftFork;
            this.rightFork = rightFork;
            this.philosopherLock = philosopherLock;
        }

        /**
         * Основной метод, представляющий жизненный цикл философа.
         */
        @Override
        public void run() {
            try {
                while (eatCount < EAT_COUNT) {
                    think();
                    eat();
                    eatCount++;
                }
            } catch (InterruptedException e) {
                System.out.println("Что-то пошло не так!");
                Thread.currentThread().interrupt();
            }
        }

        /**
         * Метод, представляющий размышления философа.
         *
         * @throws InterruptedException Если поток был прерван во время сна.
         */
        private void think() throws InterruptedException {
            System.out.println("Философ " + id + " размышляет.");
        }

        /**
         * Метод, представляющий процесс еды философа.
         *
         * @throws InterruptedException Если поток был прерван во время еды.
         */
        private void eat() throws InterruptedException {
            try {
                philosopherLock.lock();
                synchronized (leftFork) {
                    synchronized (rightFork) {
                        System.out.println("Философ " + id + " ест и использует вилки " + leftFork.hashCode() + " и " +
                                rightFork.hashCode());
                        Thread.sleep(3000);
                        putDownForks();
                    }
                }
            } finally {
                philosopherLock.unlock();
            }
        }

        /**
         * Метод, представляющий процесс возврата вилок после еды.
         */
        private void putDownForks() {
            System.out.println("Философ " + id + " кладет вилки.");
        }
    }
}