package code.repository.dev.divideandconquer.changecoin;

/**
 * @author seok
 */
public class ChangeCoin {

    public int calculateChangeCoin(int[] coins, int target) {
        int min = Integer.MAX_VALUE;

        for (int coin : coins) {
            if (target < coin) {
                continue;
            }

            if(target - coin == 0){
                return 1;
            }

            int temp = calculateChangeCoin(coins, target - coin) + 1;
            if (temp < min) {
                min = temp;
            }
        }

        return min;
    }
}
