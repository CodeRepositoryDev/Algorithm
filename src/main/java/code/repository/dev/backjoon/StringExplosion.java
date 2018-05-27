package code.repository.dev.backjoon;

import java.util.Scanner;

public class StringExplosion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String targetString = sc.nextLine();

        if (targetString.length() < 1 || targetString.length() > 1000000) {
            return;
        }

        String explosionString = sc.nextLine();

        if (explosionString.length() < 1 || explosionString.length() > 36) {
            return;
        }
        String result = findString(targetString.toCharArray(), explosionString.toCharArray());

        System.out.println(result);
    }

    private static String findString(char[] targetCharArr, char[] explosionCharArr) {
        int targetOffset = 0;
        int explosionOffset = 0;

        int count = 0;

        while (targetOffset < targetCharArr.length - count * explosionCharArr.length) {
            if (targetCharArr[targetOffset] == explosionCharArr[explosionOffset]) {
                while (explosionOffset < explosionCharArr.length && targetCharArr[targetOffset + explosionOffset] == explosionCharArr[explosionOffset]) {
                    explosionOffset++;
                }

                if (explosionOffset == explosionCharArr.length) {
                    int index = 0;
                    for (int i = targetOffset + explosionOffset; i < targetCharArr.length - count * explosionCharArr.length; i++) {
                        targetCharArr[targetOffset + index++] = targetCharArr[i];
                    }
                    targetOffset = targetOffset - explosionCharArr.length;
                    count++;
                }

                explosionOffset = 0;
            }

            targetOffset++;
        }

        String result = "";
        for (int i = 0; i < targetOffset; i++) {
            result += targetCharArr[i];
        }
        return result;
    }


    private void stringExplosion() {

        Scanner sc = new Scanner(System.in);

        String targetString = sc.nextLine();

        if (targetString.length() < 1 || targetString.length() > 1000000) {
            return;
        }

        String explosionString = sc.nextLine();

        if (explosionString.length() < 1 || explosionString.length() > 36) {
            return;
        }

        while (targetString.contains(explosionString)) {
            targetString = targetString.replace(explosionString, "");
        }

        if (targetString.length() == 0) {
            System.out.println("FRULA");
            return;
        }

        System.out.println(targetString);
    }
}
