package main.spreadsheet;

public class CellToken extends Token {
    private int column; // column A = 0, B = 1, etc.
    private int row;

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
