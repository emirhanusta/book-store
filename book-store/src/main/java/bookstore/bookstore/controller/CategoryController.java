package bookstore.bookstore.controller;

import bookstore.bookstore.dto.request.SaveCategoryRequest;
import bookstore.bookstore.dto.request.UpdateCategoryRequest;
import bookstore.bookstore.model.Category;
import bookstore.bookstore.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
@Slf4j
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
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.getAllCategories());
    }
}
