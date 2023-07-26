package bookstore.dto.response;

import bookstore.model.User;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        String role
) {
        public static UserResponseDto convertToUserResponseDto(User user) {
                return new UserResponseDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole().toString()
                );
        }
}
