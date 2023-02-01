package bookstore.bookstore.service;

import bookstore.bookstore.dto.converter.UserDtoConvertor;
import bookstore.bookstore.dto.request.SaveUserRequest;
import bookstore.bookstore.dto.response.UserResponseDto;
import bookstore.bookstore.exception.NotFoundException;
import bookstore.bookstore.model.Role;
import bookstore.bookstore.model.User;
import bookstore.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto saveUser(SaveUserRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .role(Role.valueOf(request.getRole()))
                .build();
        userRepository.save(user);
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

}