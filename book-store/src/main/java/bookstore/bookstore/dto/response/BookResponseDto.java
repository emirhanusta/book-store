package bookstore.bookstore.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class BookResponseDto {

    private Long id;
    private String title;
    private String author;
    private Integer pages;
    private String description;
    private Long categoryId;
    private Long imageId;

}
