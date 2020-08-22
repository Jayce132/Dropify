package domain;

import domain.Album;
import domain.Category;
import domain.Musician;
import domain.Song;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SongTest {

    @org.junit.jupiter.api.Test
    void getTitle() {
        Song tester = new Song("some song", new Musician("some musician"), new Album("some album", 2020));

        assertEquals("some song", tester.getTitle(), "Error");
    }

    @Test
    void getMusician() {
        Musician musician = new Musician("some musician");
        Song tester = new Song("some song", musician, new Album("some album", 2020));

        assertEquals(musician, tester.getMusician(), "Error");
    }

    @org.junit.jupiter.api.Test
    void getCategories() {
        HashSet<Category> categories = new HashSet<Category>();
        Category pop = new Category("pop", "popular");
        categories.add(pop);
        Song tester = new Song("some song", new Musician("some musician"), new Album("some album", 2020), categories);


        assertEquals(categories, tester.getCategories(), "Error");
    }

    @org.junit.jupiter.api.Test
    void getNumberOfPlays() {
        Song tester = new Song("some song", new Musician("some musician"), new Album("some album", 2020));
        tester.increaseNumberOfPlays();

        assertEquals(1, tester.getNumberOfPlays(), "Error");
        tester.increaseNumberOfPlays();
        tester.increaseNumberOfPlays();

        assertEquals(3, tester.getNumberOfPlays(), "Error");
    }

    @org.junit.jupiter.api.Test
    void getUniquePlays() {
        Song tester = new Song("some song", new Musician("some musician"), new Album("some album", 2020));

        assertEquals(0, tester.getUniquePlays(), "Error");
        tester.increaseUniquePlays();

        assertEquals(1, tester.getUniquePlays(), "Error");
    }

    @org.junit.jupiter.api.Test
    void getAlbum() {
        Album album = new Album("some album", 2020);
        Song tester = new Song("some song", new Musician("some musician"), album);

        assertEquals(album, tester.getAlbum(), "Error");
    }

    @org.junit.jupiter.api.Test
    void setTitle() {
        Song tester = new Song("some song", new Musician("some musician"), new Album("some album", 2020));
        tester.setTitle("some new song");

        assertEquals("some new song", tester.getTitle(), "Error");
    }

    @org.junit.jupiter.api.Test
    void setMusician() {
        Musician musician = new Musician("some new musician");
        Song tester = new Song("some song", new Musician("some musician"), new Album("some album", 2020));
        tester.setMusician(musician);

        assertEquals("some new musician", tester.getMusician(), "Error");
    }

    @org.junit.jupiter.api.Test
    void setCategories() {
        HashSet<Category> categories = new HashSet<Category>();
        Category pop = new Category("pop", "popular");
        categories.add(pop);
        Song tester = new Song("some song", new Musician("some musician"), new Album("some album", 2020), categories);

        HashSet<Category> secondCategories = categories;
        Category rock = new Category("rock", "guitar solo");
        secondCategories.add(rock);

        tester.setCategories(secondCategories);

        assertEquals(true, tester.getCategories().contains(rock), "Error");
    }

    @org.junit.jupiter.api.Test
    void setAlbum() {
        Song tester = new Song("some song", new Musician("some musician"), new Album("some album", 2020));
        Album secondAlbum = new Album("some new album", 2021);

        tester.setAlbum(secondAlbum);
        assertEquals(secondAlbum, tester.getAlbum(), "Error");
        assertEquals(2021, tester.getAlbum().getYearOfRelease(), "Error");
    }

    @org.junit.jupiter.api.Test
    void addCategory() {
        Song tester = new Song("some song", new Musician("some musician"), new Album("some album", 2020));
        Category pop = new Category("pop", "popular");

        tester.addCategory(pop);
        assertEquals(true, tester.getCategories().contains(pop), "Error");
    }

    @org.junit.jupiter.api.Test
    void increaseNumberOfPlays() {
        Song tester = new Song("some song", new Musician("some musician"), new Album("some album", 2020));

        tester.increaseUniquePlays();
        assertEquals(1, tester.getNumberOfPlays(), "Error");
    }

    @org.junit.jupiter.api.Test
    void increaseUniquePlays() {
        Song tester = new Song("some song", new Musician("some musician"), new Album("some album", 2020));

        tester.increaseUniquePlays();
        assertEquals(1, tester.getUniquePlays(), "Error");
        assertEquals(1, tester.getNumberOfPlays(), "Error");
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Category pop = new Category("pop", "popular");
        Song tester = new Song("some song", new Musician("some musician"), new Album("some album", 2020));
        tester.addCategory(pop);
        assertEquals("some song was made by some musician and the genre is pop: popular", tester.toString(), "Error");
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        Category pop = new Category("pop", "popular");
        Song tester = new Song("some song", new Musician("some musician"), new Album("some album", 2020));
        tester.addCategory(pop);

        Category rock = new Category("rock", "guitar solo");
        Song secondTester = new Song("some song", new Musician("some musician"), new Album("some album", 2020));
        secondTester.addCategory(rock);

        assertEquals(tester, tester, "Error");
        assertEquals(tester, secondTester, "Error");

        Song variable = tester;
        assertEquals(tester, variable, "Error");

        Song variable2 = tester;
        variable2.setTitle("alt titlu");

        assertEquals("alt titlu", tester.getTitle(), "Error");
        assertEquals(tester, variable2, "Error");

        Song variable3 = new Song("some other song", new Musician("some other musician"), new Album("some other album", 2021));
        assertFalse(tester.equals(variable3));
    }

    @org.junit.jupiter.api.Test
    void testHashCode() {
    }
}