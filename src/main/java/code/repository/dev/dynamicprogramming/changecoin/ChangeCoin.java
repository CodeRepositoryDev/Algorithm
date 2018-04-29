package code.repository.dev.dynamicprogramming.changecoin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author seok
 */
public class ChangeCoin {
    public int calculateChangeCoin(int[] coins, int target) {
        int[][] dynamicArray = new int[coins.length][target + 1];
        for (int i = 0; i < coins.length; i++) {
            Arrays.fill(dynamicArray[i], -1);
        }

        return calculate(coins, target, dynamicArray);
    }

    private int calculate(int[] coins, int target, int[][] dynamicArray) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int coin : coins) {
            if (target < coin) {
                continue;
            }

            if (target - coin == 0) {
                return 1;
            }

            if (dynamicArray[index][target - coin] == -1) {
                dynamicArray[index][target] = calculate(coins, target - coin, dynamicArray) + 1;
            } else {
                dynamicArray[index][target] = dynamicArray[index][target - coin] + 1;
            }

            if (dynamicArray[index][target] < min) {
                min = dynamicArray[index][target];
            }

            index++;
        }

        return min;
    }
}
