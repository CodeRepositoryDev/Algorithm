package code.repository.dev.backjoon;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MatrixSquare {
    static Map<Long, int[][]> resultMatrixMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        if (N < 2 || N > 5) {
            return;
        }

        double B = scanner.nextLong();
        if (B < 1 || B > 100000000000L) {
            return;
        }

        int[][] matrix = new int[N][N];
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                matrix[row][column] = scanner.nextInt();
            }
            scanner.nextLine();
        }

        resultMatrixMap.put(1L, matrix);
        int[][] resultMatrix = result(N, B, matrix);

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                if(column == N-1){
                    System.out.println((resultMatrix[row][column] % 1000));
                } else{
                    System.out.print((resultMatrix[row][column] % 1000) + " ");
                }
            }
        }
    }

    private static int[][] result(int N, double B, int[][] matrix) {
        if (B == 1) {
            return matrix;
        }

        if (B == 2) {
            int[][] result = multipleMatrix(N, matrix, matrix);
            resultMatrixMap.put(2L, result);
            return result;
        }

        if (resultMatrixMap.containsKey((long)B)) {
            return resultMatrixMap.get((long)B);
        }

        int[][] preResultMatrix = result(N, Math.ceil(B / 2.0), matrix);
        int[][] postResultMatrix = result(N, Math.floor(B / 2.0), matrix);
        int[][] tempResult = multipleMatrix(N, preResultMatrix, postResultMatrix);
        resultMatrixMap.put((long)B, tempResult);
        return tempResult;
    }

    private static int[][] multipleMatrix(int N, int[][] matrix1, int[][] matrix2) {
        int[][] multipleMatrix = new int[N][N];

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                for (int index = 0; index < N; index++) {
                    multipleMatrix[row][column] += matrix1[row][index] * matrix2[index][column];
                }
                multipleMatrix[row][column] = multipleMatrix[row][column] % 1000;
            }
        }

        return multipleMatrix;
    }
}
