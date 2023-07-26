package bookstore.config;

import bookstore.dto.request.SaveBookRequest;
import bookstore.dto.request.SaveCategoryRequest;
import bookstore.model.Role;
import bookstore.model.User;
import bookstore.service.BookService;
import bookstore.service.CategoryService;
import bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupConfig implements CommandLineRunner {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final BookService bookService;
    private final CategoryService categoryService;

    @Override
    public void run(String... args) {
        userService.saveUser(User.builder()
                .username("emirhan")
                .password(bCryptPasswordEncoder.encode("pass"))
                .email("emirhan@gmail.com")
                .role(Role.ADMIN)
                .build()
        );
        userService.saveUser(User.builder()
                .username("usta")
                .password(bCryptPasswordEncoder.encode("pass"))
                .email("usta@gmail.com")
                .role(Role.USER)
                .build()
        );
        categoryService.saveCategory(new SaveCategoryRequest(
                "Novel"
        ));
        bookService.saveBook(
                new SaveBookRequest(
                        "The Lord of the Rings",
                        "J.R.R. Tolkien",
                        1216,
                        "The Lord of the Rings is an epic high fantasy novel written by English author and scholar J. R. R. Tolkien.",
                        1L

                )
        );
        bookService.saveBook(
                new SaveBookRequest(
                        "Harry Potter and the Philosopher's Stone",
                        "J.K. Rowling",
                        223,
                        "Harry Potter and the Philosopher's Stone is a fantasy novel written by British author J. K. Rowling",
                        1L

                )
        );
    }
}