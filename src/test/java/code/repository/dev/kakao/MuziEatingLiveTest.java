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
public class MuziEatingLiveTest {
	@Test
	public void testSolution() {
		MuziEatingLive_v2 muzi = new MuziEatingLive_v2();
		//		Assert.assertEquals(muzi.solution(new int[] {3, 1, 2}, 5), 1);
		Assert.assertEquals(muzi.solution(new int[] {1, 1, 200}, 33), 3);
		//		Assert.assertEquals(muzi.solution(new int[] {3, 1, 2}, 5), 1);
		//		Assert.assertEquals(muzi.solution(new int[] {3, 1, 2}, 5), 1);
		//		Assert.assertEquals(muzi.solution(new int[] {3, 1, 2}, 5), 1);
	}
}
