package main.spreadsheet;

public class Spreadsheet {
    private static int rows = 4;
    private static int columns = 4;
    // container for our cells creating a grid of cells
    // columns wide and rows deep
    private static final Cell[][] cells = new Cell[rows][columns];

    public Spreadsheet(){

    }
    public Spreadsheet(int rowCols){

    }

    public int getNumColumns() {
        return columns;
    }

    public int getNumRows() {
        return rows;
    }

    public void printValues(){
        /**
         * for each cell in cells ; print their value
         */
    }

    public void printCellFormula(CellToken cellToken){
        /**
         * Given cellToken, print the formula it points to
         */
    }
}
