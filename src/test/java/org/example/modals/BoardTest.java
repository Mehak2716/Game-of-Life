package org.example.modals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

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
    public void TestCreatingBoardInitialGenerationWithOutOfBoundCellIndex_ExpectException(){
        Board board = new Board(1,1);
        List<Integer> initialGenerationIndex = new ArrayList<>(Arrays.asList(1));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            board.initialGeneration(initialGenerationIndex);
        });

        String expectedMessage = "Trying to access index out of bound cell";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    public void TestBoardInitialGenerationGeneratedSuccessfully(){
        Board board = new Board(1,1);
        List<Integer> initialGenerationIndex = new ArrayList<>(Arrays.asList(0));

        assertDoesNotThrow(() -> {
            board.initialGeneration(initialGenerationIndex);
        });

    }
    @Test
    public void TestBoardNextGenerationWith1AliveCell_ExpectChangeInGenerationAndAllCellDead(){
        Board board = new Board(1,1);
        List<Integer> initialGenerationIndex = new ArrayList<>(Arrays.asList(0));

        board.initialGeneration(initialGenerationIndex);
        boolean isGenChange = board.nextGeneration();
        boolean isGenEnd = board.isGenerationEnds();

        assertTrue(isGenChange);
        assertTrue(isGenEnd);
    }

    @Test
    public void TestBoardNextGenerationWith2AliveCell_ExpectChangeInGenerationAndAllCellDead(){
        Board board = new Board(1,2);
        List<Integer> initialGenerationIndex = new ArrayList<>(Arrays.asList(0,1));

        board.initialGeneration(initialGenerationIndex);
        boolean isGenChange = board.nextGeneration();
        boolean isGenEnd = board.isGenerationEnds();

        assertTrue(isGenChange);
        assertTrue(isGenEnd);
    }

    @Test
    public void TestBoardNextGenerationHaving3AliveCellEachOtherNeighbour_ExpectGenerationChangeAndNoGenEnd(){
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

    @Test
    public void TestBoardNextGenerationFor3By3Board_ExpectToEndOnSecondGen(){
        Board board = new Board(3,3);
        List<Integer> initialGenerationIndex = new ArrayList<>(Arrays.asList(2,4,8));

        board.initialGeneration(initialGenerationIndex);
        boolean isFirstGenChange = board.nextGeneration();
        boolean isFirstGenEnd = board.isGenerationEnds();
        boolean isSecondGenChange = board.nextGeneration();
        boolean isSecondGenEnd = board.isGenerationEnds();

        assertTrue(isFirstGenChange);
        assertFalse(isFirstGenEnd);
        assertTrue(isSecondGenChange);
        assertTrue(isSecondGenEnd);
    }

    @Test
    public void TestBoardNextGenerationFor3By3Board_ExpectToEndOnFourthGen(){
        Board board = new Board(3,3);
        List<Integer> initialGenerationIndex = new ArrayList<>(Arrays.asList(1,2,3,4,5));

        board.initialGeneration(initialGenerationIndex);
        boolean isFirstGenChange = board.nextGeneration();
        boolean isFirstGenEnd = board.isGenerationEnds();
        boolean isSecondGenChange = board.nextGeneration();
        boolean isSecondGenEnd = board.isGenerationEnds();
        boolean isThirdGenChange = board.nextGeneration();
        boolean isThirdGenEnd = board.isGenerationEnds();
        boolean isFourthGenChange = board.nextGeneration();
        boolean isFourthGenEnd = board.isGenerationEnds();

        assertTrue(isFirstGenChange);
        assertFalse(isFirstGenEnd);
        assertTrue(isSecondGenChange);
        assertFalse(isSecondGenEnd);
        assertTrue(isThirdGenChange);
        assertFalse(isThirdGenEnd);
        assertTrue(isFourthGenChange);
        assertTrue(isFourthGenEnd);
    }

}
