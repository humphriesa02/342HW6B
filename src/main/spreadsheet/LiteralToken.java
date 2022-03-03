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

    /**
     * Setter for value.
     */
    void setValue(int myValue) {
        value = myValue;
    }

    /**
     * Getter for value.
     */
    public int getValue() {
        return value;
    }

    //Override toString method.
    @Override
    //Convert integer to string for operations.
    public String toString() {
        return Integer.toString(value);
    }
}
