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
		int userCount = stages.length;
		for (int i = 1; i < N + 1; i++) {
			reachStages[i] = userCount;
			userCount = userCount - notClear[i];
		}

		List<FailRate> failureRate = new ArrayList<>();
		for (int i = 1; i < N + 1; i++) {
			System.out.println("Index:" + i + ", NotClear:" + notClear[i] + ", ReachStages:" + reachStages[i]);
			failureRate.add(new FailRate(i, (double)((float)notClear[i] / (float)reachStages[i])));
		}

		failureRate.sort(new Comparator<FailRate>() {
			@Override
			public int compare(FailRate o1, FailRate o2) {
				if (o1.rate > o2.rate) {
					return -1;
				}

				return 0;
			}
		});

		int[] answer = new int[N];
		for (int i = 0; i < N; i++) {
			System.out.println(failureRate);
			answer[i] = failureRate.get(i).getIndex();
		}
		return answer;
	}

	class FailRate {
		private int index;
		private double rate;

		FailRate(int index, double rate) {
			this.index = index;
			this.rate = rate;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public double getRate() {
			return rate;
		}

		public void setRate(double rate) {
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
