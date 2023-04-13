package maxen;

public class Helpers {
    static int[] Integer_arr_to_int_arr(Integer[] inputArray) {

        Integer[] biIntegers = inputArray;
        int resultArr[] = new int[biIntegers.length];
        int iterator = 0;
        for (Integer charCode : biIntegers) {
            resultArr[iterator] = charCode;
            iterator++;
        }
        return resultArr;
    }

    static void showArray(int[] input) {
        for (int i : input) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }
}
