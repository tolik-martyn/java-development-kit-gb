package hw3.task1;

public class Calculator {

    public static <T extends Number> double sum(T firstTerm, T secondTerm) {
        return firstTerm.doubleValue() + secondTerm.doubleValue();
    }

    public static <T extends Number> double multiply(T firstMultiplier, T secondMultiplier) {
        return firstMultiplier.doubleValue() * secondMultiplier.doubleValue();
    }

    public static <T extends Number> double divide(T divisible, T divisor) {
        if (divisor.doubleValue() == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return divisible.doubleValue() / divisor.doubleValue();
    }

    public static <T extends Number> double subtract(T reduced, T subtracted) {
        return reduced.doubleValue() - subtracted.doubleValue();
    }
}