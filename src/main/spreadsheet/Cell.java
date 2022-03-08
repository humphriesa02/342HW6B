package main.spreadsheet;

import java.util.ArrayList;
import java.util.Stack;

/**
 * TCSS 342
 * Authors: Dylan, Andrew, Alex
 * March 2022
 *
 * Cell data type, stores
 * formulas, values, expressionTrees
 * adjacencyCells, and indegrees.
 */
public class Cell {
    // formula represented in each cell i.e. "A1 + B2"
    private String formula;
    // value the formula represents
    private int value;
    // the expression tree below represents the formula
    private final ExpressionTree expressionTree;

    private final ArrayList<CellToken> adjacencyCells;

    private int inDegree;

    // Constructor for cell, sets each initial
    // cell to have value 0, no formula,
    // and a new expression tree based on the value 0
    public Cell(){
        formula = "";
        value = 0;
        expressionTree = new ExpressionTree();
        adjacencyCells = new ArrayList<>();
        inDegree = 0;
    }

    public void setFormula(String formula){
        this.formula = formula;
    }

    /**
     * returns the formula
     * @return formula
     */
    public String getFormula(){
        return formula;
    }

    /**
     * returns the value
     * @return value
     */
    public int getValue(){
        return value;
    }

    public ArrayList<CellToken> getAdjacencyCells(){
        return adjacencyCells;
    }

    /**
     * sets our expression tree
     * to the outcome of
     * buildExpressionTree which takes a stack
     * @param expTreeTokenStack
     */
    public void stackToTree(Stack expTreeTokenStack){

        expressionTree.buildExpressionTree(expTreeTokenStack);
    }

    /**
     * Adjacency cell setter
     * @param token
     */
    public void setAdjacencyCells(CellToken token){
        adjacencyCells.add(token);
    }

    public int getIndegree(){
        return inDegree;
    }

    public void setInDegree(int degree){
        this.inDegree = degree;
    }

    /**
     * Recursive method to output
     * the value of our expression tree
     * for this node
     * @param rootNode
     * @param spreadsheet
     * @return
     */
    public int getValue(ExpressionTreeNode rootNode, Spreadsheet spreadsheet){
        // Empty expression tree
        if(rootNode == null)
            return 0;

        // When a node becomes a leaf
        if(rootNode.left == null && rootNode.right == null){
            Token token = rootNode.getToken();
            if(token instanceof LiteralToken){
                LiteralToken literalToken = (LiteralToken) token;
                return literalToken.getValue();
            }else if(token instanceof CellToken){
                CellToken cellToken = (CellToken) token;
                return spreadsheet.getCellValue(cellToken);
            }
        }
        // Evaluate left subtree
        int leftVal = getValue(rootNode.left, spreadsheet);
        // Evaluate right subtree
        int rightVal = getValue(rootNode.right, spreadsheet);

        // Gets the parent node of left and right vals, which is an operator
        OperatorToken parentNode = (OperatorToken) rootNode.getToken();

        if (parentNode.getOperatorToken() == '+')
            return leftVal + rightVal;

        if (parentNode.getOperatorToken() == '-')
            return leftVal - rightVal;

        if (parentNode.getOperatorToken() == '*')
            return leftVal * rightVal;

        if(parentNode.getOperatorToken() == '^')
            return (int) Math.pow(leftVal, rightVal);

        return leftVal / rightVal;

    }
    /**
     * Calls the recursive getValue
     * and assigns it to the cells value
     * @param spreadsheet
     */
    public void Evaluate (Spreadsheet spreadsheet){
        this.value = getValue(this.expressionTree.getRoot(), spreadsheet);
    }
}
