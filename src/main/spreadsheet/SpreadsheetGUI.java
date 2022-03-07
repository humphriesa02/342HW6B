package main.spreadsheet;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import static main.spreadsheet.SpreadsheetApp.readString;

public class SpreadsheetGUI {

    /**
     * runs the main window
     * @param args supplies command line arguments
     */
    public static void main(String[] args) {
        Spreadsheet s = new Spreadsheet(8);
        new MainWindow(s.getNumRows(), s.getNumColumns());
    }
}

class MainWindow extends JFrame implements ActionListener {
    Spreadsheet theSpreadsheet = new Spreadsheet(8);
    JPanel mainPanel = new JPanel();
    JPanel cellPanel = new JPanel();
    JTextField[][] cellsText;

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public MainWindow(int rows, int columns) {
        this.setTitle("Spreadsheet");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.add(mainPanel);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new GridLayout(1, 1));
        cellPanel.setLayout(new GridLayout(rows, columns));
        cellsText = new JTextField[rows][columns];
        Action changeCell = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String inputCell;
                String inputFormula;
                CellToken cellToken = new CellToken();
                Stack expTreeTokenStack;

                inputFormula = readString();
                expTreeTokenStack = theSpreadsheet.getFormula(inputFormula);
                theSpreadsheet.changeCellFormulaAndRecalculate(cellToken, expTreeTokenStack, inputFormula);


            }
        };
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cellsText[i][j] = new JTextField();
                cellsText[i][j].setText(i + " " + j);
                cellsText[i][j].addActionListener(changeCell);
                cellPanel.add(cellsText[i][j]);
            }
        }
        mainPanel.add(cellPanel);
        setVisible(true);
    }

}
