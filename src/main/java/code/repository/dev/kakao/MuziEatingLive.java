/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.kakao;

/**
 * @author YoungSeok.Kim
 */
public class MuziEatingLive {
	public int solution(int[] food_times, long k) {
		long sum = 0;
		if (food_times.length > 200000 || food_times.length < 1) {
			return -1;
		}

		for (int i = 0; i < food_times.length; i++) {
			if (food_times[i] > 100000000 || food_times[i] < 1) {
				return -1;
			}
			sum += food_times[i];
		}

		if (sum <= k) {
			return -1;
		}

		int answer = 0;
		return answer + 1;
	}
}
