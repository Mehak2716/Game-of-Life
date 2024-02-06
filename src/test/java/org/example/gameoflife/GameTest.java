package org.example.gameoflife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {

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
