package bookstore.controller;

import bookstore.dto.request.SaveCategoryRequest;
import bookstore.dto.request.UpdateCategoryRequest;
import bookstore.dto.response.CategoryResponseDto;
import bookstore.model.Category;
import bookstore.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> saveCategory(@Valid @RequestBody SaveCategoryRequest category) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.saveCategory(category));
    }

    @PutMapping
    public ResponseEntity<CategoryResponseDto> updateCategory(@RequestBody UpdateCategoryRequest category) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.updateCategory(category));
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.getAllCategories());
    }
}
