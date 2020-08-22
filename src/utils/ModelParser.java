package utils;

import domain.Musician;
import domain.Song;
import domain.Category;
import domain.Album;

import java.util.HashSet;

public class ModelParser {

    private ModelParser() {

    }

    public static Musician parseMusician(String arguments) throws Exception {
        String[] musicianArray = arguments.split(Delimiter.Musician.getDelim());
        if (musicianArray.length == 1) {
            Musician musician = new Musician(arguments);
            return musician;
        } else throw new Exception("Invalid number of arguments");
    }

    public static Song parseSong(String arguments) throws Exception {
        String[] songAttributes = arguments.split(Delimiter.Song.getDelim());
        HashSet<Category> categories = new HashSet<>();

        if (songAttributes.length < 4) {
            throw new Exception("Not enough arguments");
        } else if (songAttributes.length <= 6) {
            for (int i = 3; i < songAttributes.length; i++) {
                categories.add(parseCategory(songAttributes[i]));
            }
            Song song = new Song(songAttributes[0],
                    parseMusician(songAttributes[1]),
                    parseAlbum(songAttributes[2]),
                    categories);
            return song;
        } else {
            throw new Exception("Invalid number of arguments");
        }
    }

    public static Category parseCategory(String arguments) throws Exception {

        String[] nameAndDescription = arguments.split(Delimiter.Category.getDelim());
        if (nameAndDescription.length == 2) {
            Category category = new Category(nameAndDescription[0], nameAndDescription[1]);
            return category;
        } else {
            throw new Exception("Invalid number of arguments");
        }
    }

    public static Album parseAlbum(String arguments) throws Exception {
        String[] albumAndYear = arguments.split(Delimiter.Album.getDelim());
        if (albumAndYear.length == 2) {
            Album album = new Album(albumAndYear[0], Integer.valueOf(albumAndYear[1]));
            return album;
        } else {
            throw new Exception("Invalid number of arguments");
        }
    }
}
