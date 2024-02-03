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
    public void TestCreateCellWithStableState_ExpectException(){
        Position position = new Position(0,0);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           Cell cell = new Cell(State.Stable,position);
        });

        String expectedMessage = "Cell can only be initilaized with Alive or Dead state";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
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
    public void TestCellEvolutionWhenNoNeighbour_ExpectException(){
        Position cellPosition = new Position(0,0);
        Cell cell = new Cell(State.Dead,cellPosition);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            State evolve = cell.evolve();
        });

        String expectedMessage = "No Neighbour found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void TestDeadCellEvolutionFor1AliveNeighbour_ExpectStableState(){
        Position cellPosition = new Position(0,0);
        Position neighbourPosition = new Position(0,1);
        Cell cell = new Cell(State.Dead,cellPosition);
        Cell neighbour = new Cell(State.Alive,neighbourPosition);

        cell.addNeighbour(neighbour);
        State state = cell.evolve();

        assertEquals(state, State.Stable);
    }

    @Test
    public void TestDeadCellEvolutionFor1DeadNeighbour_ExpectStableState(){
        Position cellPosition = new Position(0,2);
        Position neighbourPosition = new Position(0,3);
        Cell cell = new Cell(State.Dead,cellPosition);
        Cell neighbour = new Cell(State.Dead,neighbourPosition);

        cell.addNeighbour(neighbour);
        State state = cell.evolve();

        assertEquals(state, State.Stable);
    }

    @Test
    public void TestAliveCellEvolutionFor1DeadNeighbour_ExpectDeadState(){
        Position cellPosition = new Position(1,0);
        Position neighbourPosition = new Position(0,1);
        Cell cell = new Cell(State.Alive,cellPosition);
        Cell neighbour = new Cell(State.Dead,neighbourPosition);

        cell.addNeighbour(neighbour);
        State state = cell.evolve();

        assertEquals(state, State.Dead);
    }

    @Test
    public void TestAliveCellEvolutionFor1AliveNeighbour_ExpectDeadState(){
        Position cellPosition = new Position(0,0);
        Position neighbourPosition = new Position(1,0);
        Cell cell = new Cell(State.Alive,cellPosition);
        Cell neighbour = new Cell(State.Alive,neighbourPosition);

        cell.addNeighbour(neighbour);
        State state = cell.evolve();

        assertEquals(state, State.Dead);
    }

    @Test
    public void TestAliveCellEvolutionFor2AliveNeighbour_ExpectStableState(){
        Position cellPosition = new Position(0,0);
        Position firstNeighbourPosition = new Position(0,1);
        Position secondNeighbourPosition = new Position(1,0);
        Cell cell = new Cell(State.Alive,cellPosition);
        Cell firstNeighbour = new Cell(State.Alive,firstNeighbourPosition);
        Cell secondNeighbour = new Cell(State.Alive,secondNeighbourPosition);

        cell.addNeighbour(firstNeighbour);
        cell.addNeighbour(secondNeighbour);
        State state = cell.evolve();

        assertEquals(state, State.Stable);
    }

    @Test
    public void TestDeadCellEvolutionFor3AliveNeighbour_ExpectAliveState(){
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
        State state = cell.evolve();

        assertEquals(state, State.Alive);
    }

    @Test
    public void TestAliveCellEvolutionFor4AliveNeighbour_ExpectDeadState(){
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
        State state = cell.evolve();

        assertEquals(state, State.Dead);
    }
}
