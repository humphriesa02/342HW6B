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

    //private final int BadCell = -1;

    public Cell(){
        formula = "";
        value = 0;
        expressionTree = null;
    }
    public void stackToTree(Stack expTreeTokenStack){
        expressionTree.buildExpressionTree(expTreeTokenStack);
       //value = expressionTree.getValue();
    }

    public void Evaluate (Spreadsheet spreadsheet){

    }


}
