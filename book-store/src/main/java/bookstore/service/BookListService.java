package bookstore.service;

import bookstore.dto.response.BookListResponseDto;
import bookstore.model.Book;
import bookstore.model.Category;
import bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookListService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;


    public List<BookListResponseDto> getAllBooks(int page,int size ) {
        List<Book> books = bookRepository.findAll(PageRequest.of(page, size)).toList();
        return books.stream()
                .map(BookListResponseDto::convertToBookListResponse)
                .toList();
    }

    public List<BookListResponseDto> getBooksByCategoryName(String categoryName) {
        Category category = categoryService.findByName(categoryName);
        List<Book> books = bookRepository.findAllByCategory(category);
        return books.stream()
                .map(BookListResponseDto::convertToBookListResponse)
                .toList();
    }

    public List<BookListResponseDto> getAllBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);

        return books.stream()
                .map(BookListResponseDto::convertToBookListResponse)
                .toList();
    }

}
