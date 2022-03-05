package main.spreadsheet;

import java.util.Stack;

public class Cell {
    // formula represented in each cell i.e. "A1 + B2"
    private String formula;
    // value the formula represents
    private int value;
    // Not used yet
    private String formulaPostOrder;
    // the expression tree below represents the formula
    private ExpressionTree expressionTree;

    // Constructor for cell, sets each initial
    // cell to have value 0, no formula,
    // and a new expression tree based on the value 0
    public Cell(){
        formula = "";
        value = 0;
        expressionTree = new ExpressionTree();
    }

    /**
     * returns the formula
     * @return
     */
    public String getFormula(){
        return formula;
    }

    /**
     * returns the value
     * @return
     */
    public int getValue(){
        return value;
    }

    /**
     * sets our expression tree
     * to the outcome of
     * buildExpressionTree which takes a stack
     * @param expTreeTokenStack
     */
    public void stackToTree(Stack expTreeTokenStack){
        expressionTree.buildExpressionTree(expTreeTokenStack);
       //value = expressionTree.getValue();
    }

    public void Evaluate (Spreadsheet spreadsheet){

    }


}
