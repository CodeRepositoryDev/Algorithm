package code.repository.dev.backjoon;

import java.util.Scanner;

public class FindPrimeNumber2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String primeRange = sc.nextLine();

        int m = Integer.valueOf(primeRange.split(" ")[0]);
        int n = Integer.valueOf(primeRange.split(" ")[1]);

        if (m < 1 || m > 1000000) {
            return;
        }

        if (n < 1 || n > 1000000) {
            return;
        }

        if(m > n){
            return;
        }

        for (int i = m; i <= n; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }

    private static boolean isPrime(int number) {
        if (number == 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
