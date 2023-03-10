package bookstore.controller;

import bookstore.dto.response.UserResponseDto;
import bookstore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserById() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authService.getAuthenticatedUser());
    }

}
