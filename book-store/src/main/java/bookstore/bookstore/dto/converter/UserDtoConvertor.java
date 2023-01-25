package bookstore.bookstore.dto.converter;

import bookstore.bookstore.dto.response.UserResponseDto;
import bookstore.bookstore.model.User;

public class UserDtoConvertor {

    public static UserResponseDto convertToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();
    }
}
