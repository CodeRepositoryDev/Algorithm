package code.repository.dev.backjoon;

import java.util.Scanner;

public class RollCake {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rollCakeLength = Integer.valueOf(scanner.nextLine());
        if(rollCakeLength > 1000){
            return;
        }

        int[] rollCake = new int[rollCakeLength + 1];

        for(int cake : rollCake){
            cake = -1;
        }

        int audienceLength = Integer.valueOf(scanner.nextLine());

        if(audienceLength > 1000){
            return;
        }

        int expectMaxIndex = -1;
        int realMaxIndex = -1;
        int expectMax = -1;
        int realMax = -1;

        for (int j = 1; j < audienceLength + 1; j++) {
            String audienceWantRollCake = scanner.nextLine();
            String[] indexes = audienceWantRollCake.split(" ");

            int start = Integer.valueOf(indexes[0]);
            if(start > rollCakeLength){
                return;
            }

            int end = Integer.valueOf(indexes[1]);
            if(end > rollCakeLength){
                return;
            }

            if (expectMax < end - start) {
                expectMax = end - start;
                expectMaxIndex = j;
            }

            int count = 0;
            for (int index = start; index <= end; index++) {
                if (rollCake[index] > 0) {
                    continue;
                } else {
                    rollCake[index] = j;
                    count++;
                }
            }

            if (realMax < count) {
                realMax = count;
                realMaxIndex = j;
            }
        }

        System.out.println(expectMaxIndex);
        System.out.println(realMaxIndex);
    }
}
