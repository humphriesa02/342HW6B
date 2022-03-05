package main.spreadsheet;

import java.util.Stack;

public class Cell {
    // formula represented in each cell i.e. "A1 + B2"
    private String formula;
    // value the formula represents
    private int value;
    private String formulaPostOrder;
    // the expression tree below represents the formula
    private ExpressionTree expressionTree;
    private Stack stackOfTokens;

    private final int BadCell = -1;

    public void Evaluate (Spreadsheet spreadsheet, Stack expTreeTokenStack){
        expressionTree.buildExpressionTree(expTreeTokenStack);
        value = expressionTree.getValue();
    }


}
