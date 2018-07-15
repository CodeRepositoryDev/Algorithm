package code.repository.dev.backjoon;

import java.util.Scanner;

public class MatrixSquare {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] temp = scanner.nextLine().split(" ");
        int N = Integer.valueOf(temp[0]);
        if (2 > N | N > 5) {
            return;
        }

        long B = Long.valueOf(temp[1]);
        if (1 > B | B > 100000000000L) {
            return;
        }

        int[][] matrix = new int[N][N];

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                matrix[row][column] = scanner.nextInt();
            }
            scanner.nextLine();
        }


        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                System.out.print(matrix[row][column]);
            }
            System.out.println();
        }
    }

    private int[][] maxtrixSquare(int[][] matrix) {
        int N  = matrix.length;
        int[][] squareMatrix = new int[N][N];

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                System.out.print(matrix[row][column]);
            }
            System.out.println();
        }

        return squareMatrix;
    }
}
