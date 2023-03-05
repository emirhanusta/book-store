package bookstore.dto.converter;

import bookstore.dto.response.UserResponseDto;
import bookstore.model.User;

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
