/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.backjoon;

import java.util.Scanner;

/**
 * @author YoungSeok.Kim
 */
public class GreedyPanda {
	static int N;
	static int[][] map;
	static Integer[][] livingDayMap;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		N = Integer.valueOf(scanner.nextLine());

		map = new int[N][N];
		livingDayMap = new Integer[N][N];

		for (int rowIndex = 0; rowIndex < N; rowIndex++) {
			String[] row = scanner.nextLine().split(" ");

			for (int columnIndex = 0; columnIndex < N; columnIndex++) {
				map[rowIndex][columnIndex] = Integer.valueOf(row[columnIndex]);
				livingDayMap[rowIndex][columnIndex] = -1;
			}
		}
		int maxLivingDay = -1;

		for (int rowIndex = 0; rowIndex < N; rowIndex++) {
			for (int columnIndex = 0; columnIndex < N; columnIndex++) {
				int temp = calculateLivingDay(rowIndex, columnIndex);

				if (maxLivingDay < temp) {
					maxLivingDay = temp;
				}
			}
		}

		System.out.println(maxLivingDay);
	}

	private static int calculateLivingDay(int rowIndex, int columnIndex) {
		int currentBamboo = map[rowIndex][columnIndex];
		int left = 0, right = 0, up = 0, down = 0;

		if (rowIndex + 1 < N) {
			if (currentBamboo < map[rowIndex + 1][columnIndex]) {
				if (livingDayMap[rowIndex + 1][columnIndex] >= 0) {
					right = livingDayMap[rowIndex + 1][columnIndex] + 1;
				} else {
					right = calculateLivingDay(rowIndex + 1, columnIndex) + 1;
				}
			}
		}

		if (columnIndex + 1 < N) {
			if (currentBamboo < map[rowIndex][columnIndex + 1]) {
				if (livingDayMap[rowIndex][columnIndex + 1] >= 0) {
					down = livingDayMap[rowIndex][columnIndex + 1] + 1;
				} else {
					down = calculateLivingDay(rowIndex, columnIndex + 1) + 1;
				}
			}
		}

		if (columnIndex - 1 >= 0) {
			if (currentBamboo < map[rowIndex][columnIndex - 1]) {
				if (livingDayMap[rowIndex][columnIndex - 1] >= 0) {
					up = livingDayMap[rowIndex][columnIndex - 1] + 1;
				} else {
					up = calculateLivingDay(rowIndex, columnIndex - 1) + 1;
				}
			}
		}

		if (rowIndex - 1 >= 0) {
			if (currentBamboo < map[rowIndex - 1][columnIndex]) {
				if (livingDayMap[rowIndex - 1][columnIndex] >= 0) {
					left = livingDayMap[rowIndex - 1][columnIndex] + 1;
				} else {
					left = calculateLivingDay(rowIndex - 1, columnIndex) + 1;
				}
			}
		}

		if (up == 0 && down == 0 && left == 0 && right == 0) {
			return 1;
		}

		return livingDayMap[rowIndex][columnIndex] = Integer.max(Integer.max(up, down), Integer.max(left, right));
	}
}