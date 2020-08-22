package repositories;

import domain.Category;

import java.util.HashMap;
import java.util.Map;

public class CategoryRepository implements Repository<Category> {
    private final HashMap<Integer, Category> categoryRepository = new HashMap<>();
    private static Integer ID = 0;

    public Map<Integer, Category> getAll() {
        return categoryRepository;
    }

    public Category getByID(Integer id) throws Exception {
        if (categoryRepository.containsKey(id)) {
            return categoryRepository.get(id);
        }
        throw new Exception("Category not found by ID");
    }

    public void add(Map<String, Object> args) {
        if (args.containsKey("name") && args.containsKey("description")) {
            Category category = new Category((String) args.get("name"), (String) args.get("description"));
            ID++;
            categoryRepository.put(ID, category);
        }
    }

    public boolean modify(Integer id, Map<String, Object> args) {
        if (categoryRepository.containsKey(id)) {
            Category category = categoryRepository.get(id);
            if (args.containsKey("name") && args.containsKey("description")) {
                category.setName((String) args.get("name"));
                category.setDescription((String) args.get("description"));
                return true;
            }
        }
        return false;
    }

    public boolean delete(Integer id) {
        if (categoryRepository.containsKey(id)) {
            categoryRepository.remove(id);
            return true;
        }
        return false;
    }
}
