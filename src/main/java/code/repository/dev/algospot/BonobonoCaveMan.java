package code.repository.dev.algospot;

import java.util.Scanner;

/**
 * @author seok
 */
public class BonobonoCaveMan {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCaseCount = Integer.valueOf(sc.nextLine());
        for (int i = 0; i < testCaseCount; i++) {
            int rockNumber = Integer.valueOf(sc.nextLine());
            if (rockNumber < 1 || rockNumber > 200000) {
                return;
            }

            String[] tokens = sc.nextLine().split(" ");
            int[] fireSize = new int[tokens.length];
            boolean[] isCoveredByFire = new boolean[tokens.length];
            boolean[] isPick = new boolean[tokens.length];

            for (int j = 0; j < tokens.length; j++) {
                fireSize[j] = Integer.valueOf(tokens[j]);
            }

            System.out.println(calculateMinimalAnimalCount(fireSize, isCoveredByFire, isPick));
        }
    }

    private static int calculateMinimalAnimalCount(int[] fireSize, boolean[] isCoveredByFire, boolean[] isPick) {
        int min = 0;

        for (int i = 0; i < fireSize.length; i++) {
            if (!isCoveredByFire[i]) {
                min++;
                isCoveredByFire[i] = true;
                isPick[i] = true;

                for (int k = fireSize[i] - 1; k > 0; k--) {
                    if (i != 0 && isPick[i - k] && fireSize[i - k] <= fireSize[i] - k) {
                        min--;
                    }

                    if (i + k < fireSize.length) {
                        isCoveredByFire[i + k] = true;
                    }
                }
            }
        }

        return min;
    }
}
