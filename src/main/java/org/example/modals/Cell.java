package org.example.modals;

import org.example.constants.State;
import org.example.constants.Symbol;

public class Cell {
    private State state;
    private Neighbours neighbours;

    public Cell(State state) {

        if(state==State.Stable)
            throw new IllegalArgumentException("Cell can only be initilaized with Alive or Dead state");

        this.state = state;
        this.neighbours = new Neighbours();
    }

    public State evolve() throws IllegalArgumentException{
        int aliveNeighbours= neighbours.countAlive();
        if ((isAlive() && (aliveNeighbours < 2 || aliveNeighbours > 3))
                || (!isAlive() && aliveNeighbours == 3)) {
            toggleState();
            return this.state;
        }
        return State.Stable;
    }

    public void addNeighbour(Cell cell){
        neighbours.add(cell);
    }
    public boolean isAlive(){
        return state == State.Alive;
    }

    public void toggleState() {
        state = (state == State.Alive) ? State.Dead : State.Alive;
    }
    @Override
    public String toString() {
        return (state == State.Alive) ? Symbol.X.toString() : Symbol.O.toString();
    }

}
