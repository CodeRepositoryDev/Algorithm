/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author YoungSeok.Kim
 */
public class SudoKu {

    public List solve(List<List<Integer>> problem) {
        List<List<Cell>> sudoku = makeCellMap(problem);
        boolean isSolve = false;
        while (!isSolve) {
            int solveNumber = 81;
            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    Cell cell = sudoku.get(row).get(column);
                    if (cell.getNumber() == 0) {
                        calculateCandidate(sudoku, cell);
                        boolean[] candidate = cell.getCandidate();
                        int count = 0;
                        int candidateIndex = 0;
                        for (int index = 0; index < 9; index++) {
                            if (!candidate[index]) {
                                count++;
                                candidateIndex = index;
                            }
                        }

                        if (count == 1) {
                            cell.setNumber(candidateIndex + 1);
                            solveNumber--;
                        }
                    } else {
                        solveNumber--;
                    }
                }
            }
            if (solveNumber == 0) {
                isSolve = true;
            }
        }
        return sudoku;
    }

    private List<List<Cell>> makeCellMap(List<List<Integer>> problem) {
        List<List<Cell>> cellMap = new ArrayList<>();
        for (int row = 0; row < 9; row++) {
            List<Cell> rowCell = new ArrayList<>();
            for (int column = 0; column < 9; column++) {
                Cell cell = new Cell();
                cell.setRow(row);
                cell.setColumn(column);
                cell.setNumber(problem.get(row).get(column));
                cell.setSector((row / 3) * 3 + column / 3);
                rowCell.add(cell);
            }
            cellMap.add(rowCell);
        }

        return cellMap;
    }

    private void calculateCandidate(List<List<Cell>> sudoku, Cell currentCell) {
        boolean[] currentCellCandidate = new boolean[9];
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                Cell neighborCell = sudoku.get(row).get(column);
                if (neighborCell.getNumber() != 0) {
                    if (row == currentCell.getRow()) {
                        currentCellCandidate[neighborCell.getNumber() - 1] = true;
                    }

                    if (column == currentCell.getColumn()) {
                        currentCellCandidate[neighborCell.getNumber() - 1] = true;
                    }

                    if (currentCell.getSector() == neighborCell.getSector()) {
                        currentCellCandidate[neighborCell.getNumber() - 1] = true;
                    }
                }
            }
        }
        currentCell.setCandidate(currentCellCandidate);
    }
}
