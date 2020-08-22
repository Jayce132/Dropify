package utils;

import domain.Category;
import org.junit.jupiter.api.Test;
import utils.Delimiter;
import utils.ModelParser;

import java.util.ArrayList;

    /*  Song("@10@"),
        Musician("@20@"),
        Album("@30@"),
        Category("@40@");
    */

class ModelParserTest {
    @Test
    void verifyBestCaseParseSong() {
        String args = "Title" + Delimiter.Song.getDelim()
                + "Musician" + Delimiter.Song.getDelim()
                + "Album" + Delimiter.Album.getDelim() + "2020" + Delimiter.Song.getDelim()
                + "Category" + Delimiter.Category.getDelim() + "description";

        try {
            String categoryName = "";
            String categoryDescription = "";
            for (Category category : ModelParser.parseSong(args).getCategories()) {
                categoryName = category.getName();
                categoryDescription = category.getDescription();
            }
            assert ModelParser.parseSong(args).getTitle().equals("Title")
                    && ModelParser.parseSong(args).getMusician().getName().equals("Musician")
                    && ModelParser.parseSong(args).getAlbum().getName().equals("Album")
                    && ModelParser.parseSong(args).getAlbum().getYearOfRelease().equals(2020)
                    && categoryName.equals("Category")
                    && categoryDescription.equals("description");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void verifyMultipleCategoriesParseSong() {
        String args = "Title" + Delimiter.Song.getDelim()
                + "Musician" + Delimiter.Song.getDelim()
                + "Album" + Delimiter.Album.getDelim() + "2020" + Delimiter.Song.getDelim()
                + "Category" + Delimiter.Category.getDelim() + "description" + Delimiter.Song.getDelim()
                + "secondCategory" + Delimiter.Category.getDelim() + "second description" + Delimiter.Song.getDelim()
                + "thirdCategory" + Delimiter.Category.getDelim() + "third description";

        try {
            ArrayList<Category> categories = new ArrayList<>();
            String nameAndDescription = "";

            categories.addAll(ModelParser.parseSong(args).getCategories());

            String categoriesChecks = "Category description\n"
                    + "secondCategory second description\n"
                    + "thirdCategory third description";
            for (Category category : categories) {
                nameAndDescription = category.getName() + " " + category.getDescription();
                assert categoriesChecks.contains(nameAndDescription);
            }
            assert ModelParser.parseSong(args).getTitle().equals("Title")
                    && ModelParser.parseSong(args).getMusician().getName().equals("Musician")
                    && ModelParser.parseSong(args).getAlbum().getName().equals("Album")
                    && ModelParser.parseSong(args).getAlbum().getYearOfRelease().equals(2020);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void verifyBestCaseParseMusician() {
        String args = "Musician";

        try {
            assert ModelParser.parseMusician(args).getName().equals("Musician");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @Test
    void verifyExceptionCasesParseMusician() {
        String args = "Musician" + Delimiter.Musician.getDelim() + "secondMusician";

        try {
            assert (ModelParser.parseMusician(args).getName().equals("Musician"));
        } catch (Exception e) {
            System.out.println("Too many arguments");
        }
    }

    @Test
    void verifyBestCaseParseCategory() {
        String args = "Pop" + Delimiter.Category.getDelim() + "popular";
        String del = "\\|";

        try {
            assert ModelParser.parseCategory(args).getName().equals("Pop") && ModelParser.parseCategory(args).getDescription().equals("popular");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @Test
    void verifyBestCaseParseAlbum() {
        String args = "Album" + Delimiter.Album.getDelim() + "2020";

        try {
            assert ModelParser.parseAlbum(args).getName().equals("Album") && ModelParser.parseAlbum(args).getYearOfRelease().equals(2020);
        } catch (Exception e) {
            System.out.println("Error");
            assert false;
        }
    }

    @Test
    void tooFewArgsParseAlbum() {
        String args = "Album";

        try {
            assert ModelParser.parseAlbum(args).getName() == "" || ModelParser.parseAlbum(args).getYearOfRelease() == null;
        } catch (Exception e) {
            System.out.println("Too few arguments");
            assert false;
        }
    }
}