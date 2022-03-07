package main.spreadsheet;

import java.util.ArrayList;
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

    private ArrayList<CellToken> adjacencyCells;

    // Constructor for cell, sets each initial
    // cell to have value 0, no formula,
    // and a new expression tree based on the value 0
    public Cell(){
        formula = "";
        value = 0;
        expressionTree = new ExpressionTree();
        adjacencyCells = new ArrayList<>();
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

    public void setAdjacencyCells(CellToken token){
        adjacencyCells.add(token);
    }
    public int getValue(ExpressionTreeNode rootNode, Spreadsheet spreadsheet){
        /**
         * Look through the tree and find value
         */
        if(rootNode == null)
            return 0;

        // Leaf node, value
        if(rootNode.left == null && rootNode.left == null){
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

        OperatorToken parentNode = (OperatorToken) rootNode.getToken();


        if (parentNode.getOperatorToken() == '+'){
            return leftVal + rightVal;
        }
        if (parentNode.getOperatorToken() == '-'){
            return leftVal - rightVal;
        }

        if (parentNode.getOperatorToken() == '*')
            return leftVal * rightVal;

        return leftVal / rightVal;

    }


    public void Evaluate (Spreadsheet spreadsheet){
        value = getValue(expressionTree.getRoot(), spreadsheet);
    }
    


}
