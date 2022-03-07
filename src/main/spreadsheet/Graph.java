package main.spreadsheet;

import java.util.*;

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

    /**
     * Topological sort based off
     * Kahn's algorithm
     * Runtime is O(V + E),
     * with V being vertices count and E
     * consisting of edges.
     */
    public void topSort(Spreadsheet spreadsheet) {
        Queue<Cell> cellQueue = new LinkedList<>();
        for(Cell c : addedCells){
            if(c.getIndegree() == 0)
                cellQueue.add(c);
            else
                throw new IllegalStateException("Spreadsheet has a cycle!");
        }

        LinkedList<Cell> vertexList = new LinkedList<>();

        while (!cellQueue.isEmpty()){
            Cell currCell = cellQueue.remove();
            vertexList.add(currCell);

            for(CellToken adjacent : currCell.getAdjacencyCells()){
                Cell adjacentCell = spreadsheet.getCell(adjacent);
                adjacentCell.setInDegree(adjacentCell.getIndegree() - 1);

                if(adjacentCell.getIndegree() == 0){
                    vertexList.add(adjacentCell);
                }
            }
        }
        if(vertexList.size() != addedCells.size())
            throw new IllegalStateException("Spreadsheet has a cycle!");

        for (Cell cell : vertexList)
            cell.Evaluate(spreadsheet);
    }
}
