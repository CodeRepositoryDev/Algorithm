/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.nonogram;

/**
 * @author YoungSeok.Kim
 */
public class Nonogram {
	boolean[][] map;
	int[][] problemRow;
	int[][] problemColumn;

	public void init(int row, int column, int[][] problemRow, int[][] problemColumn) {
		this.map = new boolean[row][column];
		this.problemRow = problemRow;
		this.problemColumn = problemColumn;
	}

}
