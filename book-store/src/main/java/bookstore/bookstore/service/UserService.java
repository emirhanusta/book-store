package bookstore.bookstore.service;

import bookstore.bookstore.dto.converter.UserDtoConvertor;
import bookstore.bookstore.dto.response.UserResponseDto;
import bookstore.bookstore.exception.GeneralException;
import bookstore.bookstore.model.User;
import bookstore.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user) {

        return (User) userRepository.save(user);
    }

    public User findUserByUsername(String username)  {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new GeneralException("User not found", HttpStatus.NOT_FOUND);
        }
    }

    public UserResponseDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return UserDtoConvertor.convertToUserResponseDto(user.get());
        } else {
            throw new GeneralException("User not found", HttpStatus.NOT_FOUND);
        }
    }

    public Boolean existsByUsername(String username){

        return userRepository.findByUsername(username).isPresent();
    }
}