package main.spreadsheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class Graph {

    // Dependency graph of Cells within a 2D array

    // Total vertices
    private int V;
    // Adjacency of cells
    //private ArrayList<ArrayList<Cell>> adjacent;

    private HashMap<Cell, ArrayList<CellToken>> adjacentMap;

    public Graph(Spreadsheet spreadsheet) {
        /*V = spreadsheet.getNumRows() + spreadsheet.getNumColumns();
        adjacent = new ArrayList<>(V);

        // First arraylist is of size total grid size
        for(int i = 0; i < V; i++){
            adjacent.add(new ArrayList<Cell>());
        }*/
    }

    public void addEdge(Cell cell, ArrayList<CellToken> cellTokens){
        adjacentMap.put(cell, cellTokens);
    }

    void topSortHelp(int v, boolean visited[], Stack<Cell> cellStack) {
        visited[v] = true;
        Cell currCell;

        Iterator<Cell> iterator = adjacent.get(v).iterator();

        while(iterator.hasNext()){
            currCell = iterator.next();
            if(!visited[currCell])
        }
    }
    /**
     * Loop through our graph, topologically,
     * then with each cell we find evaluate it
     * with its reference.
     */
    public void topSort() {

    }
}
