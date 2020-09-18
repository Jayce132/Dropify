package repositories;

import domain.Category;
import utils.PostgresConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryRepository implements Repository<Category> {
    public List<Category> getAll() {
        List<Category> categoryList = new ArrayList<>();
        try {
            ResultSet resultSet = PostgresConnection
                    .getInstance()
                    .createStatement()
                    .executeQuery("SELECT * FROM categories");
            while (resultSet.next()) {
                Category category = new Category("category", "description");
                category.set_id(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        return categoryList;
    }

    public Category getByID(Integer id) throws Exception {
        try {
            ResultSet resultSet = PostgresConnection
                    .getInstance()
                    .createStatement()
                    .executeQuery("SELECT * FROM categories WHERE id = " + id + " LIMIT 1");
            if (resultSet.next()) {
                Category category = new Category("name", "description");
                category.set_id(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                return category;
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        throw new Exception("Category not found by ID");
    }

    public void add(Map<String, Object> args) {
        if (args.containsKey("name") && args.containsKey("description")) {
            try {
                String name = "'" + (String) args.get("name") + "'";
                String description = "'" + (String) args.get("description") + "'";
                PostgresConnection
                        .getInstance()
                        .createStatement()
                        .executeUpdate("INSERT INTO categories (name, description) "
                                + "VALUES(" + name + ", " + description + ")");
            } catch (SQLException e) {
                System.out.println("Connection failure.");
                e.printStackTrace();
            }
        }
    }

    public boolean modify(Integer id, Map<String, Object> args) {
        if (args.containsKey("name") && args.containsKey("description")) {
            try {
                String name = "'" + (String) args.get("name") + "'";
                String description = "'" + (String) args.get("description") + "'";
                PostgresConnection
                        .getInstance()
                        .createStatement()
                        .executeUpdate("UPDATE categories SET name = " + name
                                + ",description = " + description + " WHERE id = " + id);
                return true;
            } catch (SQLException e) {
                System.out.println("Connection failure");
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
                    .executeQuery("SELECT id_song FROM songcategory WHERE id_category = " + id);
            while(resultSet.next()) {
                id_song = resultSet.getInt("id_song");
                PostgresConnection
                        .getInstance()
                        .createStatement()
                        .executeUpdate("DELETE FROM songcategory WHERE id_song = " + id_song);
                PostgresConnection
                        .getInstance()
                        .createStatement()
                        .executeUpdate("DELETE FROM songs WHERE id = " + id_song);
            }

            PostgresConnection
                    .getInstance()
                    .createStatement()
                    .executeUpdate("DELETE FROM categories WHERE id = " + id);
            return true;
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        return false;
    }
}
