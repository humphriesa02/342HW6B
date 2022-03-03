package main.spreadsheet;

public class Spreadsheet {
    private static int rows = 4;
    private static int columns = 4;
    private static final Cell[][] cells = new Cell[rows][columns];

    public Spreadsheet(){

    }
    public Spreadsheet(int rowCols){

    }

    public static int getNumColumns() {
        return columns;
    }

    public static int getNumRows() {
        return rows;
    }
}
