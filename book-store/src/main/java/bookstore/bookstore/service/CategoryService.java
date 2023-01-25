package bookstore.bookstore.service;

import bookstore.bookstore.dto.request.SaveCategoryRequest;
import bookstore.bookstore.dto.request.UpdateCategoryRequest;
import bookstore.bookstore.exception.NotFoundException;
import bookstore.bookstore.model.Category;
import bookstore.bookstore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category saveCategory(SaveCategoryRequest category) {
        Category categoryToSave = Category.builder()
                .name(category.getName())
                .build();
        categoryRepository.save(categoryToSave);
        return categoryToSave;
    }

    public Category updateCategory(UpdateCategoryRequest category) {
        Optional<Category> categoryToUpdate = categoryRepository.findById(category.getId());
        if (categoryToUpdate.isPresent()) {
            categoryToUpdate.get().setName(category.getName());
            categoryRepository.save(categoryToUpdate.get());
            return categoryToUpdate.get();
        } else
            throw new NotFoundException("Category not found");
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public Category findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return category.get();
        } else
            throw new NotFoundException("Category not found");
    }

    public Category findByName(String name) {
        Optional<Category> category = categoryRepository.findByName(name);
        if (category.isPresent()) {
            return category.get();
        } else
            throw new NotFoundException("Category not found");
    }
}