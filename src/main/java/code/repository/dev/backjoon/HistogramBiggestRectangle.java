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

			long[] heights = new long[n + 1];

			for (int i = 0; i < n; i++) {
				heights[i] = scanner.nextLong();
			}

			System.out.println(calculateArea(0, n, heights));
		}
	}

	public static long calculateArea(int start, int end, long[] heights) {
		if (start >= end) {
			return heights[start];
		}

		long minHeight = Integer.MAX_VALUE;
		int minIndex = -1;

		for (int i = start; i < end; i++) {
			if (minHeight > heights[i]) {
				minHeight = heights[i];
				minIndex = i;
			}
		}

		long area = minHeight * (end - start);
		long left = calculateArea(start, minIndex, heights);
		long right = calculateArea(minIndex + 1, end, heights);
		return Long.max(area, Long.max(left, right));
	}
}
