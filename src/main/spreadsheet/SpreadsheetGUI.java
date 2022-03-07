package main.spreadsheet;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    JPanel mainPanel = new JPanel();
    JPanel cellPanel = new JPanel();

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
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cellPanel.add(new JTextField());
            }
        }
        mainPanel.add(cellPanel);
        setVisible(true);
    }

}
