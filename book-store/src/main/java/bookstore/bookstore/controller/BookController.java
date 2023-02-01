package bookstore.bookstore.controller;

import bookstore.bookstore.dto.request.SaveBookRequest;
import bookstore.bookstore.dto.request.UpdateBookRequest;
import bookstore.bookstore.dto.response.BookListResponseDto;
import bookstore.bookstore.dto.response.BookResponseDto;
import bookstore.bookstore.service.BookListService;
import bookstore.bookstore.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookListService bookListService;

    @PostMapping
    public ResponseEntity<BookResponseDto> saveBook(@Valid @RequestBody SaveBookRequest saveBookRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.saveBook(saveBookRequest));
    }

    @PutMapping
    public ResponseEntity<BookResponseDto> updateBook(@Valid @RequestBody UpdateBookRequest updateBookRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.updateBook(updateBookRequest));
    }

    @GetMapping
    public ResponseEntity<List<BookListResponseDto>> getAllBooks(@RequestParam(name = "page", value = "1") int page,
                                                                 @RequestParam(name = "size", value = "5") int size)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookListService.getAllBooks(page,size));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.getBookById(id));
    }

    @GetMapping("/getByTitle/{title}")
    public ResponseEntity<List<BookListResponseDto>> getBookByTitle(@PathVariable String title) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookListService.getBookByTitle(title));
    }

    @GetMapping("/getByCategoryName/{categoryName}")
    public ResponseEntity<List<BookListResponseDto>> getBooksByCategoryName(@PathVariable String categoryName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookListService.getBooksByCategoryName(categoryName));
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

}
