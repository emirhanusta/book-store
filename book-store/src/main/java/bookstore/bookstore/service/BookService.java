package bookstore.bookstore.service;

import bookstore.bookstore.dto.response.BookResponseDto;
import bookstore.bookstore.dto.converter.BookDtoConverter;
import bookstore.bookstore.dto.request.SaveBookRequest;
import bookstore.bookstore.dto.request.UpdateBookRequest;
import bookstore.bookstore.exception.GeneralException;
import bookstore.bookstore.model.Book;
import bookstore.bookstore.model.Category;
import bookstore.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    public BookResponseDto saveBook(SaveBookRequest saveBookRequest) {

        Category category = categoryService.findById(saveBookRequest.getCategoryId());

        Book book = Book.builder()
                .title(saveBookRequest.getTitle())
                .author(saveBookRequest.getAuthor())
                .pages(saveBookRequest.getPages())
                .description(saveBookRequest.getDescription())
                .category(category)
                .build();
        bookRepository.save(book);

        return BookDtoConverter.convertToBookResponse(book);
    }

    public BookResponseDto updateBook(UpdateBookRequest updateBookRequest) {

        Book book = bookRepository.findById(updateBookRequest.getId())
                .orElseThrow(() -> new GeneralException("Book not found", HttpStatus.NOT_FOUND));
        Category category = categoryService.findById(updateBookRequest.getCategoryId());

        book.setCategory(category);
        book.setAuthor(updateBookRequest.getAuthor());
        book.setDescription(updateBookRequest.getDescription());
        book.setPages(updateBookRequest.getPages());
        book.setTitle(updateBookRequest.getTitle());

        bookRepository.save(book);

        return BookDtoConverter.convertToBookResponse(book);

    }

    public BookResponseDto getBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Book not found", HttpStatus.NOT_FOUND));

        return BookDtoConverter.convertToBookResponse(book);
    }

    public void deleteBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
        } else
            throw new GeneralException("Book not found", HttpStatus.NOT_FOUND);
    }

}
