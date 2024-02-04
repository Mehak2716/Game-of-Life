package org.example.modals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    public void TestPositionCreatedSuccessfully(){
        assertDoesNotThrow(()->{
            Position position = new Position(0,0);
        });
    }

    @Test
    public void TestPositionCreatedWithNegativeInput_ExpectException(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           Position position = new Position(-1,-1);
        });

        String expectedMessage = "Position cannot be negative";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void TestIsValidNeighbourPositionForNeighbour_ExpectTrue(){
        Position position = new Position(0,0);
        Position neighbourPosition = new Position(0,1);

        boolean isValid=position.isValidNeighbourPosition(neighbourPosition);

        assertTrue(isValid);
    }

    @Test
    public void TestIsValidNeighbourPositionForNonNeighbour_ExpectFalse(){
        Position position = new Position(0,0);
        Position neighbourPosition = new Position(0,2);

        boolean isValid=position.isValidNeighbourPosition(neighbourPosition);

        assertFalse(isValid);
    }

    @Test
    public void TestIsValidNeighbourPositionForDiagonalNeighbour_ExpectTrue(){
        Position position = new Position(0,0);
        Position neighbourPosition = new Position(1,1);

        boolean isValid=position.isValidNeighbourPosition(neighbourPosition);

        assertTrue(isValid);
    }
}
