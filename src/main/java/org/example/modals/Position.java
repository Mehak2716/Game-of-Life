package org.example.modals;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column){
        if(row<0 || column<0)
            throw new IllegalArgumentException("Position cannot be negative");

        this.row = row;
        this.column = column;
    }

    public boolean isValidNeighbourPosition(Position position){
        boolean isNeighbor = Math.abs(row - position.row) <= 1 && Math.abs(column - position.column) <= 1;
        return isNeighbor && !this.equals(position);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(!(obj instanceof Position))
            return false;

        Position position = (Position) obj;
        return this.row==position.row && this.column==position.column;
    }

}
