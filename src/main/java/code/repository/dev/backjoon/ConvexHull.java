/*
 * Copyright 2018 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.backjoon;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author YoungSeok.Kim
 */
public class ConvexHull {
	private static final int MAX_VALUE = 40001;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int pointCount = Integer.valueOf(scanner.nextLine());
		Point[] points = new Point[pointCount];
		Point pointMinY = new Point();
		int min = MAX_VALUE;
		for (int index = 0; index < pointCount; index++) {
			String[] temp = scanner.nextLine().split(" ");
			int tempX = Integer.valueOf(temp[0]);
			int tempY = Integer.valueOf(temp[1]);
			points[index] = new Point(tempX, tempY);
			if (tempY < min) {
				pointMinY = points[index];
				min = tempY;
			}
		}

		Queue<Point> queue = initQueue(pointMinY, points);
		Point firstPoint = queue.poll();
		int count = 1;
		while (!queue.isEmpty()) {
			Point tempPoint = queue.poll();
			System.out.println(tempPoint.x + " , " + tempPoint.y + "," + tempPoint.slope);
		}
	}

	private static Queue<Point> initQueue(Point minPoint, Point[] points) {
		Queue<Point> queue = new PriorityQueue<>();

		for (int index = 0; index < points.length; index++) {
			int diffY = (minPoint.y - points[index].y);
			int diffX = (minPoint.x - points[index].x);
			double slope = Integer.MAX_VALUE;

			if (diffX == 0 && diffY == 0) {
				slope = 0;
			} else if (diffX != 0) {
				slope = diffY / diffX;
			}

			points[index].slope = slope;
			queue.offer(points[index]);
		}
		return queue;
	}

	public static class Point implements Comparable<Point> {
		int x;
		int y;
		double slope;

		public Point() {
			super();
		}

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if (this.slope > o.slope) {
				return 1;
			} else if (this.slope < o.slope) {
				return -1;
			}

			return 0;
		}
	}
}
