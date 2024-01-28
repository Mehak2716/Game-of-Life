package org.example.modals;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void createBoardWithNegativeArguments_ExpectsException() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Board board = new Board(-1, 0, new ArrayList<>());
        });

        String expectedMessage = "Negative values are not acceptable";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void createBoardWithListSizeGreaterThanBoardSize_ExpectsException() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Board board = new Board(1, 1, new ArrayList<>(Arrays.asList(0, 1)));
        });

        String expectedMessage = "Alice Cells List elements cannot exceed the Board size";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void createBoardWithCellsValueGreaterThanBoardSize_ExpectsException() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Board board = new Board(2, 2, new ArrayList<>(Arrays.asList(5)));
        });

        String expectedMessage = "Alice Cells List elements cannot exceed the Board size";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testInitializeBoardWith0AliveCell() throws IllegalAccessException, NoSuchFieldException {
        Board newBoard = new Board(1, 1, new ArrayList<>());
        Cell[][] expectedBoard = {
                {new Cell(Symbol.O)}
        };
        Field field = Board.class.getDeclaredField("board");
        field.setAccessible(true);
        Cell[][] result = (Cell[][]) field.get(newBoard);
        assertTrue(Arrays.deepEquals(expectedBoard, result));
    }

    @Test
    public void testInitiallizeBoardWith1AliveCell() throws IllegalAccessException, NoSuchFieldException {
        Board newBoard = new Board(1, 1, new ArrayList<>(Arrays.asList(0)));
        Cell[][] expectedBoard = {
                {new Cell( Symbol.X)}
        };
        Field field = Board.class.getDeclaredField("board");
        field.setAccessible(true);
        Cell[][] result = (Cell[][]) field.get(newBoard);
        assertTrue(Arrays.deepEquals(expectedBoard, result));
    }

    @Test
    public void testInitiallizeBoardWith2AliveCell() throws IllegalAccessException, NoSuchFieldException {
        Board newBoard = new Board(2, 2, new ArrayList<>(Arrays.asList(0, 3)));
        Cell[][] expectedBoard = {
                {new Cell( Symbol.X), new Cell( Symbol.O)},
                {new Cell( Symbol.O), new Cell( Symbol.X)}
        };
        Field field = Board.class.getDeclaredField("board");
        field.setAccessible(true);
        Cell[][] result = (Cell[][]) field.get(newBoard);
        assertTrue(Arrays.deepEquals(expectedBoard, result));
    }

    @Test
    /**
     * For Only 1 Alive Cell, there would be no neighbours, So alive Neighbour Count would be 0
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public void testUpdateNeighboursFor1AliveCell() throws NoSuchFieldException, IllegalAccessException {
        Board newBoard = new Board(1, 1, new ArrayList<>(Arrays.asList(0)));
        int[][] expectedNeighbours = {{0}};
        Field field = Board.class.getDeclaredField("aliveNeighbours");
        field.setAccessible(true);
        int[][] result = (int[][]) field.get(newBoard);
        assertTrue(Arrays.deepEquals(expectedNeighbours, result));
    }

    @Test
    /**
     * For Alive Cell at Position 0 and 3, there neighbours will update their alive neighbour count
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public void testUpdateNeighboursFor2AliveCell() throws NoSuchFieldException, IllegalAccessException {
        Board newBoard = new Board(2, 2, new ArrayList<>(Arrays.asList(0, 3)));
        int[][] expectedNeighbours = {
                {1, 2},
                {2, 1}
        };
        Field field = Board.class.getDeclaredField("aliveNeighbours");
        field.setAccessible(true);
        int[][] result = (int[][]) field.get(newBoard);
        assertTrue(Arrays.deepEquals(expectedNeighbours, result));
    }


    @Test
    /**
     * After making move, Single Dead Cell remains dead because of no alive neighbours
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public void testBoardAfterMoveFor0AliceCells() throws NoSuchFieldException, IllegalAccessException {
        Board newBoard = new Board(1, 1, new ArrayList<>());
        newBoard.move();
        Cell[][] expectedBoard = {
                {new Cell( Symbol.O)}
        };
        Field field = Board.class.getDeclaredField("board");
        field.setAccessible(true);
        Cell[][] result = (Cell[][]) field.get(newBoard);
        assertTrue(Arrays.deepEquals(expectedBoard, result));
    }


    @Test
    /**
     * After making move, Single Cell Alive died due to underpopulation
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public void testBoardAfterMoveFor1AliceCells() throws NoSuchFieldException, IllegalAccessException {
        Board newBoard = new Board(1, 1, new ArrayList<>(Arrays.asList(0)));
        newBoard.move();
        Cell[][] expectedBoard = {
                {new Cell( Symbol.O)}
        };
        Field field = Board.class.getDeclaredField("board");
        field.setAccessible(true);
        Cell[][] result = (Cell[][]) field.get(newBoard);
        assertTrue(Arrays.deepEquals(expectedBoard, result));
    }


    @Test
    /**
     * After making move, Only Dead Cell becomes alive due to reproduction
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public void testBoardAfterMoveFor3AliceCells() throws NoSuchFieldException, IllegalAccessException {
        Board newBoard = new Board(2, 2, new ArrayList<>(Arrays.asList(0, 1, 2)));
        newBoard.move();
        Cell[][] expectedBoard = {
                {new Cell( Symbol.X), new Cell( Symbol.X)},
                {new Cell( Symbol.X), new Cell( Symbol.X)}
        };
        Field field = Board.class.getDeclaredField("board");
        field.setAccessible(true);
        Cell[][] result = (Cell[][]) field.get(newBoard);
        assertTrue(Arrays.deepEquals(expectedBoard, result));
    }

    @Test
    /**
     * After making move, Alive Cell at 1 and 7 dies due to underpopulation and
     * Cell at 3 and 5  becomes alive due to reproduction
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public void testBoardAfterMoveFor3AliceCellsForGridSize3() throws NoSuchFieldException, IllegalAccessException {
        Board newBoard = new Board(3, 3, new ArrayList<>(Arrays.asList(1, 4, 7)));
        newBoard.move();
        Cell[][] expectedBoard = {
                {new Cell( Symbol.O), new Cell( Symbol.O),new Cell(Symbol.O)},
                {new Cell( Symbol.X), new Cell( Symbol.X),new Cell( Symbol.X)},
                {new Cell( Symbol.O), new Cell( Symbol.O),new Cell(Symbol.O)}
        };
        Field field = Board.class.getDeclaredField("board");
        field.setAccessible(true);
        Cell[][] result = (Cell[][]) field.get(newBoard);
        assertTrue(Arrays.deepEquals(expectedBoard, result));
    }

    @Test
    /**
     * After making move, No Change in alive Neighbours as no cell dies or reproduce.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public void testNeighboursAfterMoveFor0AliceCells() throws NoSuchFieldException, IllegalAccessException {
        Board newBoard = new Board(1, 1, new ArrayList<>());
        newBoard.move();
        int[][] expectedBoard = {{0}};
        Field field = Board.class.getDeclaredField("aliveNeighbours");
        field.setAccessible(true);
        int[][] result = (int[][]) field.get(newBoard);
        assertTrue(Arrays.deepEquals(expectedBoard, result));
    }

    @Test
    /**
     * After making move, alive Neighbours count reduce to 0 as only alive cell dies due to underpopulation
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public void testNeighboursAfterMoveFor1AliceCells() throws NoSuchFieldException, IllegalAccessException {
        Board newBoard = new Board(2, 2, new ArrayList<>(Arrays.asList(1)));
        newBoard.move();
        int[][] expectedBoard = {{0,0},{0,0}};
        Field field = Board.class.getDeclaredField("aliveNeighbours");
        field.setAccessible(true);
        int[][] result = (int[][]) field.get(newBoard);
        assertTrue(Arrays.deepEquals(expectedBoard, result));
    }

    @Test
    /**
     * After making move, alive Neighbours count increases as only dead cell becomes alive due to reproduction
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public void testNeighboursAfterMoveFor3AliceCells() throws NoSuchFieldException, IllegalAccessException {
        Board newBoard = new Board(2, 2, new ArrayList<>(Arrays.asList(0,1,2)));
        newBoard.move();
        int[][] expectedBoard = {{3,3},{3,3}};
        Field field = Board.class.getDeclaredField("aliveNeighbours");
        field.setAccessible(true);
        int[][] result = (int[][]) field.get(newBoard);
        assertTrue(Arrays.deepEquals(expectedBoard, result));
    }
    @Test
    public void testMoveForNoFurtherGenerationChange(){
        Board newBoard = new Board(3, 3, new ArrayList<>(Arrays.asList(1,2,4,5)));
        assertFalse(newBoard.move());
    }
    @Test
    public void testIfAllCellsDeadFor0AliveCellsExpectsTrue()
    {
        Board board = new Board(1,1,new ArrayList<>());
        assertTrue(board.isAllDead());
    }

    @Test
    public void testIfAllCellsDeadFor1AliveCellsExpectsTrue()
    {
        Board board = new Board(1,1,new ArrayList<>(Arrays.asList(0)));
        assertFalse(board.isAllDead());
    }

}
