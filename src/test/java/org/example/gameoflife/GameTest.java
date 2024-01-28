package org.example.gameoflife;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {

    @Test
    public void test_GameForNegativeValuesExpectsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Game game = new Game(-1,0,0);
        });

        String expectedMessage = "Provided value must be non-negative";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void testGenerateRandomAliveCellsFor1Row1Column10SeedExpects0AliveCell() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        Game game = new Game(1,1,10);
        Method method =  Game.class.getDeclaredMethod("generateRandomAliveCells",null);
        method.setAccessible(true);
        List<Integer> result = (List<Integer>) method.invoke(game,  null);
        assertEquals(0,result.size());
    }

    @Test
    public void testGenerateRandomAliveCellsFor1Row2Column50SeedExpects1AliveCell() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        Game game = new Game(1,2,50);
        Method method =  Game.class.getDeclaredMethod("generateRandomAliveCells",null);
        method.setAccessible(true);
        List<Integer> result = (List<Integer>) method.invoke(game,  null);
        assertEquals(1,result.size());
    }

    @Test
    public void testGenerateRandomAliveCellsFor2Row3Column75SeedExpects4AliveCell() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        Game game = new Game(2,3,75);
        Method method =  Game.class.getDeclaredMethod("generateRandomAliveCells",null);
        method.setAccessible(true);
        List<Integer> result = (List<Integer>) method.invoke(game,  null);
        assertEquals(4,result.size());
    }

}
