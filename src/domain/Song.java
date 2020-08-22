package domain;

import java.util.HashSet;
import java.util.Objects;

public class Song extends Entity {
    private String title;
    private Musician musician;
    private Album album;
    private HashSet<Category> categories;
    private Integer numberOfPlays;
    private Integer uniquePlays;

    public Song(String title, Musician musician, Album album) {
        this.title = title;
        this.musician = musician;
        this.album = album;
        this.categories = new HashSet<Category>();
        numberOfPlays = 0;
        uniquePlays = 0;
    }

    public Song(String title, Musician musician, Album album, HashSet<Category> categories) {
        this(title, musician, album);
        this.categories = categories;
        numberOfPlays = 0;
        uniquePlays = 0;

    }


    public String getTitle() {
        return title;
    }

    public Musician getMusician() {
        return musician;
    }

    public HashSet<Category> getCategories() {
        return categories;
    }

    public Integer getNumberOfPlays() {
        return numberOfPlays;
    }

    public Integer getUniquePlays() {
        return uniquePlays;
    }

    public Album getAlbum() {
        return album;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMusician(Musician musician) {
        this.musician = musician;
    }

    public void setCategories(HashSet<Category> categories) {
        this.categories = categories;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Boolean addCategory(Category category) {
        if (categories.contains(category)) {
            return false;
        } else {
            categories.add(category);
            return true;
        }
    }

    public void increaseNumberOfPlays() {
        numberOfPlays++;
    }

    public void increaseUniquePlays() {
        numberOfPlays++;
        uniquePlays++;
    }

    @Override
    public String toString() {
        String msg = title + " was made by " + musician + " and the genre is ";
        for (Category cat : categories) {
            msg = msg + cat.toString();
        }
        return msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(title, song.title) &&
                Objects.equals(musician, song.musician) &&
                Objects.equals(album, song.album);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, musician, album);
    }

    //    @Override
//    public boolean equals(Object o) {
//
//        if (o == this) return true;
//        if (!(o instanceof Song)) {
//            return false;
//        }
//
//        Song song = (Song) o;
//
//        return song.title.equals(title) &&
//                song.musician.equals(musician) &&
//                song.album.equals(album);
//    }
}
