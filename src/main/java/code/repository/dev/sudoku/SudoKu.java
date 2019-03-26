/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.sudoku;

import java.util.Arrays;
import java.util.List;

/**
 * @author YoungSeok.Kim
 */
public class SudoKu {

	public List solve(List<List<Integer>> problem) {
		List<List<Integer>> result = problem;
		boolean isSolve = false;
		while (!isSolve) {
			for (int row = 0; row < 9; row++) {
				List<Integer> cadidate = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
				for (int column = 0; column < 9; column++) {

					if (result.get(row).get(column) == 0) {

					}
				}
			}

			isSolve = true;
		}
		return result;
	}
}
