package bookstore.service;

import bookstore.exception.GeneralException;
import bookstore.model.User;
import bookstore.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void ShouldSaveAndReturnUser_whenGivenUser() {
        // Given
        User user = User.builder()
                .username("username")
                .password("password")
                .email("email")
                .build();

        // When

        when(userRepository.save(any(User.class))).thenReturn(user);

        // Then
        User savedUser = userService.saveUser(user);
        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void ShouldFindUserByUsername_whenGivenUsernameExist() {
        // given - precondition or setup
        String username = "username";

        User expected = User.builder()
                .username(username)
                .password("password")
                .build();

        // when -  action or the behaviour that we are going test
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(expected));

        User actual = userService.findUserByUsername(username);

        // then - verify the output
        assertEquals(expected, actual);
        verify(userRepository, times(1)).findByUsername(username);
    }
    @Test
    @DisplayName("Should throw GeneralException(\"User not found\") when given username does not exist")
    void ShouldThrowGeneralException_whenGivenUsernameDoesNotExist() {
        // given - precondition or setup
        String username = "username";

        GeneralException expectedError = new GeneralException("User not found", HttpStatus.NOT_FOUND);

        // when -  action or the behaviour that we are going test
        when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.empty());

        GeneralException actual = assertThrows(GeneralException.class, () -> userService.findUserByUsername(username));
        // then - verify the output
        verify(userRepository).findByUsername(Mockito.anyString());
        assertEquals(expectedError.getMessage(), actual.getMessage());
    }

    @Test
    void ShouldReturnTru_whenGivenUsernameExist() {
        // given - precondition or setup
        String username = "username";

        // when -  action or the behaviour that we are going test
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(User.builder().build()));

        Boolean actual = userService.existsByUsername(username);

        // then - verify the output
        assertTrue(actual);
        verify(userRepository, times(1)).findByUsername(username);
    }
    @Test
    void ShouldReturnFalse_whenGivenUsernameDoesNotExist() {

        // given - precondition or setup
        String username = "username";

        // when -  action or the behaviour that we are going test
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        Boolean actual = userService.existsByUsername(username);

        // then - verify the output
        assertFalse(actual);
        verify(userRepository, times(1)).findByUsername(username);
    }


}