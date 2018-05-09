package code.repository.dev.backjoon;

import java.util.Scanner;

/**
 * @author seok
 */
public class NumberTriangle {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        int[][] numberTriangle = new int[size][];
        for (int i = 0; i < size; i++) {
            String temp = scanner.nextLine();
            temp = temp.trim();
            String[] tempArray = temp.split(" ");
            numberTriangle[i] = new int[tempArray.length];
            for (int j = 0; j < tempArray.length; j++) {
                numberTriangle[i][j] = Integer.parseInt(tempArray[j]);
            }
        }

        System.out.println(numberTriangleMax(size, numberTriangle));
    }


    /*
     * DP
     * version3
     * */

    public static int numberTriangleMax(int size, int[][] numberTriangle) {
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                numberTriangle[i - 1][j] = numberTriangle[i - 1][j] + Math.max(numberTriangle[i][j], numberTriangle[i][j + 1]);
            }
        }

        return numberTriangle[0][0];
    }

    /*
     * DP
     * version2
     * */
    public static int numberTriangleMaxV2(int size, int[][] numberTriangle) {
        int[][] dynamicArray = new int[size][];
        for (int i = 0; i < size; i++) {
            dynamicArray[i] = new int[i + 1];
            for (int j = 0; j < i + 1; j++) {
                dynamicArray[i][j] = -1;
            }
        }

        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (dynamicArray[i][j] == -1 && dynamicArray[i][j + 1] == -1) {
                    dynamicArray[i - 1][j] = numberTriangle[i - 1][j] + Math.max(numberTriangle[i][j], numberTriangle[i][j + 1]);
                } else {
                    if (dynamicArray[i][j] == -1 && dynamicArray[i][j + 1] != -1) {
                        dynamicArray[i - 1][j] = numberTriangle[i - 1][j] + Math.max(numberTriangle[i][j], dynamicArray[i][j + 1]);
                    } else if (dynamicArray[i][j] != -1 && dynamicArray[i][j + 1] == -1) {
                        dynamicArray[i - 1][j] = numberTriangle[i - 1][j] + Math.max(dynamicArray[i][j], numberTriangle[i][j + 1]);
                    } else {
                        dynamicArray[i - 1][j] = numberTriangle[i - 1][j] + Math.max(dynamicArray[i][j], dynamicArray[i][j + 1]);
                    }
                }
            }
        }
        return getMaxValue(numberTriangle, 0, 0, size);
    }

    /*
     * DP
     * version1
     * */
    public static int numberTriangleMaxV1(int size, int[][] numberTriangle) {
        int[][] dynamicArray = new int[size][];
        for (int i = 0; i < size; i++) {
            dynamicArray[i] = new int[i + 1];
            for (int j = 0; j < i + 1; j++) {
                dynamicArray[i][j] = -1;
            }
        }

        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                int temp1;
                int temp2;

                if (dynamicArray[i][j] == -1) {
                    temp1 = numberTriangle[i][j];
                } else {
                    temp1 = dynamicArray[i][j];
                }

                if (dynamicArray[i][j + 1] == -1) {
                    temp2 = numberTriangle[i][j + 1];
                } else {
                    temp2 = dynamicArray[i][j + 1];
                }

                dynamicArray[i - 1][j] = numberTriangle[i - 1][j] + Math.max(temp1, temp2);
            }
        }

        return dynamicArray[0][0];
    }

    /*
    * DAC
    * */
    private static int getMaxValue(int[][] numberTriangle, int row, int column, int size) {
        if (row == size - 1) {
            if (column < size - 1) {
                return Math.max(numberTriangle[row][column], numberTriangle[row][column + 1]);
            } else {
                return Integer.MIN_VALUE;
            }
        }

        int candidateMax1 = numberTriangle[row][column] + getMaxValue(numberTriangle, row + 1, column, size);
        int candidateMax2 = numberTriangle[row][column] + getMaxValue(numberTriangle, row + 1, column + 1, size);

        return Math.max(candidateMax1, candidateMax2);
    }
}
