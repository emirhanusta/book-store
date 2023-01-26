package bookstore.bookstore.service;

import bookstore.bookstore.dto.response.BookListResponseDto;
import bookstore.bookstore.dto.converter.BookDtoConverter;
import bookstore.bookstore.exception.NotFoundException;
import bookstore.bookstore.model.Book;
import bookstore.bookstore.model.BookStatus;
import bookstore.bookstore.model.User;
import bookstore.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<BookListResponseDto> getBookListByUserId(Long userId) {
        Optional<User> user = Optional.ofNullable(userService.findById(userId));

        if (user.isPresent()) {
            return user.get().getBooks()
                    .stream()
                    .map(BookDtoConverter::convertToBookListResponse)
                    .toList();
        } else {
            throw new NotFoundException("User not found");
        }
    }

    public List<BookListResponseDto> getBookListByUserIdWithBookStatus(Long userId, BookStatus bookStatus) {
        Optional<User> user = Optional.ofNullable(userService.findById(userId));

        if (user.isPresent()) {
            List<Book> books = user.get().getBooks()
                    .stream()
                    .filter(book -> book.getBookStatus().equals(bookStatus))
                    .toList();
            return books.stream()
                    .map(BookDtoConverter::convertToBookListResponse)
                    .toList();
        } else {
            throw new NotFoundException("User not found");
        }
    }

}
