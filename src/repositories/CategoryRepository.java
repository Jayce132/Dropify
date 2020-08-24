package repositories;

import domain.Album;
import domain.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryRepository implements Repository<Category> {
    private final List<Category> categoryRepository = new ArrayList<>();
    private static Integer ID = 0;

    public List<Category> getAll() {
        return categoryRepository;
    }

    public Category getByID(Integer id) throws Exception {
        for (Category category : categoryRepository) {
            if (category.get_id().equals(id)) {
                return category;
            }
        }
        throw new Exception("Category not found by ID");
    }

    public void add(Map<String, Object> args) {
        if (args.containsKey("name") && args.containsKey("description")) {
            Category category = new Category((String) args.get("name"), (String) args.get("description"));
            ID++;
            category.set_id(ID);
            categoryRepository.add(category);
        }
    }

    public boolean modify(Integer id, Map<String, Object> args) {
        for (Category category : categoryRepository) {
            if (category.get_id().equals(id)) {
                if (args.containsKey("name") && args.containsKey("description")) {
                    category.setName((String) args.get("name"));
                    category.setDescription((String) args.get("description"));
                    return true;
                }
            }
        }
        return false;
    }

    public boolean delete(Integer id) {
        for (Category category : categoryRepository) {
            if (category.get_id().equals(id)) {
                categoryRepository.remove(category);
                return true;
            }
        }
        return false;
    }
}
