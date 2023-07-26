package bookstore.dto.response;

public record TokenResponseDto(
        String token,
        UserResponseDto user
) {
}
