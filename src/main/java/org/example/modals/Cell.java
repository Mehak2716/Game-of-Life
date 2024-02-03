package org.example.modals;


public class Cell {
    private Symbol symbol;
    private Neighbours neighbours;

    public Cell(Symbol symbol) {
        this.symbol = symbol;
    }

    public int decision(){
        int aliveNeighbours= neighbours.countAlive();
        if(symbol==Symbol.X && (aliveNeighbours<2 || aliveNeighbours>3)){
            symbol = Symbol.O; // becomes dead
            return -1;
        }
        if(symbol==Symbol.O && aliveNeighbours==3){
            symbol = Symbol.X; //becomes alive
            return 1;
        }
        return 0;
    }

    public boolean isAlive(){
        return symbol==Symbol.X;
    }

    @Override
    public String toString() {
        return symbol.toString();
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == this)
            return true;

        if(!(obj instanceof Cell))
            return false;

       Cell cell = (Cell) obj;

        return this.symbol==cell.symbol;
    }

}
