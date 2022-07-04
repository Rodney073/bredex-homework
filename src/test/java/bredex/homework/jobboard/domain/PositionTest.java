package bredex.homework.jobboard.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    private Position position;

    @BeforeEach
    public void init() {
        position = new Position();
    }

    @Test
    public void positionNameLongerThan50CharsShouldThrowException() {

        //Test 51 Chars
        InvalidPositionNameException ex = assertThrows(InvalidPositionNameException.class, () -> {
            position.validatePositionName("PositionNamePositionNamePositionNamePositionNameNam");
        });

        assertEquals("The position name can't be longer than 50 characters!", ex.getMessage());
    }


    @Test
    public void emptyPositionNameShouldThrowException() {

        InvalidPositionNameException ex = assertThrows(InvalidPositionNameException.class, () -> {
            position.validatePositionName("");
        });

        assertEquals("The position name can not be empty!", ex.getMessage());
    }

    @Test
    public void locationNameLongerThan50CharsShouldThrowException() {

        //Test 52 chars
        InvalidLocationException ex = assertThrows(InvalidLocationException.class, () -> {
            position.validateLocationName("LocationLocationLocationLocationLocationLocationLoc");
        });

        assertEquals("The location name can't be longer than 50 characters!", ex.getMessage());
    }

    @Test
    public void emptyLocationShouldThrowException() {

        InvalidLocationException ex = assertThrows(InvalidLocationException.class, () -> {
            position.validateLocationName("");
        });

        assertEquals("The location name can not be empty!", ex.getMessage());

    }

    @Test
    public void validPositionNameShouldNotThrowException() {

        //Test 50 chars
        assertDoesNotThrow(() -> {
            position.validatePositionName("PositionNamePositionNamePositionNamePositionNameNa");
        });
        //Average case
        assertDoesNotThrow(() -> {
            position.validatePositionName("Software tester");
        });

    }

    @Test
    public void validLocationNameShouldNotThrowException() {

        //Test 50 chars
        assertDoesNotThrow(() -> {
            position.validateLocationName("LocationLocationLocationLocationLocationLocationLo");
        });

        //Average case
        assertDoesNotThrow(() -> {
            position.validateLocationName("New York");
        });

    }

}