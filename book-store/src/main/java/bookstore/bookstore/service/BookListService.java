package bookstore.bookstore.service;

import bookstore.bookstore.dto.response.BookListResponseDto;
import bookstore.bookstore.dto.converter.BookDtoConverter;
import bookstore.bookstore.dto.response.BookResponseDto;
import bookstore.bookstore.exception.NotFoundException;
import bookstore.bookstore.model.Book;
import bookstore.bookstore.model.BookStatus;
import bookstore.bookstore.model.Category;
import bookstore.bookstore.model.User;
import bookstore.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookListService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    public List<BookListResponseDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookDtoConverter::convertToBookListResponse)
                .toList();
    }

    public List<BookListResponseDto> getBooksByCategory(Category category) {
        List<Book> books = bookRepository.findAllByCategory(categoryService.findByName(category.getName()));
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
