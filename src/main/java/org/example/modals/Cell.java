package org.example.modals;

import org.example.constants.State;


public class Cell {
    private State state;
    private Position position;
    private Neighbours neighbours;

    public Cell(State state, Position position) {

        this.state = state;
        this.position = position;
        this.neighbours = new Neighbours();
    }

    public boolean isStateChange() throws IllegalArgumentException{
        int aliveNeighbours= neighbours.countAlive();
        return (isAlive() && (aliveNeighbours < 2 || aliveNeighbours > 3))
                || (!isAlive() && aliveNeighbours == 3);
    }

    public void addNeighbour(Cell cell){
        if(position.isValidNeighbourPosition(cell.position)){
            neighbours.add(cell);
            return;
        }
        throw new IllegalArgumentException("Not a Valid Neighbour");
    }
    public boolean isAlive(){
        return state == State.Alive;
    }

    public void toggleState() {
        state = (state == State.Alive) ? State.Dead : State.Alive;
    }
    @Override
    public String toString() {
        return (state == State.Alive) ? "*" : "_";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(!(obj instanceof Cell))
            return false;

        Cell cell = (Cell) obj;
        return this.state==cell.state && this.position.equals(cell.position);
    }

}
