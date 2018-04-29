package code.repository.dev.dynamicprogramming.changecoin;

import org.junit.Test;

/**
 * @author seok
 */
public class TestCoinChange {
    private ChangeCoin sut = new ChangeCoin();

    @Test
    public void ChangeCoinTest() {
        int[] coins = {10, 50, 100, 120, 200};
        int result = sut.calculateChangeCoin(coins, 380);
        System.out.println(result);
    }
}
