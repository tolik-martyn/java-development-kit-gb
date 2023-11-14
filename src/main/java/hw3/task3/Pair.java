package hw3.task3;

public class Pair<K, V> {
    private final K first;
    private final V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public static void main(String[] args) {

        Pair<String, Integer> pair1 = new Pair<>("One", 1);
        Pair<String, Double> pair2 = new Pair<>("Two", 2.0);

        System.out.println("Pair 1: " + pair1);
        System.out.println("Pair 2: " + pair2);

        String firstValue = pair1.getFirst();
        Double secondValue = pair2.getSecond();

        System.out.println("First value of Pair 1: " + firstValue);
        System.out.println("Second value of Pair 2: " + secondValue);
    }
}