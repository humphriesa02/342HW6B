package main.spreadsheet;

public class CellToken extends Token {

    private int column; // column A = 0, B = 1, etc.
    private int row;
    
    /**
     * Default Constructor.
     */
    public CellToken() {

    }

    /**
     * Given inputs in constructor.
     * @param myRow
     * @param myColumn
     */
    public CellToken(int myRow, int myColumn) {
        row = myRow;
        column = myColumn;
    }

    /**
     * Setter for row.
     * @param myRow
     */
    void setRow(int myRow) {
        row = myRow;
    }

    /**
     * setter for column.
     * @param myColumn
     */
    void setColumn(int myColumn) {
        column = myColumn;
    }
    /**
     * Getter for row.
     * @return A row.
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter for column.
     * @return A column.
     */
    public int getColumn() {
        return column;
    }



}
