package bookstore.service;

import bookstore.dto.response.BookResponseDto;
import bookstore.dto.request.SaveBookRequest;
import bookstore.dto.request.UpdateBookRequest;
import bookstore.exception.GeneralException;
import bookstore.model.Book;
import bookstore.model.Category;
import bookstore.model.Image;
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

        Category category = categoryService.findById(saveBookRequest.categoryId());

        Book book = Book.builder()
                .title(saveBookRequest.title())
                .author(saveBookRequest.author())
                .pages(saveBookRequest.pages())
                .description(saveBookRequest.description())
                .category(category)
                .build();
        bookRepository.save(book);

        return BookResponseDto.convertToBookResponse(book);
    }

    public BookResponseDto updateBook(UpdateBookRequest updateBookRequest) {

        Book book = bookRepository.findById(updateBookRequest.id())
                .orElseThrow(() -> new GeneralException("Book not found", HttpStatus.NOT_FOUND));
        Category category = categoryService.findById(updateBookRequest.categoryId());

        book.setCategory(category);
        book.setAuthor(updateBookRequest.author());
        book.setDescription(updateBookRequest.description());
        book.setPages(updateBookRequest.pages());
        book.setTitle(updateBookRequest.title());

        bookRepository.save(book);

        return BookResponseDto.convertToBookResponse(book);

    }

    public BookResponseDto getBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Book not found", HttpStatus.NOT_FOUND));

        return BookResponseDto.convertToBookResponse(book);
    }

    public void deleteBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Book not found", HttpStatus.NOT_FOUND));

        bookRepository.delete(book);
    }

    public void updateBookImage(Long bookId, Image image) {
        Book bookToUpdate = bookRepository.findById(bookId)
                .orElseThrow(() -> new GeneralException("Book not found", HttpStatus.NOT_FOUND));

        bookToUpdate.setImage(image);

        bookRepository.save(bookToUpdate);
    }
}
