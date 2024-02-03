package org.example.modals;


import org.example.constants.State;
import org.example.constants.Symbol;

public class Cell {
    private State state;
    private Neighbours neighbours;

    public Cell(State state) {

        this.state = state;
        this.neighbours = new Neighbours();
    }

    public int decision(){
//        int aliveNeighbours= neighbours.countAlive();
//        if(symbol==Symbol.X && (aliveNeighbours<2 || aliveNeighbours>3)){
//            symbol = Symbol.O; // becomes dead
//            return -1;
//        }
//        if(symbol==Symbol.O && aliveNeighbours==3){
//            symbol = Symbol.X; //becomes alive
//            return 1;
//        }
//        return 0;
        return -1;
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
