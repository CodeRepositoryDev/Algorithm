package code.repository.dev.sudoku;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

@RunWith(JUnit4.class)
public class SudokuTest {
    private SudoKu sudoKu = new SudoKu();

    @Test
    public void testSolveVeryEasy() {
        List<List<Integer>> problem = Arrays.asList(
                Arrays.asList(4, 7, 5, 0, 0, 0, 0, 8, 0),
                Arrays.asList(1, 0, 6, 0, 4, 0, 5, 0, 9),
                Arrays.asList(8, 0, 0, 5, 1, 7, 6, 0, 4),
                Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0),
                Arrays.asList(2, 8, 1, 6, 7, 0, 3, 9, 0),
                Arrays.asList(0, 6, 9, 8, 0, 5, 0, 4, 1),
                Arrays.asList(5, 0, 7, 0, 8, 2, 9, 0, 3),
                Arrays.asList(9, 4, 0, 7, 0, 1, 2, 0, 0),
                Arrays.asList(6, 2, 0, 3, 5, 0, 4, 1, 7)
        );

        List result = sudoKu.solve(problem);
        printSudoku(result);
    }

    @Test
    public void testSolveEasy() {
        List<List<Integer>> problem = Arrays.asList(
                Arrays.asList(0, 0, 0, 0, 0, 0, 7, 3, 8),
                Arrays.asList(3, 0, 7, 8, 0, 0, 0, 0, 4),
                Arrays.asList(8, 0, 4, 0, 3, 0, 1, 6, 5),
                Arrays.asList(7, 8, 0, 1, 0, 3, 0, 0, 6),
                Arrays.asList(0, 0, 0, 6, 0, 7, 5, 2, 0),
                Arrays.asList(0, 0, 6, 0, 4, 0, 8, 7, 0),
                Arrays.asList(2, 7, 3, 0, 0, 1, 0, 0, 0),
                Arrays.asList(0, 0, 0, 3, 0, 0, 4, 5, 0),
                Arrays.asList(0, 4, 0, 0, 6, 0, 0, 0, 7)
        );

        List result = sudoKu.solve(problem);
        printSudoku(result);
    }

    @Test
    public void testSolveNormal() {
        List<List<Integer>> problem = Arrays.asList(
                Arrays.asList(0, 0, 3, 0, 2, 0, 0, 0, 8),
                Arrays.asList(1, 8, 0, 0, 0, 6, 0, 0, 5),
                Arrays.asList(0, 0, 0, 8, 9, 5, 0, 0, 0),
                Arrays.asList(0, 4, 0, 0, 1, 2, 0, 8, 0),
                Arrays.asList(0, 1, 0, 6, 3, 0, 0, 4, 0),
                Arrays.asList(0, 9, 0, 0, 0, 0, 0, 0, 6),
                Arrays.asList(0, 0, 8, 0, 0, 0, 0, 5, 1),
                Arrays.asList(0, 3, 0, 9, 0, 4, 0, 2, 7),
                Arrays.asList(2, 0, 0, 7, 0, 0, 0, 0, 4)
        );

        List result = sudoKu.solve(problem);
        printSudoku(result);
    }

    private void printSudoku(List<List<Cell>> sudoku) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                Cell cell = sudoku.get(row).get(column);
                System.out.print(cell.getNumber());
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
