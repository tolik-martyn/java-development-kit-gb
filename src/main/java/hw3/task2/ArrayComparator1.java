package hw3.task2;

public class ArrayComparator1<T> {

    public boolean compareArrays(T[] array1, T[] array2) {
        if (array1 == null || array2 == null) {
            return false;
        }
        return array1.length == array2.length;
    }

    public static void main(String[] args) {

        ArrayComparator1<Integer> intArrComp = new ArrayComparator1<>();
        ArrayComparator1<String> stringArrCom = new ArrayComparator1<>();

        Integer[] intArray1 = {2, 4, 6};
        Integer[] intArray2 = {1, 3, 5};
        Integer[] intArray3 = {1, 3, 5, 7};
        System.out.println("Integer arrays are equal: " + intArrComp.compareArrays(intArray1, intArray2));
        System.out.println("Integer arrays are equal: " + intArrComp.compareArrays(intArray1, intArray3));

        String[] strArray1 = {"apple", "banana"};
        String[] strArray2 = {"apple", "banana", "orange"};
        String[] strArray3 = {"orange", "banana", "apple"};
        System.out.println("String arrays are equal: " + stringArrCom.compareArrays(strArray1, strArray2));
        System.out.println("String arrays are equal: " + stringArrCom.compareArrays(strArray3, strArray2));
        // System.out.println("Compilation error: " + intArrComp.compareArrays(intArray2, strArray2));
        // System.out.println("Compilation error: " + stringArrCom.compareArrays(intArray2, strArray2));
    }
}