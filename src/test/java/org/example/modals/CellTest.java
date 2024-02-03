package org.example.modals;

import org.example.constants.State;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    @Test
    public void TestCellCreatedSuccessfully(){
        assertDoesNotThrow(()->{
            Cell cell = new Cell(State.Dead);
        });
    }

    @Test
    public void TestCreateCellWithStableState_ExpectException(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           Cell cell = new Cell(State.Stable);
        });

        String expectedMessage = "Cell can only be initilaized with Alive or Dead state";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void TestAddedNeighbourSuccessfully(){
        Cell cell = new Cell(State.Dead);
        Cell neighbourCell = new Cell(State.Dead);

        assertDoesNotThrow(()->{
           cell.addNeighbour(neighbourCell);
        });
    }

    @Test
    public void TestIsCellAliveForAliveCell_ExpectTrue(){
        Cell cell = new Cell(State.Alive);

        boolean isAlive = cell.isAlive();

        assertTrue(isAlive);
    }

    @Test
    public void TestIsCellAliveForDeadCell_ExpectFalse(){
        Cell cell = new Cell(State.Dead);

        boolean isAlive = cell.isAlive();

        assertFalse(isAlive);
    }

    @Test
    public void TestToggleStateForDeadCell_ExpectAliveState(){
        Cell cell = new Cell(State.Dead);

        cell.toggleState();
        boolean isAlive = cell.isAlive();

        assertTrue(isAlive);
    }

    @Test
    public void TestToggleStateForAliveCell_ExpectDeadState(){
        Cell cell = new Cell(State.Alive);

        cell.toggleState();
        boolean isAlive = cell.isAlive();

        assertFalse(isAlive);
    }

    @Test
    public void TestCellEvolutionWhenNoNeighbour_ExpectException(){
        Cell cell = new Cell(State.Dead);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            State evolve = cell.evolve();
        });

        String expectedMessage = "No Neighbour found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void TestDeadCellEvolutionFor1AliveNeighbour_ExpectStableState(){
        Cell cell = new Cell(State.Dead);
        Cell neighbour = new Cell(State.Alive);

        cell.addNeighbour(neighbour);
        State state = cell.evolve();

        assertEquals(state, State.Stable);
    }

    @Test
    public void TestDeadCellEvolutionFor1DeadNeighbour_ExpectStableState(){
        Cell cell = new Cell(State.Dead);
        Cell neighbour = new Cell(State.Dead);

        cell.addNeighbour(neighbour);
        State state = cell.evolve();

        assertEquals(state, State.Stable);
    }

    @Test
    public void TestAliveCellEvolutionFor1DeadNeighbour_ExpectDeadState(){
        Cell cell = new Cell(State.Alive);
        Cell neighbour = new Cell(State.Dead);

        cell.addNeighbour(neighbour);
        State state = cell.evolve();

        assertEquals(state, State.Dead);
    }

    @Test
    public void TestAliveCellEvolutionFor1AliveNeighbour_ExpectDeadState(){
        Cell cell = new Cell(State.Alive);
        Cell neighbour = new Cell(State.Alive);

        cell.addNeighbour(neighbour);
        State state = cell.evolve();

        assertEquals(state, State.Dead);
    }

    @Test
    public void TestAliveCellEvolutionFor2AliveNeighbour_ExpectStableState(){
        Cell cell = new Cell(State.Alive);
        Cell firstNeighbour = new Cell(State.Alive);
        Cell secondNeighbour = new Cell(State.Alive);

        cell.addNeighbour(firstNeighbour);
        cell.addNeighbour(secondNeighbour);
        State state = cell.evolve();

        assertEquals(state, State.Stable);
    }

    @Test
    public void TestDeadCellEvolutionFor3AliveNeighbour_ExpectAliveState(){
        Cell cell = new Cell(State.Dead);
        Cell firstNeighbour = new Cell(State.Alive);
        Cell secondNeighbour = new Cell(State.Alive);
        Cell thirdNeighbour = new Cell(State.Alive);

        cell.addNeighbour(firstNeighbour);
        cell.addNeighbour(secondNeighbour);
        cell.addNeighbour(thirdNeighbour);
        State state = cell.evolve();

        assertEquals(state, State.Alive);
    }

    @Test
    public void TestAliveCellEvolutionFor4AliveNeighbour_ExpectDeadState(){
        Cell cell = new Cell(State.Alive);
        Cell firstNeighbour = new Cell(State.Alive);
        Cell secondNeighbour = new Cell(State.Alive);
        Cell thirdNeighbour = new Cell(State.Alive);
        Cell fourthNeighbour = new Cell(State.Alive);

        cell.addNeighbour(firstNeighbour);
        cell.addNeighbour(secondNeighbour);
        cell.addNeighbour(thirdNeighbour);
        cell.addNeighbour(fourthNeighbour);
        State state = cell.evolve();

        assertEquals(state, State.Dead);
    }
}
