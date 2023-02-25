package bookstore.bookstore.config;

import bookstore.bookstore.model.Role;
import bookstore.bookstore.model.User;
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

    }
}