package bookstore.service;

import bookstore.dto.response.BookListResponseDto;
import bookstore.dto.converter.BookDtoConverter;
import bookstore.model.Book;
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
