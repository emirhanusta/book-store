package bookstore.bookstore.controller;

import bookstore.bookstore.dto.request.SaveCategoryRequest;
import bookstore.bookstore.dto.request.UpdateCategoryRequest;
import bookstore.bookstore.model.Category;
import bookstore.bookstore.service.CategoryService;
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
    public ResponseEntity<Category> saveCategory(@Valid @RequestBody SaveCategoryRequest category) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.saveCategory(category));
    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody UpdateCategoryRequest category) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.updateCategory(category));
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.getAllCategories());
    }
}
