/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.backjoon;

import java.util.Scanner;

/**
 * @author YoungSeok.Kim
 */
public class HistogramBiggestRectangle {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			int n = scanner.nextInt();
			if (n == 0) {
				return;
			}

			int[] heights = new int[n];
			int maxHeight = -1;
			for (int i = 0; i < n; i++) {
				heights[i] = scanner.nextInt();
				if (maxHeight < heights[i]) {
					maxHeight = heights[i];
				}
			}

			int maxArea = -1;

			//			int dp[][] = new int[n][maxHeight];
			//			for (int i = 0; i < n; i++) {
			//				for (int h = 0; h < heights[i]; h++) {
			//					if (i != 0 && dp[i - 1][h] > 0) {
			//						dp[i][h] = dp[i - 1][h] + h + 1;
			//						if (dp[i][h] > maxArea) {
			//							maxArea = dp[i][h];
			//						}
			//
			//					} else {
			//						dp[i][h] = h + 1;
			//						if (dp[i][h] > maxArea) {
			//							maxArea = dp[i][h];
			//						}
			//					}
			//				}
			//			}

			//			for (int i = 0; i < n; i++) {
			//				int x1 = 1;
			//				for (int j = i - 1; j >= 0; j--) {
			//					if (heights[j] >= heights[i]) {
			//						x1++;
			//					} else {
			//						break;
			//					}
			//				}
			//				int preArea = heights[i] * x1;
			//
			//				int x2 = 0;
			//				for (int j = i; j < n; j++) {
			//					if (heights[j] >= heights[i]) {
			//						x2++;
			//					} else {
			//						break;
			//					}
			//				}
			//
			//				int postArea = heights[i] * x2;
			//
			//				if (maxArea < Integer.max(preArea, postArea)) {
			//					maxArea = Integer.max(preArea, postArea);
			//				}
			//			}

			System.out.println(maxArea);
			return;
		}
	}
}
