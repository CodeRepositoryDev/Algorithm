package code.repository.dev.backjoon;

import java.util.Scanner;

public class Resignation2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = Integer.valueOf(scanner.nextLine());
        Counsel[] counselArray = new Counsel[N];

        for (int index = 0; index < N; index++) {
            counselArray[index] = new Counsel(scanner.nextLine().split(" "));
        }

        System.out.println(calculateMaxPayment(0, 0, counselArray));
    }

    private static int calculateMaxPayment(int index, int payment, Counsel[] counsels) {
        if (index >= counsels.length) {
            return payment;
        }

        Counsel counsel = counsels[index];

        if (index + counsel.getTime() <= counsels.length) {
            return Integer.max(calculateMaxPayment(index + 1, payment, counsels), calculateMaxPayment(index + counsel.getTime(), payment + counsel.getPayment(), counsels));
        } else {
            return Integer.max(calculateMaxPayment(index + 1, payment, counsels), calculateMaxPayment(index + counsel.getTime(), payment, counsels));
        }

    }

    private static class Counsel {
        private int time;
        private int payment;

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getPayment() {
            return payment;
        }

        public void setPayment(int payment) {
            this.payment = payment;
        }

        public Counsel(String[] strings) {
            this.time = Integer.valueOf(strings[0]);
            this.payment = Integer.valueOf(strings[1]);
        }
    }
}
