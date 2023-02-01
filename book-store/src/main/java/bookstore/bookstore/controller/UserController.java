package bookstore.bookstore.controller;

import bookstore.bookstore.dto.request.SaveUserRequest;
import bookstore.bookstore.dto.response.UserResponseDto;
import bookstore.bookstore.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto>  saveUser(@Valid @RequestBody SaveUserRequest saveUserRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.saveUser(saveUserRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getUserById(id));
    }

}
