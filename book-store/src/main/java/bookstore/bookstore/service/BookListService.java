package bookstore.bookstore.service;

import bookstore.bookstore.dto.response.BookListResponseDto;
import bookstore.bookstore.dto.converter.BookDtoConverter;
import bookstore.bookstore.model.Book;
import bookstore.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookListService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final UserService userService;


    public List<BookListResponseDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookDtoConverter::convertToBookListResponse)
                .toList();
    }

    public List<BookListResponseDto> getBooksByCategoryName(String categoryName) {
        List<Book> books = bookRepository.findAllByCategory(categoryService.findByName(categoryName));
        return books.stream()
                .map(BookDtoConverter::convertToBookListResponse)
                .toList();
    }

    public List<BookListResponseDto> getBookByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);

        return books.stream()
                .map(BookDtoConverter::convertToBookListResponse)
                .toList();
    }

}
