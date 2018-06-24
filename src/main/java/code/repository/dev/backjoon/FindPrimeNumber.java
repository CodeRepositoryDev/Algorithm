package code.repository.dev.backjoon;

import java.util.Scanner;

public class FindPrimeNumber {
    private static boolean[] primeNumber = new boolean[1001];

    public static void initPrimNumber() {
        for (int i = 0; i < primeNumber.length; i++) {
            primeNumber[i] = true;
        }

        primeNumber[1] = false;

        for (int i = 2; i < primeNumber.length / 2; i++) {
            int index = 2;
            while (i * index < primeNumber.length) {
                primeNumber[i * index++] = false;
            }
        }
    }

    public static void main(String[] args) {
        initPrimNumber();
        Scanner sc = new Scanner(System.in);

        int count = Integer.valueOf(sc.nextLine());
        String primes = sc.nextLine();
        String[] arr = primes.split(" ");

        int result = 0;
        for (int i = 0; i < count; i++) {
            if (primeNumber[Integer.valueOf(arr[i])]) {
                result++;
            }
        }

        System.out.println(result);
    }
}
