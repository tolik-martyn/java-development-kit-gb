package hw3.task2;

public class ArrayComparator2 {

    public static <T> boolean compareArrays(T[] array1, T[] array2) {
        if (array1.length != array2.length) {
            return false;
        }
        // возвращает false, если массивы содержат элементы разного типа.
        for (int i = 0; i < array1.length; i++) {
            if (!array1[i].getClass().equals(array2[i].getClass())) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Integer[] intArray1 = {2, 4, 6};
        Integer[] intArray2 = {1, 3, 5};
        Integer[] intArray3 = {1, 3, 5, 7};
        System.out.println("Integer arrays are equal: " + compareArrays(intArray1, intArray2));
        System.out.println("Integer arrays are equal: " + compareArrays(intArray1, intArray3));

        String[] strArray1 = {"apple", "banana"};
        String[] strArray2 = {"apple", "banana", "orange"};
        String[] strArray3 = {"orange", "banana", "apple"};
        System.out.println("String arrays are equal: " + compareArrays(strArray1, strArray2));
        System.out.println("String arrays are equal: " + compareArrays(strArray3, strArray2));
        System.out.println("String array and Integer array are equal: " + compareArrays(intArray2, strArray2));
        System.out.println("Integer array and String array are equal: " + compareArrays(strArray2, intArray2));
    }
}