package code.repository.dev.backjoon;

import java.util.Scanner;

/**
 * @author seok
 */
public class Lotto {
    static int[] numbers;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String line = sc.nextLine();

            if("0".equals(line)){
                return;
            }

            String[] tokens = line.split(" ");
            int arraySize = Integer.valueOf(tokens[0]);
            if (arraySize <= 6 || arraySize >= 13) {
                return;
            }

            if (tokens.length > arraySize + 1) {
                return;
            }

            numbers = new int[arraySize];
            for (int i = 0; i < arraySize; i++) {
                numbers[i] = Integer.valueOf(tokens[i + 1]);
            }

            search(0, 0, "");
            System.out.println();
        }
    }

    private static void search(int start, int size, String str) {
        if (size == 6) {
            System.out.println(str);
            return;
        }

        if(start == numbers.length){
            return;
        }

        search(start + 1, size + 1, str + numbers[start] + " ");
        search(start + 1, size, str);
    }
}
