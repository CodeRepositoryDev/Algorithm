package code.repository.dev.divideandconquer.changecoin;

/**
 * @author seok
 */
public class ChangeCoin {

    public int calculateChangeCoin(int[] coins, int target) {
        int min = Integer.MAX_VALUE;

        for (int coin :  coins){
            if(target < coin){
                continue;
            }

            int quotient = target / coin;
            int remainder = target % coin;

            if(remainder == 0) {
                if(quotient < min) {
                    min = quotient;
                }
            } else {
                int temp = calculateChangeCoin(coins, remainder) + quotient;
                if(temp < min){
                    min = temp;
                }
            }
        }

        return min;
    }
}
