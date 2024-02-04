package org.example.modals;

import org.example.constants.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NeighboursTest {

    @Test
    public void TestCreatedNeighboursSuccessfully(){
        assertDoesNotThrow(()->{
            Neighbours neighbours = new Neighbours();
        });

    }

    @Test
    public void TestAddedNeighbourSuccessfully(){
        Neighbours neighbours = new Neighbours();
        Position cellPosition = new Position(0,0);
        Cell cell = new Cell(State.Alive,cellPosition);

        assertDoesNotThrow(()->{
            neighbours.add(cell);
        });
    }

    @Test
    public void TestCountAliveNeighboursForNoNeighbours_Expect0(){
        Neighbours neighbours = new Neighbours();

        int count = neighbours.countAlive();

        assertEquals(count,0);
    }

    @Test
    public void TestCountAliveNeighboursFor1AliveNeighbours_Expect1(){
        Neighbours neighbours = new Neighbours();
        Position cellPosition = new Position(0,0);
        Cell cell = new Cell(State.Alive,cellPosition);

        neighbours.add(cell);
        int count = neighbours.countAlive();

        assertEquals(count,1);
    }

    @Test
    public void TestCountAliveNeighboursFor1DeadNeighbours_Expect0(){
        Neighbours neighbours = new Neighbours();
        Position cellPosition = new Position(0,0);
        Cell cell = new Cell(State.Dead,cellPosition);

        neighbours.add(cell);
        int count = neighbours.countAlive();

        assertEquals(count,0);
    }

    @Test
    public void TestCountAliveNeighboursFor2AliveNeighbours_Expect2(){
        Neighbours neighbours = new Neighbours();
        Position firstCellPosition = new Position(0,0);
        Position secondCellPosition = new Position(0,1);
        Cell firstCell = new Cell(State.Alive,firstCellPosition);
        Cell secondCell = new Cell(State.Alive,secondCellPosition);

        neighbours.add(firstCell);
        neighbours.add(secondCell);
        int count = neighbours.countAlive();

        assertEquals(count,2);
    }

    @Test
    public void TestCountAliveNeighboursFor2AliveAnd1DeadNeighbour_Expect2(){
        Neighbours neighbours = new Neighbours();
        Position firstCellPosition = new Position(0,0);
        Position secondCellPosition = new Position(0,1);
        Position thirdCellPosition = new Position(1,1);
        Cell firstCell = new Cell(State.Alive,firstCellPosition);
        Cell secondCell = new Cell(State.Alive,secondCellPosition);
        Cell thirdCell = new Cell(State.Dead,thirdCellPosition);

        neighbours.add(firstCell);
        neighbours.add(secondCell);
        neighbours.add(thirdCell);
        int count = neighbours.countAlive();

        assertEquals(count,2);
    }
}
