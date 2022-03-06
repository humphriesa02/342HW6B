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

    public void setFormula(String formula){
        this.formula = formula;
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
    public void stackToTree(Stack expTreeTokenStack, Spreadsheet spreadsheet){
        expressionTree.buildExpressionTree(expTreeTokenStack);
        //value = getValue(expressionTree.getRoot(), spreadsheet);
    }
    /*public int getValue(ExpressionTreeNode rootNode, Spreadsheet spreadsheet){
        *//**
         * Look through the tree and find value
         * Gonna need to use priority
         *//*

        if(rootNode == null)
            return 0;
        // Leaf node
        if(rootNode.left == null && rootNode.left == null){
            LiteralToken rootToken = (LiteralToken) rootNode.getToken();
            return rootToken.getValue();
        }
        // Evaluate left subtree
        Token leftToken = getValue(rootNode.left, spreadsheet);
        int leftVal = 0;
        // Evaluate right subtree
        Token rightToken = rootNode.right.getToken();
        int rightVal = 0;

        OperatorToken parentNode = (OperatorToken) rootNode.getToken();
        if(leftToken instanceof CellToken){
            leftVal = spreadsheet.getCellValue((CellToken) leftToken);
        }else if(leftToken instanceof LiteralToken){
            leftVal = ((LiteralToken) leftToken).getValue();
        }
        if(rightToken instanceof CellToken){
            rightVal = spreadsheet.getCellValue((CellToken) rightToken);
        }else if(rightToken instanceof LiteralToken){
            rightVal = ((LiteralToken) rightToken).getValue();
        }

        if (parentNode.getOperatorToken() == '+'){
            return leftVal + rightVal;
        }
        if (parentNode.getOperatorToken() == '-'){
            return leftVal - rightVal;
        }

        if (parentNode.getOperatorToken() == '*')
            return leftVal * rightVal;

        return leftVal / rightVal;

    }*/


    public void Evaluate (Spreadsheet spreadsheet){

    }


}
