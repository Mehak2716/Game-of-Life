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
        if(neighbours.isEmpty())
            throw new IllegalArgumentException("No Neighbour found");

        int aliveCount=0;
        for(Cell cell:neighbours){
            if(cell.isAlive()){
                aliveCount++;
            }
        }
        return aliveCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof Neighbours))
            return false;

        Neighbours cellNeighbours = (Neighbours) obj;
        return this.neighbours.equals(cellNeighbours.neighbours);
    }

}
