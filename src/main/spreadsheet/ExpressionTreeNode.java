package main.spreadsheet;

public class ExpressionTreeNode {
    private Token token;

    ExpressionTreeNode left;
    ExpressionTreeNode right;

    public ExpressionTreeNode(Token token, ExpressionTreeNode left, ExpressionTreeNode right){
        this.token = token;
        this.left = left;
        this.right = right;
    }

    public Token getToken(){
        return token;
    }
}
