package main.spreadsheet;

public class LiteralToken extends Token {
    private int value;

    // When we get a number only literal
    public LiteralToken(int value){
        this.value = value;
    }
    // When we get a reference to a cell
    public LiteralToken(String value){
        setTokenString(value);
    }
    //Convert integer to string for operations
    public String toString() {
        return Integer.toString(value);
    }
}
