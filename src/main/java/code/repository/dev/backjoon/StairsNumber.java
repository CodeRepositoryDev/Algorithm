package code.repository.dev.backjoon;

import java.util.Scanner;

public class StairsNumber {
    private static int[][][] DPArr = new int[101][10][1 << 10];
    private static int[] bitMap = new int[11];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();

        if (N < 1 || N > 100) {
            return;
        }

        int full = 1023;

        for (int number = 1; number < 10; number++) {
            DPArr[1][number][1 << number] = 1;
        }

        for (int index = 0; index<=10; index++){

        }

        for (int depth = 2; depth <= N; depth++) {
            for (int number = 0; number < 10; number++) {
                for (int selectZeroToNine = 0; selectZeroToNine < 1024; selectZeroToNine++) {
                    if (number == 0)
                        DPArr[depth][0][selectZeroToNine | (1 << 0)] += DPArr[depth - 1][1][selectZeroToNine];
                    else if (number == 9)
                        DPArr[depth][9][selectZeroToNine | (1 << 9)] += DPArr[depth - 1][8][selectZeroToNine];
                    else {
                        DPArr[depth][number][selectZeroToNine | (1 << number)] += DPArr[depth - 1][number - 1][selectZeroToNine];
                        DPArr[depth][number][selectZeroToNine | (1 << number)] += DPArr[depth - 1][number + 1][selectZeroToNine];
                    }
                }
            }
        }

        int ans = 0;

        for (int index = 0; index <= 9; ++index) {
            ans = (ans + DPArr[N][index][full]) % 1000000000;
        }

        System.out.println(ans);
    }
}
