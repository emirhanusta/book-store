package bookstore.bookstore.service;

import bookstore.bookstore.dto.response.BookResponseDto;
import bookstore.bookstore.dto.converter.BookDtoConverter;
import bookstore.bookstore.dto.request.SaveBookRequest;
import bookstore.bookstore.dto.request.UpdateBookRequest;
import bookstore.bookstore.exception.NotFoundException;
import bookstore.bookstore.model.Book;
import bookstore.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    public BookResponseDto saveBook(SaveBookRequest saveBookRequest) {
        Book book = Book.builder()
                .title(saveBookRequest.getTitle())
                .author(saveBookRequest.getAuthor())
                .pages(saveBookRequest.getPages())
                .description(saveBookRequest.getDescription())
                .category(categoryService.findById(saveBookRequest.getCategoryId()))
                .build();
        bookRepository.save(book);
        return BookDtoConverter.convertToBookResponse(book);
    }

    public BookResponseDto updateBook(UpdateBookRequest updateBookRequest) {

        Optional<Book> book = bookRepository.findById(updateBookRequest.getId());

        if (book.isPresent()) {
            book.get().setCategory(categoryService.findById(updateBookRequest.getCategoryId()));
            book.get().setAuthor(updateBookRequest.getAuthor());
            book.get().setDescription(updateBookRequest.getDescription());
            book.get().setPages(updateBookRequest.getPages());
            book.get().setTitle(updateBookRequest.getTitle());
            bookRepository.save(book.get());
            return BookDtoConverter.convertToBookResponse(book.get());
        } else {
            throw new NotFoundException("Book not found");
        }
    }

    public BookResponseDto getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return BookDtoConverter.convertToBookResponse(book.get());
        } else
            throw new NotFoundException("Book not found");
    }

    public void deleteBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
        } else
            throw new NotFoundException("Book not found");
    }

}
