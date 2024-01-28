package org.example.modals;
import java.util.Arrays;
import java.util.List;


public class Board {
    private final  int rows;
    private final int columns;
    private Cell [][] board;
    private int [][] aliveNeighbours;

    private int aliveCellCount;
    private final int [] NEIGHBOUR_ROW_INDEX={-1,-1,-1,0,1,1,1,0};
    private final int [] NEIGHBOUR_COLUMN_INDEX={-1,0,1,1,1,0,-1,-1};


    public Board(int rows, int columns,List<Integer> aliveCells){
        if(rows<0 || columns<0)
            throw new IllegalArgumentException("Negative values are not acceptable");

        if(aliveCells.size()>rows*columns || (!aliveCells.isEmpty() && aliveCells.getLast()>=rows*columns))
            throw new IllegalArgumentException("Alice Cells List elements cannot exceed the Board size");

        this.rows = rows;
        this.columns = columns;
        this.board = new Cell [rows][columns];
        this.aliveNeighbours = new int [rows][columns];
        this.aliveCellCount = aliveCells.size();
        initializeWithAliveCells(aliveCells);
    }

    private void initializeWithAliveCells(List<Integer> aliveCellsIndexes){

        int k=0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(k<aliveCellsIndexes.size() && aliveCellsIndexes.get(k)/columns==i && aliveCellsIndexes.get(k)%columns==j){
                    board[i][j]= new Cell(Symbol.X);
                    updateNeighbours(i,j,1);
                    k++;
                }
                else{
                    board[i][j]=new Cell(Symbol.O);
                }
            }
        }
    }

    private void updateNeighbours(int row, int column,int decisionFactor){
        for(int i=0;i<NEIGHBOUR_ROW_INDEX.length;i++){
           int nRow = row + NEIGHBOUR_ROW_INDEX[i];
           int nCol = column + NEIGHBOUR_COLUMN_INDEX[i];
           if(nRow>=0 && nRow<rows && nCol>=0 && nCol<columns){
               aliveNeighbours[nRow][nCol]+=decisionFactor;
           }
        }
    }

    public boolean move(){
        int [][] aliveNeighboursTemp =  Arrays.stream(aliveNeighbours).map(int[]::clone).toArray(int[][]::new);
        boolean isStateChange = false;
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                int decision= board[i][j].decision(aliveNeighboursTemp[i][j]);
                if(decision!=0) {
                    isStateChange=true;
                    updateNeighbours(i,j,decision);
                    aliveCellCount+=decision;
                }
            }
        }
        return isStateChange;
    }

    public boolean isAllDead(){
        return aliveCellCount==0;
    }

    public void show(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }


}
