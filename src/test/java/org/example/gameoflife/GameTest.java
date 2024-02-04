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

    private final PrintStream standardOut = System.out;
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
    public void TestGenerateRandomAliveCellsFor1CellWith10Seed_ExpectsNoAliveCell() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        Game game = new Game(1,1,10);
        Method method =  Game.class.getDeclaredMethod("generateRandomAliveCells",null);
        method.setAccessible(true);
        List<Integer> result = (List<Integer>) method.invoke(game,  null);
        assertEquals(0,result.size());
    }

    @Test
    public void TestGenerateRandomAliveCellsFor2CellWith50Seed_Expects1AliveCell() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        Game game = new Game(1,2,50);
        Method method =  Game.class.getDeclaredMethod("generateRandomAliveCells",null);
        method.setAccessible(true);
        List<Integer> result = (List<Integer>)method.invoke(game,  null);
        assertEquals(1,result.size());
    }

    @Test
    public void TestGenerateRandomAliveCellsFor6CellWith75Seed_Expects4AliveCell() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        Game game = new Game(2,3,75);
        Method method =  Game.class.getDeclaredMethod("generateRandomAliveCells",null);
        method.setAccessible(true);
        List<Integer> result = (List<Integer>) method.invoke(game,  null);
        assertEquals(4,result.size());
    }

    @Test
    public void TestStartGameWithInitialGenerationAllDead(){
        Game game = new Game(1,1,10);
        game.start();

        assertEquals("Initial Generation is all dead", outputStreamCaptor.toString().trim());
    }

    @Test
    public void TestStartGameWithInitialGenerationHaving1AliveCell(){
        String expectedResult = "Generation 1 : \n" + "* * \n" +
                                "All Cell dies...Game ends";
        Game game = new Game(1,2,100);

        game.start();

        assertEquals(expectedResult, outputStreamCaptor.toString().trim());
    }

    @Test
    public void TestStartGameWithInitialGenerationHaving4AliveCell(){
        String expectedResult = "Generation 1 : \n" +
                "* * \n" +
                "* * \n" +
                "This Generation will keep on living";
        Game game = new Game(2,2,100);

        game.start();

        assertEquals(expectedResult, outputStreamCaptor.toString().trim());
    }

}
