/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.nonogram;

import java.util.Arrays;

/**
 * @author YoungSeok.Kim
 */
public class Nonogram {
	private int row;
	private int column;
	private Boolean[][] map;
	private int[][] problemRow;
	private int[][] problemColumn;

	public void init(int row, int column, int[][] problemRow, int[][] problemColumn) {
		this.row = row;
		this.column = column;
		this.map = new Boolean[row][column];
		this.problemRow = problemRow;
		this.problemColumn = problemColumn;
	}

	public void fillRow() {
		for (int rowIndex = 0; rowIndex < this.row; rowIndex++) {
			int[] currentProblemRow = this.problemRow[rowIndex];

			if (currentProblemRow.length == 1) {
				int fillNumber = currentProblemRow[0];
				if (fillNumber > row / 2) {
					for (int columnIndex = row - fillNumber; columnIndex < fillNumber; columnIndex++) {
						map[rowIndex][columnIndex] = true;
					}
				}
			} else if (currentProblemRow.length == Math.ceil(row / 2)) {
				for (int columnIndex = 0; columnIndex < column; columnIndex++) {
					map[rowIndex][columnIndex++] = true;
					map[rowIndex][columnIndex] = false;
				}
			} else {
				if (Arrays.stream(currentProblemRow).sum() + currentProblemRow.length - 1 == column) {
					int columnIndex = 0;
					for (int problemRow : currentProblemRow) {
						for (; columnIndex < problemRow; columnIndex++) {
							map[rowIndex][columnIndex] = true;
						}
						map[rowIndex][++columnIndex] = false;
					}
				} else {
					int problemRowIndex = 0;
					for (int columnIndex = 0; columnIndex < column; columnIndex++) {
						if (map[rowIndex][columnIndex] == false) {
							continue;
						}

						if (map[rowIndex][columnIndex]) {
							for (int r = 0; r < currentProblemRow[problemRowIndex++]; r++) {
								map[rowIndex][columnIndex] = true;
								columnIndex += 1;
							}
							map[rowIndex][columnIndex] = false;
							continue;
						}

						if (problemRowIndex >= currentProblemRow.length) {
							map[rowIndex][columnIndex] = false;
							continue;
						}

						int fillNumber = currentProblemRow[problemRowIndex++];
						int remainColum = 0;
						for (int calculateColumn = columnIndex; calculateColumn < column; calculateColumn++) {

						}
						if (fillNumber > row / 2) {
							for (int columnIndex = row - fillNumber; columnIndex < fillNumber; columnIndex++) {
								map[rowIndex][columnIndex] = true;
							}
						}
					}
				}
			}
		}

	}

	public void fillColumn() {
		for (int columnIndex = 0; columnIndex < this.column; columnIndex++) {
			int[] currentProblemColumn = this.problemColumn[columnIndex];

			if (currentProblemColumn.length == 1) {
				int fillNumber = currentProblemColumn[0];
				if (fillNumber > column / 2) {
					for (int rowIndex = column - fillNumber; rowIndex < fillNumber; rowIndex++) {
						map[rowIndex][columnIndex] = true;
					}
				}
			}
		}
	}
}
