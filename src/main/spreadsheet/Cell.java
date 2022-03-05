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

    Cell(){
        expressionTree = ExpressionTree.buildExpressionTree(Spreadsheet.getFormula(formula));
        value = expressionTree.getValue();
    }

    private final int BadCell = -1;

    public void Evaluate (Spreadsheet spreadsheet){
        /**
         * Calculate value
         *     +
         *   A1  *
         * "To evaluate an expression tree, you will need to do a traversal of the tree. For example, to
         * evaluate the following tree, you would evaluate subtree A, then subtree B, then add the
         * two results. (What kind of traversal is this?)"
         *
         * Value = traversed Expression Tree
          */
    }


}
