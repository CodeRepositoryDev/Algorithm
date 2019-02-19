/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.kakao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author YoungSeok.Kim
 */
public class FailureRate {
	public int[] solution(int N, int[] stages) {
		int[] notClear = new int[N + 1];
		int[] reachStages = new int[N + 1];

		for (int stageNumberInUser : stages) {
			if (stageNumberInUser < N + 1) {
				notClear[stageNumberInUser] += 1;
			}
		}

		List<FailRate> failureRate = new ArrayList<>();
		int userCount = stages.length;
		for (int i = 1; i < N + 1; i++) {
			reachStages[i] = userCount;
			float rate = 0.0f;
			if (reachStages[i] != 0) {
				rate = (float)notClear[i] / (float)reachStages[i];
			}

			failureRate.add(new FailRate(i, rate));
			userCount = userCount - notClear[i];
		}

		for (int i = 1; i < N + 1; i++) {
			System.out.println("Index:" + i + ", NotClear:" + notClear[i] + ", ReachStages:" + reachStages[i]);
		}

		failureRate.sort(new Comparator<FailRate>() {
			@Override
			public int compare(FailRate o1, FailRate o2) {
				if (o1.rate > o2.rate) {
					return -1;
				} else if (o1.rate == o2.rate) {
					if (o1.getIndex() > o2.getIndex()) {
						return 1;
					}
				} else {
					return 1;
				}

				return 0;
			}
		});

		int[] answer = new int[N];
		for (int i = 0; i < N; i++) {
			answer[i] = failureRate.get(i).getIndex();
		}

		System.out.println(failureRate);
		return answer;
	}

	class FailRate {
		private int index;
		private float rate;

		FailRate(int index, float rate) {
			this.index = index;
			this.rate = rate;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public float getRate() {
			return rate;
		}

		public void setRate(float rate) {
			this.rate = rate;
		}

		@Override
		public String toString() {
			return "FailRate{" +
				"index=" + index +
				", rate=" + rate +
				'}';
		}
	}
}
