package repositories;

import domain.Album;
import utils.PostgresConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AlbumRepository implements Repository<Album> {
    public List<Album> getAll() {
        List<Album> albumList = new ArrayList<>();
        try {
            ResultSet resultSet = PostgresConnection
                    .getInstance()
                    .createStatement()
                    .executeQuery("SELECT * FROM albums");
            while (resultSet.next()) {
                Album album = new Album("album", 1999);
                album.set_id(resultSet.getInt("id"));
                album.setName(resultSet.getString("name"));
                album.setYearOfRelease(resultSet.getInt("yearofrelease"));
                albumList.add(album);
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        return albumList;
    }

    public Album getByID(Integer id) throws Exception {
        try {
            ResultSet resultSet = PostgresConnection
                    .getInstance()
                    .createStatement()
                    .executeQuery("SELECT * FROM albums WHERE id = " + id + " LIMIT 1");
            if (resultSet.next()) {
                Album album = new Album("name", 1999);
                album.set_id(resultSet.getInt("id"));
                album.setName(resultSet.getString("name"));
                album.setYearOfRelease(resultSet.getInt("yearofrelease"));
                return album;
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        throw new Exception("Album not found by ID");
    }

    public void add(Map<String, Object> args) {
        if (args.containsKey("name") && args.containsKey("yearOfRelease")) {
            try {
                String name = "'" + (String) args.get("name") + "'";
                int yearOfRelease = (int) args.get("yearOfRelease");
                PostgresConnection
                        .getInstance()
                        .createStatement()
                        .executeUpdate("INSERT INTO albums (name, yearofrelease) "
                                + "VALUES(" + name + ", " + yearOfRelease + ")");
            } catch (SQLException e) {
                System.out.println("Connection failure.");
                e.printStackTrace();
            }
        }
    }

    public boolean modify(Integer id, Map<String, Object> args) {
        if (args.containsKey("name") && args.containsKey("yearOfRelease")) {
            try {
                String name = "'" + (String) args.get("name") + "'";
                int yearOfRelease = (int) args.get("yearOfRelease");
                PostgresConnection
                        .getInstance()
                        .createStatement()
                        .executeUpdate("UPDATE albums SET name = " + name
                                + ", yearofrelease = " + yearOfRelease + " WHERE id = " + id);
                return true;
            } catch (SQLException e) {
                System.out.println("Connection failure.");
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean delete(Integer id) {
        try {
            int id_song;
            ResultSet resultSet = PostgresConnection
                    .getInstance()
                    .createStatement()
                    .executeQuery("SELECT id FROM songs WHERE album = " + id);
            while (resultSet.next()) {
                id_song = resultSet.getInt("id");
                PostgresConnection
                        .getInstance()
                        .createStatement()
                        .executeUpdate("DELETE FROM songcategory WHERE id_song = " + id_song);
            }
            PostgresConnection
                    .getInstance()
                    .createStatement()
                    .executeUpdate("DELETE FROM songs WHERE album = " + id);
            PostgresConnection
                    .getInstance()
                    .createStatement()
                    .executeUpdate("DELETE FROM albums WHERE id = " + id);
            return true;
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        return false;
    }
}