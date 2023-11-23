package hw5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;


/**
 * Класс, реализующий задачу обедающих философов (один ест, остальные размышляют).
 */
public class DiningPhilosophers1 {

    private static final int NUM_PHILOSOPHERS = 5;
    private static final int EAT_COUNT = 3;

    public static void main(String[] args) {
        Object[] forks = new Object[NUM_PHILOSOPHERS];
        Lock commonLock = new ReentrantLock();

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Object();
        }

        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % NUM_PHILOSOPHERS], commonLock);
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
        private final Lock commonLock;
        private final Random random = new Random();
        private int eatCount = 0;

        /**
         * Конструктор класса Philosopher.
         *
         * @param id          Идентификатор философа.
         * @param leftFork    Левая вилка.
         * @param rightFork   Правая вилка.
         * @param commonLock  Общий замок.
         */
        public Philosopher(int id, Object leftFork, Object rightFork, Lock commonLock) {
            this.id = id;
            this.leftFork = leftFork;
            this.rightFork = rightFork;
            this.commonLock = commonLock;
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
            Thread.sleep(random.nextInt(1000));
        }

        /**
         * Метод, представляющий процесс еды философа.
         *
         * @throws InterruptedException Если поток был прерван во время еды.
         */
        private void eat() throws InterruptedException {
            try {
                commonLock.lock();
                synchronized (leftFork) {
                    synchronized (rightFork) {
                        System.out.println("Философ " + id + " ест.");
                        Thread.sleep(random.nextInt(1000));
                        putDownForks();
                    }
                }
            } finally {
                commonLock.unlock();
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