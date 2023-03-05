package bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDto {

    private Long id;
    private String title;
    private String author;
    private Integer pages;
    private String description;
    private Long categoryId;
    private Long imageId;

}
