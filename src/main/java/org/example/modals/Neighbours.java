package org.example.modals;

import java.util.ArrayList;

public class Neighbours {
    private ArrayList<Cell> neighbours;

    public Neighbours(){

        this.neighbours = new ArrayList<>();
    }

    public void add(Cell cell){

        neighbours.add(cell);
    }

    public int countAlive(){

        int aliveCount=0;
        for(Cell cell:neighbours){
            if(cell.isAlive()){
                aliveCount++;
            }
        }
        return aliveCount;
    }

}
