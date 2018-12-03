/*
 * Copyright 2018 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.backjoon;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author YoungSeok.Kim
 */
public class Paint {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String[] size = scanner.nextLine().split(" ");
		int height = Integer.valueOf(size[0]);
		int width = Integer.valueOf(size[1]);

		int[][] paint = new int[height][width];

		for (int rowNumber = 0; rowNumber < height; rowNumber++) {
			String[] row = scanner.nextLine().split(" ");
			for (int columnNumber = 0; columnNumber < width; columnNumber++) {
				paint[rowNumber][columnNumber] = Integer.valueOf(row[columnNumber]);
			}
		}

		Stack<Point> floodFill = new Stack<>();
		int maxSize = 0;
		int paintCount = 0;
		for (int rowNumber = 0; rowNumber < height; rowNumber++) {
			for (int columnNumber = 0; columnNumber < width; columnNumber++) {
				if (paint[rowNumber][columnNumber] == 1) {
					floodFill.push(new Point(rowNumber, columnNumber));
					paint[rowNumber][columnNumber] = 3;
					paintCount++;
				}

				int paintSize = 0;

				while (!floodFill.isEmpty()) {
					Point point = floodFill.pop();
					System.out.println(point);

					paintSize++;
					if (point.x > 0 && paint[point.x - 1][point.y] == 1) {
						floodFill.push(new Point(point.x - 1, point.y));
						paint[point.x - 1][point.y] = 3;
					}

					if (point.y > 0 && paint[point.x][point.y - 1] == 1) {
						floodFill.push(new Point(point.x, point.y - 1));
						paint[point.x][point.y - 1] = 3;
					}

					if (point.x < height - 1 && paint[point.x + 1][point.y] == 1) {
						floodFill.push(new Point(point.x + 1, point.y));
						paint[point.x + 1][point.y] = 3;
					}

					if (point.y < width - 1 && paint[point.x][point.y + 1] == 1) {
						floodFill.push(new Point(point.x, point.y + 1));
						paint[point.x][point.y + 1] = 3;
					}
				}

				if (paintSize > maxSize) {
					maxSize = paintSize;
				}
			}
		}
		System.out.println(paintCount);
		System.out.println(maxSize);
	}

	private static class Point {
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		int x;
		int y;

		public String toString() {
			return "x: " + this.x + ", y: " + this.y;
		}
	}
}
