package bookstore.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class UserResponseDto {

        private Long id;
        private String username;
        private String email;
        private String role;
}
