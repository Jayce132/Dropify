package services;

import domain.Category;
import repositories.CategoryRepository;

import java.util.List;
import java.util.Map;

public class CategoryService {
    public static CategoryRepository categoryRepository = new CategoryRepository();

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Category getByID(Integer id) throws Exception {
        return categoryRepository.getByID(id);
    }

    public void add(Map<String, Object> args) {
        categoryRepository.add(args);
    }

    public boolean modify(Integer id, Map<String, Object> args) {
        return categoryRepository.modify(id, args);
    }

    public boolean delete(Integer id) {
        return categoryRepository.delete(id);
    }
}
