package main.spreadsheet;

import java.util.Stack;

public class Spreadsheet {
    // Final variables for rows and cols, default to them
    // if we do not specify the size of our spreadsheet in constructor
    private static final int ROWS = 4;
    private static final int COLS = 4;
    // container for our cells creating a grid of cells
    // columns wide and rows deep
    private static Cell[][] spreadsheetCells;
    // Default cell if we are out of bounds on a call
    private final int BadCell = -1;

    // default constructor makes spreadsheet of size ROWS x COLS
    public Spreadsheet(){
        spreadsheetCells = new Cell[ROWS][COLS];
        // Assigning the elements of the spreadsheet (originally null)
        // to new cells.
        for(int i = 0; i < spreadsheetCells.length; i++){
            for(int j = 0; j < spreadsheetCells[i].length; j++)
                spreadsheetCells[i][j] = new Cell();
        }
    }

    // Constructor passing in size in rowCols, makes a
    // spreadsheet of size rowCols x rowCols
    public Spreadsheet(int rowCols){
        spreadsheetCells = new Cell[rowCols][rowCols];
        for(int i = 0; i < spreadsheetCells.length; i++){
            for(int j = 0; j < spreadsheetCells[i].length; j++)
            spreadsheetCells[i][j] = new Cell();
        }

    }

    public int getNumColumns() {
        return spreadsheetCells[0].length;
    }

    public int getNumRows() {
        return spreadsheetCells.length;
    }

    public int getCellValue(CellToken cellToken){
        return spreadsheetCells[cellToken.getRow()][cellToken.getColumn()].getValue();
    }

    /**
     * Prints the value of each cell
     * in the spreadsheetCells array.
     */
    public void printValues(){
        for(int i = 0; i < spreadsheetCells.length; i++){
            for (int j = 0; j < spreadsheetCells[i].length; j++){
                System.out.println(spreadsheetCells[i][j].getValue());
            }
        }

    }

    /**
     * Prints all formulas of each cell
     * if they are not blank
     */
    public void printAllFormulas(){
        for(int i = 0; i < spreadsheetCells.length; i++){
            for (int j = 0; j < spreadsheetCells[i].length; j++){
                if(spreadsheetCells[i][j].getFormula() != ""){
                    System.out.println(spreadsheetCells[i][j].getFormula());
                }
            }
        }
    }

    public void setCellFormula(CellToken cellToken, String formula){
        spreadsheetCells[cellToken.getRow()][cellToken.getColumn()].setFormula(formula);
    }
    /**
     * Prints the formula of cellToken
     * by finding it inside spreadsheetCells
     * @param cellToken
     */
    public void printCellFormula(CellToken cellToken){
        /**
         * Given cellToken, print the formula it points to
         */
        System.out.println(spreadsheetCells[cellToken.getRow()][cellToken.getColumn()].getFormula());
    }

    public String printCellToken(CellToken cellToken){
        return cellToken.getRow() + " " + cellToken.getColumn();
    }

