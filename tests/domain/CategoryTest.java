package domain;

import domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void getName() {
        Category tester = new Category("pop", "popular");

        assertEquals("pop", tester.getName(), "Error");
    }

    @Test
    void getDescription() {
        Category tester = new Category("pop", "popular");

        assertEquals("popular", tester.getDescription(), "Error");
    }

    @Test
    void setName() {
        Category tester = new Category("pop", "popular");

        tester.setName("rock");

        assertEquals("rock", tester.getName(), "Error");
    }

    @Test
    void setDescription() {
        Category tester = new Category("pop", "popular");

        tester.setDescription("guitar solo");

        assertEquals("guitar solo", tester.getDescription(), "Error");
    }

    @Test
    void testToString() {
        Category tester = new Category("pop", "popular");

        assertEquals("pop: popular", tester.toString(), "Error");
    }

    @Test
    void testEquals() {
        Category tester = new Category("pop", "popular");
        Category secondTester = new Category("pop", "popular");

        Category variable = tester;

        assertEquals(tester, tester, "Error");
        assertEquals(tester, secondTester, "Error");
        assertEquals(tester, variable, "Error");

        variable.setName("rock");
        assertEquals(tester, variable, "Error");
    }

    @Test
    void testHashCode() {
    }
}