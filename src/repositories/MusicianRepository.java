package repositories;

import domain.Musician;
import utils.PostgresConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MusicianRepository implements Repository<Musician> {
    public List<Musician> getAll() {
        List<Musician> musicianList = new ArrayList<>();
        try {
            ResultSet resultSet = PostgresConnection
                    .getInstance()
                    .createStatement()
                    .executeQuery("SELECT * FROM musicians");
            while (resultSet.next()) {
                Musician musician = new Musician("musician");
                musician.set_id(resultSet.getInt("id"));
                musician.setName(resultSet.getString("name"));
                musicianList.add(musician);
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        return musicianList;
    }

    public Musician getByID(Integer id) throws Exception {
        try {
            ResultSet resultSet = PostgresConnection
                    .getInstance()
                    .createStatement()
                    .executeQuery("SELECT * FROM musicians WHERE id = " + id + " LIMIT 1");
            if (resultSet.next()) {
                Musician musician = new Musician("musician");
                musician.set_id(resultSet.getInt("id"));
                musician.setName(resultSet.getString("name"));
                return musician;
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        throw new Exception("Musician not found by ID");
    }

    public void add(Map<String, Object> args) {
        if (args.containsKey("name")) {
            try {
                String name = "'" + (String) args.get("name") + "'";
                PostgresConnection
                        .getInstance()
                        .createStatement()
                        .executeUpdate("INSERT INTO musicians (name) "
                                + "VALUES(" + name + ")");
            } catch (SQLException e) {
                System.out.println("Connection failure.");
                e.printStackTrace();
            }
        }
    }
    //Use
    //executeUpdate
    //instead of
    //executeQuery
    //if no data will be returned (i.e. a non-SELECT operation).

    public boolean modify(Integer id, Map<String, Object> args) {
        if (args.containsKey("name")) {
            try {
                String name = "'" + (String) args.get("name") + "'";
                PostgresConnection
                        .getInstance()
                        .createStatement()
                        .executeUpdate("UPDATE musicians SET name = " + name + "WHERE id = " + id);
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
                    .executeQuery("SELECT id FROM songs WHERE musician = " + id);
            while(resultSet.next()) {
                id_song = resultSet.getInt("id");
                PostgresConnection
                        .getInstance()
                        .createStatement()
                        .executeUpdate("DELETE FROM songcategory WHERE id_song = " + id_song);
            }

            PostgresConnection
                    .getInstance()
                    .createStatement()
                    .executeUpdate("DELETE FROM songs WHERE musician = " + id);

            PostgresConnection
                    .getInstance()
                    .createStatement()
                    .executeUpdate("DELETE FROM musicians WHERE id = " + id);
            return true;
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        return false;
    }
}