    /**
     * getFormula
     *
     * Given a string that represents a formula that is an infix
     * expression, return a stack of Tokens so that the expression,
     * when read from the bottom of the stack to the top of the stack,
     * is a postfix expression.
     *
     * A formula is defined as a sequence of tokens that represents
     * a legal infix expression.
     *
     * A token can consist of a numeric literal, a cell reference, or an
     * operator (+, -, *, /).
     *
     * Multiplication (*) and division (/) have higher precedence than
     * addition (+) and subtraction (-).  Among operations within the same
     * level of precedence, grouping is from left to right.
     *
     * This algorithm follows the algorithm described in Weiss, pages 105-108.
     */
    Stack getFormula(String formula) {
        Stack returnStack = new Stack();  // stack of Tokens (representing a postfix expression)
        boolean error = false;
        char ch = ' ';

        int literalValue = 0;

        CellToken cellToken = new CellToken();
        int column = 0;
        int row = 0;

        int index = 0;  // index into formula
        Stack operatorStack = new Stack();  // stack of operators

        while (index < formula.length() ) {
            // get rid of leading whitespace characters
            while (index < formula.length() ) {
                ch = formula.charAt(index);
                if (!Character.isWhitespace(ch)) {
                    break;
                }
                index++;
            }

            if (index == formula.length() ) {
                error = true;
                break;
            }

            // ASSERT: ch now contains the first character of the next token.
            if (OperatorToken.isOperator(ch)) {
                // We found an operator token
                switch (ch) {
                    case OperatorToken.Plus:
                    case OperatorToken.Minus:
                    case OperatorToken.Mult:
                    case OperatorToken.Div:
                    case OperatorToken.LeftParen:
                        // push operatorTokens onto the output stack until
                        // we reach an operator on the operator stack that has
                        // lower priority than the current one.
                        OperatorToken stackOperator;
                        while (!operatorStack.isEmpty()) {
                            stackOperator = (OperatorToken) operatorStack.peek();
                            if ( (stackOperator.priority() >= OperatorToken.operatorPriority(ch)) &&
                                    (stackOperator.getOperatorToken() != OperatorToken.LeftParen) ) {

                                // output the operator to the return stack
                                operatorStack.pop();
                                returnStack.push(stackOperator);
                            } else {
                                break;
                            }
                        }
                        break;

                    default:
                        // This case should NEVER happen
                        System.out.println("Error in getFormula.");
                        System.exit(0);
                        break;
                }
                // push the operator on the operator stack
                operatorStack.push(new OperatorToken(ch));

                index++;

            } else if (ch == ')') {    // maybe define OperatorToken.RightParen ?
                OperatorToken stackOperator;
                stackOperator = (OperatorToken) operatorStack.pop();
                // This code does not handle operatorStack underflow.
                while (stackOperator.getOperatorToken() != OperatorToken.LeftParen) {
                    // pop operators off the stack until a LeftParen appears and
                    // place the operators on the output stack
                    returnStack.push(stackOperator);
                    stackOperator = (OperatorToken) operatorStack.pop();
                }

                index++;
            } else if (Character.isDigit(ch)) {
                // We found a literal token
                literalValue = ch - '0';
                index++;
                while (index < formula.length()) {
                    ch = formula.charAt(index);
                    if (Character.isDigit(ch)) {
                        literalValue = (literalValue * 10) + (ch - '0');
                        index++;
                    } else {
                        break;
                    }
                }
                // place the literal on the output stack
                returnStack.push(new LiteralToken(literalValue));

            } else if (Character.isUpperCase(ch)) {
                // We found a cell reference token
                index = getCellToken(formula, index, cellToken);
                if (cellToken.getRow() == BadCell) {
                    error = true;
                    break;
                } else {
                    // place the cell reference on the output stack
                    returnStack.push(cellToken);
                }

            } else {
                error = true;
                break;
            }
        }

        // pop all remaining operators off the operator stack
        while (!operatorStack.isEmpty()) {
            returnStack.push(operatorStack.pop());
        }

        if (error) {
            // a parse error; return the empty stack
            returnStack.clear();
        }
        return returnStack;
    }
    /**
     * getCellToken
     *
     * Assuming that the next chars in a String (at the given startIndex)
     * is a cell reference, set cellToken's column and row to the
     * cell's column and row.
     * If the cell reference is invalid, the row and column of the return CellToken
     * are both set to BadCell (which should be a final int that equals -1).
     * Also, return the index of the position in the string after processing
     * the cell reference.
     * (Possible improvement: instead of returning a CellToken with row and
     * column equal to BadCell, throw an exception that indicates a parsing error.)
     *
     * A cell reference is defined to be a sequence of CAPITAL letters,
     * followed by a sequence of digits (0-9).  The letters refer to
     * columns as follows: A = 0, B = 1, C = 2, ..., Z = 25, AA = 26,
     * AB = 27, ..., AZ = 51, BA = 52, ..., ZA = 676, ..., ZZ = 701,
     * AAA = 702.  The digits represent the row number.
     *
     * @param inputString  the input string
     * @param startIndex  the index of the first char to process
     * @param cellToken  a cellToken (essentially a return value)
     * @return  index corresponding to the position in the string just after the cell reference
     */
    int getCellToken (String inputString, int startIndex, CellToken cellToken) {
        char ch;
        int column = 0;
        int row = 0;
        int index = startIndex;

        // handle a bad startIndex
        if ((startIndex < 0) || (startIndex >= inputString.length() )) {
            cellToken.setColumn(BadCell);
            cellToken.setRow(BadCell);
            return index;
        }

        // get rid of leading whitespace characters
        while (index < inputString.length() ) {
            ch = inputString.charAt(index);
            if (!Character.isWhitespace(ch)) {
                break;
            }
            index++;
        }
        if (index == inputString.length()) {
            // reached the end of the string before finding a capital letter
            cellToken.setColumn(BadCell);
            cellToken.setRow(BadCell);
            return index;
        }

        // ASSERT: index now points to the first non-whitespace character

        ch = inputString.charAt(index);
        // process CAPITAL alphabetic characters to calculate the column
        if (!Character.isUpperCase(ch)) {
            cellToken.setColumn(BadCell);
            cellToken.setRow(BadCell);
            return index;
        } else {
            column = ch - 'A';
            index++;
        }

        while (index < inputString.length() ) {
            ch = inputString.charAt(index);
            if (Character.isUpperCase(ch)) {
                column = ((column + 1) * 26) + (ch - 'A');
                index++;
            } else {
                break;
            }
        }
        if (index == inputString.length() ) {
            // reached the end of the string before fully parsing the cell reference
            cellToken.setColumn(BadCell);
            cellToken.setRow(BadCell);
            return index;
        }

        // ASSERT: We have processed leading whitespace and the
        // capital letters of the cell reference

        // read numeric characters to calculate the row
        if (Character.isDigit(ch)) {
            row = ch - '0';
            index++;
        } else {
            cellToken.setColumn(BadCell);
            cellToken.setRow(BadCell);
            return index;
        }

        while (index < inputString.length() ) {
            ch = inputString.charAt(index);
            if (Character.isDigit(ch)) {
                row = (row * 10) + (ch - '0');
                index++;
            } else {
                break;
            }
        }

        // successfully parsed a cell reference
        cellToken.setColumn(column);
        cellToken.setRow(row);
        return index;
    }

    /**
     * Currently finds the cell inside spreadsheetCells
     * using cellToken getRow() and getColumn(),
     * then calls the Cell .stackToTree function,
     * which takes a stack and sets the cell's
     * expression tree based on the stack
     * @param cellToken
     * @param expTreeTokenStack
     */
    void changeCellFormulaAndRecalculate(CellToken cellToken, Stack expTreeTokenStack){
        spreadsheetCells[cellToken.getRow()][cellToken.getColumn()].stackToTree(expTreeTokenStack, this);
    }
}
