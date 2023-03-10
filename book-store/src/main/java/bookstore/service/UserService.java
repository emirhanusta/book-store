package bookstore.service;

import bookstore.exception.GeneralException;
import bookstore.model.User;
import bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
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


    public Boolean existsByUsername(String username){

        return userRepository.findByUsername(username).isPresent();
    }
}