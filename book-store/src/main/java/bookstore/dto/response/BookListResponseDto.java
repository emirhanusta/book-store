package bookstore.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class BookListResponseDto {

        private Long id;
        private String title;
        private Long categoryId;
        private Long imageId;
}