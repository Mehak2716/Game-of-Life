package org.example.gameoflife;

import org.example.modals.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class GameTest {

    @Mock
    Board mockBoard;

    @InjectMocks
    Game spyGame;

    @BeforeEach
    public void init() {
        spyGame = new Game(2,2,75);
        MockitoAnnotations.openMocks(this);
    }
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void TestGameForNegativeValues_ExpectsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Game game = new Game(-1,0,0);
        });

        String expectedMessage = "Provided value must be non-negative";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void TestStartGameWithInitialGenerationAllDead(){
        Game game = new Game(1,1,10);
        game.start();

        assertEquals("Initial Generation is all dead", outputStreamCaptor.toString().trim());
    }

    @Test
    public void TestWhenGameStartedInitialGenerationCreatedSuccessfully(){
        spyGame.start();

        verify(mockBoard,times(1)).initialGeneration(anyList());
    }

    @Test
    public void TestGameStopIfGenerationEnd(){

        when(mockBoard.isGenerationEnds()).thenReturn(true);
        spyGame.start();

        verify(mockBoard,times(0)).nextGeneration();
        verify(mockBoard,times(1)).isGenerationEnds();
    }

    @Test
    public void TestGameStopIfNoChangeInGeneration(){

        when(mockBoard.isGenerationEnds()).thenReturn(false).thenReturn(false);
        when(mockBoard.nextGeneration()).thenReturn(true).thenReturn(false);
        spyGame.start();

        verify(mockBoard,times(1)).initialGeneration(anyList());
        verify(mockBoard,times(2)).nextGeneration();
        verify(mockBoard,times(2)).isGenerationEnds();
    }

    @Test
    public void TestStartGameWithInitialGenerationHaving2AliveCell(){
        String expectedResult ="Generation 0 : \n" +
                "* * \n" +
                "Generation 1 : \n" +
                "_ _ \n" +
                "All Cell dies...Game ends";
        Game game = new Game(1,2,100);

        game.start();

        assertEquals(expectedResult, outputStreamCaptor.toString().trim());
    }

    @Test
    public void TestStartGameWithInitialGenerationHaving4AliveCell(){
        String expectedResult = "Generation 0 : \n" +
                "* * \n" +
                "* * \n" +
                "This Generation will keep on living";
        Game game = new Game(2,2,100);

        game.start();

        assertEquals(expectedResult, outputStreamCaptor.toString().trim());
    }

}
