package domain;

import domain.Album;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlbumTest {

    @Test
    void getName() {
        Album tester = new Album ("some album", 2020);

        assertEquals("some album", tester.getName(), "Error");
    }

    @Test
    void getYearOfRelease() {
        Album tester = new Album ("some album", 2020);

        assertEquals(2020, tester.getYearOfRelease(), "Error");
    }

    @Test
    void setName() {
        Album tester = new Album ("some album", 2020);

        tester.setName("some other album");

        assertEquals("some other album", tester.getName(), "Error");
    }

    @Test
    void setYearOfRelease() {
        Album tester = new Album ("some album", 2020);

        tester.setYearOfRelease(2021);

        assertEquals(2021, tester.getYearOfRelease(), "Error");
    }

    @Test
    void testToString() {
        Album tester = new Album ("some album", 2020);

        assertEquals("some album was released in 2020", tester.toString(), "Error");
    }

    @Test
    void testEquals() {
        Album tester = new Album ("some album", 2020);
        Album secondTester = new Album ("some album", 2020);
        Album variable = tester;
        Album variable2 = tester;

        assertEquals(tester, tester, "Error");
        assertEquals(tester, secondTester, "Error");
        assertEquals(tester, variable, "Error");

        variable2.setName("some other album");

        assertEquals("some other album", tester.getName());
        assertEquals(tester, variable2, "Error");
    }

    @Test
    void testHashCode() {
    }
}