package main.spreadsheet;

import java.util.Stack;

public class ExpressionTree {

    private ExpressionTreeNode root;

    // Initialize an Empty tree.
    public ExpressionTree() {
        this.root = null;
    }

    // Initialize ExpressionTree with root.
    public ExpressionTree(final ExpressionTreeNode myRoot) {
        this.root = myRoot;
    }

    // Empty tree.
    public void makeEmpty(){
        this.root = null;
    }

    /**
     * Makes a tree from stack of tokens.
     */
    public void tokenTree(Stack<Token> stackOfTokens) {
        root = getTokens(stackOfTokens);
        if (!stackOfTokens.isEmpty()) {
            System.out.println("Cannot make a tree!");
        }
    }

    /**
     * Organize tokens from stack into a tree. (STILL WORKING ON METHOD)
     */
    public ExpressionTreeNode getTokens(Stack<Token> stackOfTokens) {
        ExpressionTreeNode tree = null;
        Token token;

        if(stackOfTokens.isEmpty())
            return null;

        return null; // ignore for now.
    }
    public void printTree(){

    }
//    public int Evaluate(Spreadsheet spreadsheet){
//
//    }

    ExpressionTreeNode GetExpressionTree(Stack s) {
        ExpressionTreeNode returnTree;
        Token token;
        if (s.isEmpty())
            return null;
        token = (Token) s.pop(); // need to handle stack underflow
        if ((token instanceof LiteralToken) ||
                (token instanceof CellToken) ) {
            // Literals and Cells are leaves in the expression tree
            returnTree = new ExpressionTreeNode(token, null, null);
            return returnTree;
        } else if (token instanceof OperatorToken) {
            // Continue finding tokens that will form the
            // right subtree and left subtree.
            ExpressionTreeNode rightSubtree = GetExpressionTree (s);
            ExpressionTreeNode leftSubtree = GetExpressionTree (s);
            returnTree =
                    new ExpressionTreeNode(token, leftSubtree, rightSubtree);
            return returnTree;
        }
    }

}
