/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.kakao;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author YoungSeok.Kim
 */
public class FailureRateTest {
	@Test
	public void testSolution() {
		FailureRate failureRate = new FailureRate();
		Assert.assertArrayEquals(failureRate.solution(5, new int[] {2, 1, 2, 6, 2, 4, 3, 3}), new int[] {3, 4, 2, 1, 5});
		Assert.assertArrayEquals(failureRate.solution(4, new int[] {4, 4, 4, 4, 4}), new int[] {4, 1, 2, 3});
		Assert.assertArrayEquals(failureRate.solution(4, new int[] {5, 5, 5, 5, 5, 5, 5}), new int[] {1, 2, 3, 4});
		Assert.assertArrayEquals(failureRate.solution(4, new int[] {2, 5, 5, 5, 5, 5, 5}), new int[] {2, 1, 3, 4});
		Assert.assertArrayEquals(failureRate.solution(4, new int[] {2, 3, 5, 5, 5, 5, 5}), new int[] {3, 2, 1, 4});
		Assert.assertArrayEquals(failureRate.solution(4, new int[] {2, 3, 4, 5, 5, 5, 5}), new int[] {4, 3, 2, 1});
		Assert.assertArrayEquals(failureRate.solution(4, new int[] {3, 5, 5, 5, 5, 5, 5}), new int[] {3, 1, 2, 4});
		Assert.assertArrayEquals(failureRate.solution(3, new int[] {1, 1, 1, 1, 1, 2}), new int[] {2, 1, 3});
	}
}
