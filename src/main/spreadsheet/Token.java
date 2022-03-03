package main.spreadsheet;

public abstract class Token {

    private String tokenString = null;

    public String getTokenString(){
        return tokenString;
    }

    public void setTokenString(String val){
        tokenString = val;
    }
}
