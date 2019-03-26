package code.repository.dev.sudoku;

import lombok.Data;

@Data
public class Cell {
    private int row;
    private int column;
    private int number;
    private int sector;
    private boolean[] candidate = new boolean[9];
}
