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

    static void showArray(char[] input) {
        for (char i : input) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    static void showArray(int[][] input) {
        for (int[] inside : input) {
            System.out.println();
            for (int i : inside) {
                System.out.print(i + ", ");
            }
        }
        System.out.println();
    }

    static char[] char_arr_from_int(int[] input) {
        char[] result = new char[input.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = (char) input[i];
        }
        return result;
    }
}
