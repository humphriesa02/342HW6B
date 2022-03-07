package main.spreadsheet;

import java.util.*;

/**
 * Dependency Graph of Cells within
 * a 2D Spreadsheet represented by an
 * ArrayList
 */
public class Graph {
    // Cells in an ArrayList
    private ArrayList<Cell> addedCells;

    // Constructor setting the new ArrayList
    public Graph() {
        addedCells = new ArrayList<>();
    }

    // Adds cells to the list
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
        if(vertexList.size() != addedCells.size()){
            System.out.println("Spreadsheet has a cycle!");
            return;
        }

        // Looks at each cell in the VertexList and
        // Evaluates them
        for (Cell cell : vertexList)
            cell.Evaluate(spreadsheet);
    }
}
