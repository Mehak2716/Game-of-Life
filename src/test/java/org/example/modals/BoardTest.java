package org.example.modals;

import org.example.constants.State;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void TestCreateBoardSuccessfully(){

        assertDoesNotThrow(()->{
            Board board = new Board(1, 1);
        });

    }
    @Test
    public void TestCreateBoardWithNegativeArguments_ExpectsException() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Board board = new Board(-1, 0);
        });

        String expectedMessage = "Negative values are not acceptable";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void TestBoardInitializedProperlyHavingSingleCell() throws NoSuchFieldException, IllegalAccessException {
        Position cellPosition = new Position(0,0);
        Cell cell = new Cell(State.Dead,cellPosition);
        Cell [][] expectedBoard = {{cell}};

        Board board = new Board(1,1);
        Field field = Board.class.getDeclaredField("board");
        field.setAccessible(true);
        Cell[][] actualBoard = (Cell[][]) field.get(board);

        assertTrue(Arrays.deepEquals(actualBoard, expectedBoard));

    }

    @Test
    public void TestBoardInitializedProperlyHaving2Cells() throws NoSuchFieldException, IllegalAccessException {
        Position firstCellPosition = new Position(0,0);
        Position secondCellPosition = new Position(0,1);
        Cell firstCell = new Cell(State.Dead,firstCellPosition);
        Cell secondCell = new Cell(State.Dead,secondCellPosition);
        firstCell.addNeighbour(secondCell);
        secondCell.addNeighbour(firstCell);
        Cell [][] expectedBoard = {{firstCell,secondCell}};

        Board board = new Board(1,2);
        Field field = Board.class.getDeclaredField("board");
        field.setAccessible(true);
        Cell[][] actualBoard = (Cell[][]) field.get(board);

        assertTrue(Arrays.deepEquals(actualBoard, expectedBoard));

    }

    @Test
    public void TestBoardInitialGenerationWith1AliveCell() throws NoSuchFieldException, IllegalAccessException {
        Position cellPosition = new Position(0,0);
        Cell cell = new Cell(State.Alive,cellPosition);
        Cell [][] expectedBoard = {{cell}};

        Board board = new Board(1,1);
        List<Integer> initialGenerationIndex = new ArrayList<>(Arrays.asList(0));
        board.initialGeneration(initialGenerationIndex);
        Field field = Board.class.getDeclaredField("board");
        field.setAccessible(true);
        Cell[][] actualBoard = (Cell[][]) field.get(board);

        assertTrue(Arrays.deepEquals(actualBoard, expectedBoard));
    }

    @Test
    public void TestBoardInitialGenerationWithNooneAlive_ExpectException(){
        Board board = new Board(1,1);
        List<Integer> initialGenerationIndex = new ArrayList<>();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            board.initialGeneration(initialGenerationIndex);
        });

        String expectedMessage = "Initial Generation is all Dead";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }
    @Test
    public void TestBoardInitialGenerationWith2CellsHavingSecondCellAlive() throws NoSuchFieldException, IllegalAccessException {
        Position firstCellPosition = new Position(0,0);
        Position secondCellPosition = new Position(0,1);
        Cell firstCell = new Cell(State.Dead,firstCellPosition);
        Cell secondCell = new Cell(State.Alive,secondCellPosition);
        firstCell.addNeighbour(secondCell);
        secondCell.addNeighbour(firstCell);
        Cell [][] expectedBoard = {{firstCell,secondCell}};

        Board board = new Board(1,2);
        List<Integer> initialGenerationIndex = new ArrayList<>(Arrays.asList(1));
        board.initialGeneration(initialGenerationIndex);
        Field field = Board.class.getDeclaredField("board");
        field.setAccessible(true);
        Cell[][] actualBoard = (Cell[][]) field.get(board);

        assertTrue(Arrays.deepEquals(actualBoard, expectedBoard));
    }
    @Test
    public void TestBoardNextGenerationWith1AliveCell_ExpectGenerationChangeAndAllCellDead(){
        Board board = new Board(1,1);
        List<Integer> initialGenerationIndex = new ArrayList<>(Arrays.asList(0));

        board.initialGeneration(initialGenerationIndex);
        boolean isGenChange = board.nextGeneration();
        boolean isGenEnd = board.isGenerationEnds();

        assertTrue(isGenChange);
        assertTrue(isGenEnd);
    }

    @Test
    public void TestBoardNextGenerationWith2AliveCell_ExpectGenerationChangeAndAllCellDead(){
        Board board = new Board(1,2);
        List<Integer> initialGenerationIndex = new ArrayList<>(Arrays.asList(0,1));

        board.initialGeneration(initialGenerationIndex);
        boolean isGenChange = board.nextGeneration();
        boolean isGenEnd = board.isGenerationEnds();

        assertTrue(isGenChange);
        assertTrue(isGenEnd);
    }

    @Test
    public void TestBoardNextGenerationHaving3AliveCellEachOtherNeighbour_ExpectGenerationChangeAndNoEnd(){
        Board board = new Board(2,2);
        List<Integer> initialGenerationIndex = new ArrayList<>(Arrays.asList(0,1,2));

        board.initialGeneration(initialGenerationIndex);
        boolean isGenChange = board.nextGeneration();
        boolean isGenEnd = board.isGenerationEnds();

        assertTrue(isGenChange);
        assertFalse(isGenEnd);
    }

    @Test
    public void TestBoardNextGenerationHaving4AliveCellEachOtherNeighbour_ExpectNoGenerationChangeAndNoEnd(){
        Board board = new Board(2,2);
        List<Integer> initialGenerationIndex = new ArrayList<>(Arrays.asList(0,1,2,3));

        board.initialGeneration(initialGenerationIndex);
        boolean isGenChange = board.nextGeneration();
        boolean isGenEnd = board.isGenerationEnds();

        assertFalse(isGenChange);
        assertFalse(isGenEnd);
    }

    @Test
    public void TestBoardNextGenerationHavingNoAliveCell_ExpectNoGnerationChangeAndGenEnd(){
        Board board = new Board(2,2);

        boolean isGenChange = board.nextGeneration();
        boolean isGenEnd = board.isGenerationEnds();

        assertFalse(isGenChange);
        assertTrue(isGenEnd);
    }

}
