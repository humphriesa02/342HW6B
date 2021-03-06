package main.spreadsheet;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Stack;

import static main.spreadsheet.SpreadsheetApp.readString;

public class SpreadsheetGUI {

    /**
     * runs the main window
     * @param args supplies command line arguments
     */
    public static void main(String[] args) {
        Spreadsheet s = new Spreadsheet(8);
        new MainWindow(s);
    }
}

class MainWindow extends JFrame implements ActionListener {
    JPanel mainPanel = new JPanel();
    JPanel cellPanel = new JPanel();
    JTextField[][] cellsText;

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public MainWindow(Spreadsheet spreadsheet) {
        int rows = spreadsheet.getNumRows();
        int columns = spreadsheet.getNumColumns();
        this.setTitle("Spreadsheet");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.add(mainPanel);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new GridLayout(1, 1));
        String[] columnNames = new String[columns];
        for(int i = 0; i < columns; i++){
            columnNames[i] = toLetter(i);
        }
        JTable jTable = new JTable(rows, columnNames);
        mainPanel.add(jTable);
        /*cellPanel.setLayout(new GridLayout(rows * 2, columns * 2));
        cellsText = new JTextField[rows][columns];

        //Creates an array that contains the JTextFields
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String c = toLetter(i);
                JLabel cellLabel = new JLabel(c + " " + j);
                cellLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                cellPanel.add(cellLabel);
                cellsText[i][j] = new JTextField();
                int finalI = i;
                int finalJ = j;
                cellsText[i][j].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        if(e.getKeyCode() == KeyEvent.VK_ENTER){
                            CellToken currCellToken = new CellToken();

                            JTextField cellsText = (JTextField) e.getSource();

                            currCellToken.setRow(finalI);
                            currCellToken.setColumn(finalJ);
                            Stack expressionStack = spreadsheet.getFormula(cellsText.getText());

                            spreadsheet.changeCellFormulaAndRecalculate(currCellToken, expressionStack, cellsText.getText());
                            cellsText.setText(spreadsheet.getCell(currCellToken).getValue() + "");
                            System.out.println(currCellToken.getRow() + " " + currCellToken.getColumn());
                        }
                    }
                });
                cellPanel.add(cellsText[i][j]);
            }
        }*/
        //mainPanel.add(cellPanel);
        setVisible(true);
    }
    public String toLetter(int c) {
        if( c/26 == 0 ) {
            return "" + (char)((int)'A' + (c%26));
        } else {
            return toLetter((c/26)-1) + (char)((int)'A' + (c%26));
        }
    }

}
