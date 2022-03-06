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

    public ExpressionTreeNode getRoot(){
        return root;
    }

    // Empty tree.
    public void makeEmpty(){
        this.root = null;
    }

    /**
     * Makes a tree from stack of tokens.
     */
    public void buildExpressionTree(Stack<Token> stackOfTokens) {
        root = GetExpressionTree(stackOfTokens);
        if (!stackOfTokens.isEmpty()) {
            System.out.println("Cannot make a tree!");
        }
    }


    public void printTree(){
        
    }
//    public int Evaluate(Spreadsheet spreadsheet){
//
//    }

    /**
     * Internal building of tree, we call this on root
     * Sets roots children and all subsequent children
     * Based on the stack provided
     * @param s
     * @return
     */
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
        return null;
    }


}
