package org.example.modals;

import org.example.constants.State;
import java.util.ArrayList;
import java.util.List;


public class Board {
    private final  int rows;
    private final int columns;
    private Cell [][] board;
    private int populationCount;

    public Board(int rows, int columns){
        if(rows<0 || columns<0)
            throw new IllegalArgumentException("Negative values are not acceptable");

        this.rows = rows;
        this.columns = columns;
        this.board = new Cell [rows][columns];
        initialize();
    }

    private void initialize(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                board[i][j] = initializeCell(i,j);
            }
        }
    }

    private Cell initializeCell(int row,int column){
        Cell cell = new Cell(State.Dead,new Position(row,column));
        int[] di = {-1, -1, 0, -1};
        int[] dj = {-1, 0, -1, 1};
        for (int k = 0; k < di.length; k++) {
            int x = row + di[k];
            int y = column + dj[k];
            if (x>=0 && y>=0 && x<rows && y<columns) {
                cell.addNeighbour(board[x][y]);
                board[x][y].addNeighbour(cell);
            }
        }
        return cell;
    }

    public void initialGeneration(List<Integer> aliveCellsIndex){
        if(aliveCellsIndex.isEmpty())
            throw new IllegalArgumentException("Initial Generation is all Dead");

        if(aliveCellsIndex.getLast()>=rows*columns)
            throw new IllegalArgumentException("Trying to access index out of bound cell");

        for (Integer cellsIndex : aliveCellsIndex) {
            int rowIndex = cellsIndex / columns;
            int columnIndex = cellsIndex % columns;
            board[rowIndex][columnIndex].toggleState();
            populationCount++;
        }
    }

    public boolean nextGeneration(){
        boolean generationChange=false;
        ArrayList<Cell> toggleStateList = new ArrayList<>();
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                Cell cell = board[i][j];
                if(cell.isStateChange()) {
                    populationCount += (cell.isAlive()) ? -1 : 1;
                    toggleStateList.add(cell);
                    generationChange = true;
                }
            }
        }
        updateGeneration(toggleStateList);
        return generationChange;
    }

    private void updateGeneration(ArrayList<Cell> toggleList){
        for(Cell cell:toggleList){
            cell.toggleState();
        }
    }
    public boolean isGenerationEnds(){
        return populationCount==0;
    }

    public void display(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
}
