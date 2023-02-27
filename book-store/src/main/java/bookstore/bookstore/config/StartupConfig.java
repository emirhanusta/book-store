package bookstore.bookstore.config;

import bookstore.bookstore.dto.request.SaveBookRequest;
import bookstore.bookstore.dto.request.SaveCategoryRequest;
import bookstore.bookstore.model.Role;
import bookstore.bookstore.model.User;
import bookstore.bookstore.service.BookService;
import bookstore.bookstore.service.CategoryService;
import bookstore.bookstore.service.UserService;
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
    public void run(String... args) throws Exception {
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
        bookService.saveBook(SaveBookRequest.builder()
                .title("The Lord of the Rings")
                .author("J.R.R. Tolkien")
                .pages(1216)
                .categoryId(1L)
                .description("The Lord of the Rings is an epic high fantasy novel written by English author and scholar J. R. R. Tolkien.")
                .build());
        bookService.saveBook(SaveBookRequest.builder()
                .title("Harry Potter and the Philosopher's Stone")
                .author("J.K. Rowling")
                .pages(223)
                .description("Harry Potter and the Philosopher's Stone is a fantasy novel written by British author J. K. Rowling.")
                .categoryId(1L)
                .build());

    }
}