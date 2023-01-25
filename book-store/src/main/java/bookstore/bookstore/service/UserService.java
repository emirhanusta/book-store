package bookstore.bookstore.service;

import bookstore.bookstore.dto.converter.BookDtoConverter;
import bookstore.bookstore.dto.converter.UserDtoConvertor;
import bookstore.bookstore.dto.request.SaveUserRequest;
import bookstore.bookstore.dto.response.BookListResponseDto;
import bookstore.bookstore.dto.response.UserResponseDto;
import bookstore.bookstore.exception.NotFoundException;
import bookstore.bookstore.model.Book;
import bookstore.bookstore.model.BookStatus;
import bookstore.bookstore.model.User;
import bookstore.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BookService bookService;

    public UserResponseDto createUser(SaveUserRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        return UserDtoConvertor.convertToUserResponseDto(user);
    }

    public UserResponseDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return UserDtoConvertor.convertToUserResponseDto(user.get());
        } else {
            throw new NotFoundException("User not found");
        }
    }

    public List<BookListResponseDto> getBookListByUserId(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get().getBooks()
                    .stream()
                    .map(BookDtoConverter::convertToBookListResponse)
                    .toList();
        } else {
            throw new NotFoundException("User not found");
        }
    }

    public List<BookListResponseDto> getBookListByUserIdWithBookStatus(Long id, BookStatus bookStatus) {
        Optional<User> user = userRepository.findById(id);

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

    public List<BookListResponseDto> addBookToUserBoookList(Long id, Long bookId, BookStatus bookStatus) {
        Optional<User> user = userRepository.findById(id);
        Optional<Book> book = Optional.ofNullable(bookService.getOneBookById(bookId));

        if (user.isPresent()) {
            if (book.isPresent()) {
                List<Book> books = user.get().getBooks();
                books.add(book.get());
                book.get().setBookStatus(bookStatus);

                return books.stream()
                        .map(BookDtoConverter::convertToBookListResponse)
                        .toList();
            } else {
                throw new NotFoundException("Book not found");
            }
        } else {
            throw new NotFoundException("User not found");
        }
    }
}