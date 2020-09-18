package repositories;

import domain.Category;
import domain.Song;
import domain.Musician;
import domain.Album;
import utils.PostgresConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class SongRepository implements Repository<Song> {
    List<Song> songRepository = null;

    public List<Song> getAll() {
        List<Song> songList = new ArrayList<>();
        try {
            ResultSet resultSet = PostgresConnection
                    .getInstance()
                    .createStatement()
                    .executeQuery("SELECT songs.id, songs.title, songs.musician, musicians.name AS musician_name, " +
                            "       songs.album, albums.name AS album_name, albums.yearofrelease, " +
                            "       songcategory.id_song, songcategory.id_category, " +
                            "        categories.name AS category_name, categories.description " +
                            "FROM songs " +
                            "INNER JOIN songcategory ON songcategory.id_song = songs.id " +
                            "INNER JOIN categories ON songcategory.id_category = categories.id " +
                            "INNER JOIN musicians ON songs.musician = musicians.id " +
                            "INNER JOIN albums ON songs.album = albums.id");

            Map<Integer, Set<Category>> categoryMap = new HashMap<>();
            HashSet<Category> categorySet = new HashSet<>();
            int songID = 0;
            while (resultSet.next()) {
                boolean addSongToSongList = false;
                songID = resultSet.getInt("id");
                String title = resultSet.getString("title");
                Musician musician = new Musician("musician");
                try {
                    musician.set_id(resultSet.getInt("musician"));
                    musician.setName(resultSet.getString("musician_name"));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Musician not found by ID");
                }

                Album album = new Album("album", 1999);
                try {
                    album.set_id(resultSet.getInt("album"));
                    album.setName(resultSet.getString("album_name"));
                    album.setYearOfRelease(resultSet.getInt("yearofrelease"));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Album not found by ID");
                }

                Category category = new Category("category", "description");
                if (!categoryMap.containsKey(songID)) {
                    categorySet = new HashSet<>();
                    addSongToSongList = true;
                }
                try {
                    category.set_id(resultSet.getInt("id_category"));
                    category.setName(resultSet.getString("category_name"));
                    category.setDescription(resultSet.getString("description"));
                    categorySet.add(category);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Category not found by ID");
                }
                categoryMap.put(songID, categorySet);

                Song song = new Song(title, musician, album, categorySet);
                song.set_id(songID);
                if (addSongToSongList) {
                    songList.add(song);
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection failure" +
                    "SongRepository getAll method failed.");
            e.printStackTrace();
        }
        return songList;
    }

    public Song getByID(Integer id) throws Exception {
        try {
            ResultSet resultSet = PostgresConnection
                    .getInstance()
                    .createStatement()
                    .executeQuery("SELECT songs.id, songs.title, songs.musician, musicians.name AS musician_name, " +
                            "       songs.album, albums.name AS album_name, albums.yearofrelease, " +
                            "        songcategory.id_song, songcategory.id_category, " +
                            "        categories.name AS category_name, categories.description " +
                            "FROM songs " +
                            "INNER JOIN songcategory ON songcategory.id_song = songs.id " +
                            "INNER JOIN categories ON songcategory.id_category = categories.id " +
                            "INNER JOIN musicians ON songs.musician = musicians.id " +
                            "INNER JOIN albums ON songs.album = albums.id " +
                            "WHERE songs.id = " + id);
            int songID = 0;
            if (resultSet.next()) {
                songID = resultSet.getInt("id");
                String title = resultSet.getString("title");

                Musician musician = new Musician("name");
                musician.set_id(resultSet.getInt("musician"));
                musician.setName(resultSet.getString("musician_name"));

                Album album = new Album("name", 1999);
                album.set_id(resultSet.getInt("album"));
                album.setName(resultSet.getString("album_name"));
                album.setYearOfRelease(resultSet.getInt("yearofrelease"));

                Category category = new Category("category", "description");
                HashSet<Category> categories = new HashSet<>();

                category.set_id(resultSet.getInt("id_category"));
                category.setName(resultSet.getString("category_name"));
                category.setDescription(resultSet.getString("description"));
                categories.add(category);

                while (resultSet.next()) {
                    category = new Category("category", "description");
                    category.set_id(resultSet.getInt("id_category"));
                    category.setName(resultSet.getString("category_name"));
                    category.setDescription(resultSet.getString("description"));
                    categories.add(category);
                }
                Song song = new Song(title, musician, album, categories);
                song.set_id(songID);
                return song;
            }
        } catch (SQLException e) {
            System.out.println("Connection failure." +
                    "SongRepository getByID method failed.");
            e.printStackTrace();
        }
        throw new Exception("Song not found by ID");
    }

    public void add(Map<String, Object> args) {
        if (args.containsKey("title")
                && args.containsKey("musician")
                && args.containsKey("album")
                && args.containsKey("categories")) {
            String title = "'" + (String) args.get("title") + "'";

            Musician musician = (Musician) args.get("musician");
            int musicianID = musician.get_id();

            Album album = (Album) args.get("album");
            int albumID = album.get_id();

            HashSet<Category> categories = (HashSet<Category>) args.get("categories");

            try {
                PostgresConnection
                        .getInstance()
                        .createStatement()
                        .executeUpdate("INSERT INTO songs (title, musician, album, numberofplays, uniqueplays) " +
                                "VALUES (" + title + ", " + musicianID + ", " + albumID + ", 0, 0)");

                ResultSet resultSet = PostgresConnection
                        .getInstance()
                        .createStatement()
                        .executeQuery("SELECT MAX(id) AS songID FROM songs");

                resultSet.next();
                int songID = resultSet.getInt("songID");
                int categoryID = -1;
                for (Category category : categories) {
                    categoryID = category.get_id();

                    PostgresConnection
                            .getInstance()
                            .createStatement()
                            .executeUpdate("INSERT INTO songcategory (id_song, id_category) " +
                                    "VALUES (" + songID + ", " + categoryID + ")");
                }

            } catch (SQLException e) {
                System.out.println("Connection failure." +
                        "SongRepository add method failed.");
                e.printStackTrace();
            }
        }
    }

    public boolean modify(Integer id, Map<String, Object> args) {
        if (args.containsKey("title")
                && args.containsKey("musician")
                && args.containsKey("album")
                && args.containsKey("categories")) {
            String title = "'" + (String) args.get("title") + "'";

            Musician musician = (Musician) args.get("musician");
            int musicianID = musician.get_id();

            Album album = (Album) args.get("album");
            int albumID = album.get_id();

            HashSet<Category> categories = (HashSet<Category>) args.get("categories");

            try {
                PostgresConnection
                        .getInstance()
                        .createStatement()
                        .executeUpdate("UPDATE songs SET title = " + title
                                + ", musician = " + musicianID
                                + ", album = " + albumID + " WHERE id = " + id);

                PostgresConnection
                        .getInstance()
                        .createStatement()
                        .executeUpdate("DELETE FROM songcategory WHERE id_song = " + id);

                int categoryID = -1;
                for (Category category : categories) {
                    categoryID = category.get_id();

                    PostgresConnection
                            .getInstance()
                            .createStatement()
                            .executeUpdate("INSERT INTO songcategory (id_song, id_category) "
                                    + "VALUES (" + id + ", "  + categoryID + ")");
                }
                return true;
            } catch (SQLException e) {
                System.out.println("Connection failure." +
                        "SongRepository modify method failed.");
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean delete(Integer id) {
        try {
            PostgresConnection
                    .getInstance()
                    .createStatement()
                    .executeUpdate("DELETE FROM songcategory WHERE id_song = " + id);

            PostgresConnection
                    .getInstance()
                    .createStatement()
                    .executeUpdate("DELETE FROM songs WHERE id = " + id);

            return true;
        } catch (SQLException e) {
            System.out.println("Connection failure." +
                    "SongRepository delete method failed.");
            e.printStackTrace();
        }
        return false;
    }

}