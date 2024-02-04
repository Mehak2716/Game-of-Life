package org.example.modals;

import org.example.constants.State;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    @Test
    public void TestCellCreatedSuccessfully(){
        assertDoesNotThrow(()->{
            Position position = new Position(0,0);
            Cell cell = new Cell(State.Dead,position);
        });
    }

    @Test
    public void TestAddedNeighbourSuccessfully(){
        Position cellPosition = new Position(0,0);
        Position neighbourPosition = new Position(0,1);
        Cell cell = new Cell(State.Dead,cellPosition);
        Cell neighbourCell = new Cell(State.Dead,neighbourPosition);

        assertDoesNotThrow(()->{
           cell.addNeighbour(neighbourCell);
        });
    }

    @Test
    public void TestAddingNonNeighbour_ExpectException(){
        Position cellPosition = new Position(0,0);
        Position neighbourPosition = new Position(0,2);
        Cell cell = new Cell(State.Dead,cellPosition);
        Cell neighbourCell = new Cell(State.Dead,neighbourPosition);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cell.addNeighbour(neighbourCell);
        });

        String expectedMessage = "Not a Valid Neighbour";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void TestIsCellAliveForAliveCell_ExpectTrue(){
        Position cellPosition = new Position(0,1);
        Cell cell = new Cell(State.Alive,cellPosition);

        boolean isAlive = cell.isAlive();

        assertTrue(isAlive);
    }

    @Test
    public void TestIsCellAliveForDeadCell_ExpectFalse(){
        Position cellPosition = new Position(1,0);
        Cell cell = new Cell(State.Dead,cellPosition);

        boolean isAlive = cell.isAlive();

        assertFalse(isAlive);
    }

    @Test
    public void TestToggleStateForDeadCell_ExpectAliveState(){
        Position cellPosition = new Position(0,2);
        Cell cell = new Cell(State.Dead,cellPosition);

        cell.toggleState();
        boolean isAlive = cell.isAlive();

        assertTrue(isAlive);
    }

    @Test
    public void TestToggleStateForAliveCell_ExpectDeadState(){
        Position cellPosition = new Position(2,0);
        Cell cell = new Cell(State.Alive,cellPosition);

        cell.toggleState();
        boolean isAlive = cell.isAlive();

        assertFalse(isAlive);
    }

    @Test
    public void TestStateChangeForSingleAliveCellHavingNoNeighbour_ExpectTrue(){
        Position cellPosition = new Position(0,0);
        Cell cell = new Cell(State.Alive,cellPosition);

        boolean stateChange = cell.isStateChange();

        assertTrue(stateChange);
    }

    @Test
    public void TestStateChangeForSingleDeadCellHavingNoNeighbour_ExpectFalse(){
        Position cellPosition = new Position(0,0);
        Cell cell = new Cell(State.Dead,cellPosition);

        boolean stateChange = cell.isStateChange();

        assertFalse(stateChange);
    }

    @Test
    public void TestDeadCellStateChangeFor1AliveNeighbour_ExpectFalse(){
        Position cellPosition = new Position(0,0);
        Position neighbourPosition = new Position(0,1);
        Cell cell = new Cell(State.Dead,cellPosition);
        Cell neighbour = new Cell(State.Alive,neighbourPosition);

        cell.addNeighbour(neighbour);
        boolean stateChange = cell.isStateChange();

        assertFalse(stateChange);
    }

    @Test
    public void TestDeadCellStateChangeFor1DeadNeighbour_ExpectFalse(){
        Position cellPosition = new Position(0,2);
        Position neighbourPosition = new Position(0,3);
        Cell cell = new Cell(State.Dead,cellPosition);
        Cell neighbour = new Cell(State.Dead,neighbourPosition);

        cell.addNeighbour(neighbour);
        boolean stateChange = cell.isStateChange();

        assertFalse(stateChange);
    }

    @Test
    public void TestAliveCellStateChangeFor1DeadNeighbour_ExpectTrue(){
        Position cellPosition = new Position(1,0);
        Position neighbourPosition = new Position(0,1);
        Cell cell = new Cell(State.Alive,cellPosition);
        Cell neighbour = new Cell(State.Dead,neighbourPosition);

        cell.addNeighbour(neighbour);
        boolean stateChange = cell.isStateChange();

        assertTrue(stateChange);
    }

    @Test
    public void TestAliveCellStateChangeFor1AliveNeighbour_ExpectTrue(){
        Position cellPosition = new Position(0,0);
        Position neighbourPosition = new Position(1,0);
        Cell cell = new Cell(State.Alive,cellPosition);
        Cell neighbour = new Cell(State.Alive,neighbourPosition);

        cell.addNeighbour(neighbour);
        boolean stateChange = cell.isStateChange();

        assertTrue(stateChange);
    }

    @Test
    public void TestAliveCellStateChangeFor2AliveNeighbour_ExpectFalse(){
        Position cellPosition = new Position(0,0);
        Position firstNeighbourPosition = new Position(0,1);
        Position secondNeighbourPosition = new Position(1,0);
        Cell cell = new Cell(State.Alive,cellPosition);
        Cell firstNeighbour = new Cell(State.Alive,firstNeighbourPosition);
        Cell secondNeighbour = new Cell(State.Alive,secondNeighbourPosition);

        cell.addNeighbour(firstNeighbour);
        cell.addNeighbour(secondNeighbour);
        boolean stateChange = cell.isStateChange();

        assertFalse(stateChange);
    }

    @Test
    public void TestDeadCellStateChangeFor3AliveNeighbour_ExpectTrue(){
        Position cellPosition = new Position(0,0);
        Position firstNeighbourPosition = new Position(0,1);
        Position secondNeighbourPosition = new Position(1,0);
        Position thirdNeighbourPosition = new Position(1,1);
        Cell cell = new Cell(State.Dead,cellPosition);
        Cell firstNeighbour = new Cell(State.Alive,firstNeighbourPosition);
        Cell secondNeighbour = new Cell(State.Alive,secondNeighbourPosition);
        Cell thirdNeighbour = new Cell(State.Alive,thirdNeighbourPosition);

        cell.addNeighbour(firstNeighbour);
        cell.addNeighbour(secondNeighbour);
        cell.addNeighbour(thirdNeighbour);
        boolean stateChange = cell.isStateChange();

        assertTrue(stateChange);
    }

    @Test
    public void TestAliveCellStateChangeFor4AliveNeighbour_ExpectTrue(){
        Position cellPosition = new Position(1,1);
        Position firstNeighbourPosition = new Position(0,1);
        Position secondNeighbourPosition = new Position(1,0);
        Position thirdNeighbourPosition = new Position(1,2);
        Position fourthNeighbourPosition = new Position(2,2);
        Cell cell = new Cell(State.Alive,cellPosition);
        Cell firstNeighbour = new Cell(State.Alive,firstNeighbourPosition);
        Cell secondNeighbour = new Cell(State.Alive,secondNeighbourPosition);
        Cell thirdNeighbour = new Cell(State.Alive,thirdNeighbourPosition);
        Cell fourthNeighbour = new Cell(State.Alive,fourthNeighbourPosition);

        cell.addNeighbour(firstNeighbour);
        cell.addNeighbour(secondNeighbour);
        cell.addNeighbour(thirdNeighbour);
        cell.addNeighbour(fourthNeighbour);
        boolean stateChange = cell.isStateChange();

        assertTrue(stateChange);
    }
}
