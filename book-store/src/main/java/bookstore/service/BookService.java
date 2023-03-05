package bookstore.service;

import bookstore.dto.response.BookResponseDto;
import bookstore.dto.converter.BookDtoConverter;
import bookstore.dto.request.SaveBookRequest;
import bookstore.dto.request.UpdateBookRequest;
import bookstore.exception.GeneralException;
import bookstore.model.Book;
import bookstore.model.Category;
import bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


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

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Book not found", HttpStatus.NOT_FOUND));

        bookRepository.delete(book);
    }

}
