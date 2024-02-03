package org.example.modals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Cell cell = new Cell(Symbol.X);

        assertDoesNotThrow(()->{
            neighbours.add(cell);
        });
    }

    @Test
    public void TestCountAliveNeighboursFor0Neighbours(){
        Neighbours neighbours = new Neighbours();

        int count = neighbours.countAlive();

        assertEquals(count,0);
    }

    @Test
    public void TestCountAliveNeighboursFor1AliveNeighbours(){
        Neighbours neighbours = new Neighbours();
        Cell cell = new Cell(Symbol.X);

        neighbours.add(cell);
        int count = neighbours.countAlive();

        assertEquals(count,1);
    }

    @Test
    public void TestCountAliveNeighboursFor1DeadNeighbours(){
        Neighbours neighbours = new Neighbours();
        Cell cell = new Cell(Symbol.O);

        neighbours.add(cell);
        int count = neighbours.countAlive();

        assertEquals(count,0);
    }

    @Test
    public void TestCountAliveNeighboursFor2AliveNeighbours(){
        Neighbours neighbours = new Neighbours();
        Cell firstCell = new Cell(Symbol.X);
        Cell secondCell = new Cell(Symbol.X);

        neighbours.add(firstCell);
        neighbours.add(secondCell);
        int count = neighbours.countAlive();

        assertEquals(count,2);
    }
}
