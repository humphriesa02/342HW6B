package main.spreadsheet;

public class Cell {
    // formula represented in each cell i.e. "A1 + B2"
    private String formula;
    // value the formula represents
    private int value;
    private String formulaPostOrder;
    // the expression tree below represents the formula
    private ExpressionTree expressionTree;

    public void Evaluate (Spreadsheet spreadsheet);
}
