package bookstore.service;

import bookstore.dto.request.LoginRequest;
import bookstore.dto.request.SignUpRequest;
import bookstore.dto.response.TokenResponseDto;
import bookstore.dto.response.UserResponseDto;
import bookstore.exception.GeneralException;
import bookstore.model.Role;
import bookstore.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final UserService userService;
    private final TokenService tokenService;

    public TokenResponseDto login(LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.username(),
                            loginRequest.password()
                    )
            );
            return  new TokenResponseDto(
                    tokenService.generateToken(authentication),
                    UserResponseDto.convertToUserResponseDto(userService.findUserByUsername(loginRequest.username()))
            );
        }catch (final BadCredentialsException e) {
            throw new GeneralException("Incorrect username or password", HttpStatus.UNAUTHORIZED);
        }
    }

    public UserResponseDto signup(SignUpRequest signUpRequest) {

        var isAllReadyRegistired= userService.existsByUsername(signUpRequest.username());

        if(isAllReadyRegistired){
            throw new GeneralException("Username is already used",HttpStatus.FOUND);
        }

        var user = User.builder()
                .username(signUpRequest.username())
                .password(encoder.encode(signUpRequest.password()))
                .role(Role.USER)
                .email(signUpRequest.email())
                .build();

        return UserResponseDto.convertToUserResponseDto(userService.saveUser(user));
    }

    public UserResponseDto getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return UserResponseDto.convertToUserResponseDto(userService.findUserByUsername(username));
    }
}
