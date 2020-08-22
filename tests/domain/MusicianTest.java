package domain;

import domain.Musician;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicianTest {

    @Test
    void getName() {
        Musician tester = new Musician("some musician");

        assertEquals("some musician", tester.getName(), "Error");
    }

    @Test
    void setName() {
        Musician tester = new Musician("some musician");

        tester.setName("some other musician");

        assertEquals("some other musician", tester.getName(), "Error");
    }

    @Test
    void testToString() {
        Musician tester = new Musician("some musician");

        assertEquals("some musician", tester.toString(), "Error");
    }

    @Test
    void testEquals() {
        Musician tester = new Musician("some musician");
        Musician secondTester = new Musician("some musician");
        Musician variable = tester;
        Musician variable2 = tester;

        assertEquals(tester, tester, "Error");
        assertEquals(tester, secondTester, "Error");
        assertEquals(tester, variable, "Error");

        variable2.setName("some other musician");
        assertEquals(tester, variable2, "Error");
    }

    @Test
    void testHashCode() {
    }
}