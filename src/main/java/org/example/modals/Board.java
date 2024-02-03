package org.example.modals;
import org.example.constants.State;

import java.util.List;


public class Board {
    private final  int rows;
    private final int columns;
    private Cell [][] board;


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
                board[i][j] = new Cell(State.Dead);

                int[] di = {-1, -1, 0};
                int[] dj = {-1, 0, -1};

                for (int k = 0; k < di.length; k++) {
                    int x = i + di[k];
                    int y = j + dj[k];

                    if (x>=0 && y>=0) {
                        board[i][j].addNeighbour(board[x][y]);
                        board[x][y].addNeighbour(board[i][j]);
                    }
                }
            }
        }
    }
    public void initialGeneration(List<Integer> aliveCellsIndex){
        if(aliveCellsIndex.isEmpty())
            throw new IllegalArgumentException("Initial Generation is all Dead");

        for (Integer cellsIndex : aliveCellsIndex) {
            int rowIndex = cellsIndex / columns;
            int columnIndex = cellsIndex % columns;
            board[rowIndex][columnIndex].toggleState();
        }
    }

//    public boolean move(){
//        int [][] aliveNeighboursTemp =  Arrays.stream(aliveNeighbours).map(int[]::clone).toArray(int[][]::new);
//        boolean isStateChange = false;
//        for(int i=0;i<rows;i++){
//            for(int j=0;j<columns;j++){
//                int decision= board[i][j].decision();
//                if(decision!=0) {
//                    isStateChange=true;
//                    updateNeighbours(i,j,decision);
//                    aliveCellCount+=decision;
//                }
//            }
//        }
//        return isStateChange;
//    }

//    public boolean isAllDead(){
//        return aliveCellCount==0;
//    }

    public void show(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }


}
