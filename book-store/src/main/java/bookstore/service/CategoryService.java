package bookstore.service;

import bookstore.dto.request.SaveCategoryRequest;
import bookstore.dto.request.UpdateCategoryRequest;
import bookstore.dto.response.CategoryResponseDto;
import bookstore.exception.GeneralException;
import bookstore.model.Category;
import bookstore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponseDto saveCategory(SaveCategoryRequest category) {
        Category categoryToSave = Category.builder()
                .name(category.name())
                .build();
        categoryRepository.save(categoryToSave);
        return CategoryResponseDto.convertToCategoryResponse(categoryToSave);
    }

    public CategoryResponseDto updateCategory(UpdateCategoryRequest updateCategoryRequest) {
        Optional<Category> categoryToUpdate = categoryRepository.findById(updateCategoryRequest.id());
        if (categoryToUpdate.isPresent()) {
            categoryToUpdate.get().setName(updateCategoryRequest.name());
            categoryRepository.save(categoryToUpdate.get());
            return CategoryResponseDto.convertToCategoryResponse(categoryToUpdate.get());
        } else
            throw new GeneralException("Category with id: " + updateCategoryRequest.id() + " does not exist", HttpStatus.NOT_FOUND);
    }

    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream().
                map(CategoryResponseDto::convertToCategoryResponse).toList();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Category not found", HttpStatus.NOT_FOUND));

    }

    protected Category findByName(String name) {
        Optional<Category> category = categoryRepository.findByName(name);
        if (category.isPresent()) {
            return category.get();
        } else
            throw new GeneralException("Category not found", HttpStatus.NOT_FOUND);
    }

}
