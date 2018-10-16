/*
 * Copyright 2018 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.backjoon;

import java.util.Scanner;

import code.repository.dev.tree.SegmentTreeNode;

/**
 * @author YoungSeok.Kim
 */
public class MinAndMax {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String firstLine = sc.nextLine();

		String[] temp = firstLine.split(" ");
		int N = Integer.valueOf(temp[0]);
		int M = Integer.valueOf(temp[1]);

		int[] targetArray = new int[N];
		for (int i = 0; i < N; i++) {
			targetArray[i] = Integer.valueOf(sc.nextLine());
		}

		SegmentTreeNode segmentTreeNode = new SegmentTreeNode();
		segmentTreeNode.initMin(targetArray, 0, N - 1);
		segmentTreeNode.initMax(targetArray, 0, N - 1);

		for (int index = 0; index < M; index++) {
			String startEnd = sc.nextLine();
			String[] temporary = startEnd.split(" ");
			int start = Integer.valueOf(temporary[0]);
			int end = Integer.valueOf(temporary[1]);
			int min = segmentTreeNode.searchMin(start - 1, end - 1);
			int max = segmentTreeNode.searchMax(start - 1, end - 1);

			System.out.println(min + " " + max);
		}
	}
}
