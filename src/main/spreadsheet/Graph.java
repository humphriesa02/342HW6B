package main.spreadsheet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class Graph {

    // Dependency graph of Cells within a 2D array

    // Adjacency of cells
    private ArrayList<Cell> addedCells;


    public Graph(Spreadsheet spreadsheet) {
        addedCells = new ArrayList<>();
    }

    public void addEdge(Cell cell){
        addedCells.add(cell);
    }

    void topSortHelp(int v, boolean visited[], Stack<Cell> cellStack) {
        visited[v] = true;

        for(int i = 0; i < addedCells.size(); i++){
            if(!visited[i])
                topSortHelp(i, visited, cellStack);
        }

        cellStack.push(addedCells.get(v));
    }
    /**
     * Loop through our graph, topologically,
     * then with each cell we find evaluate it
     * with its reference.
     */
    public void topSort(Spreadsheet spreadsheet) {

        Stack<Cell> sortedStack = new Stack<>();

        boolean visited[] = new boolean[addedCells.size()];

        for(int i = 0; i < addedCells.size(); i++)
            visited[i] = false;

        for(int i = 0; i < addedCells.size(); i++){
            if(!visited[i])
                topSortHelp(i,visited,sortedStack);
        }

        while(!sortedStack.isEmpty()){
            Cell evalCell = sortedStack.pop();
            evalCell.Evaluate(spreadsheet);
        }
    }
}
