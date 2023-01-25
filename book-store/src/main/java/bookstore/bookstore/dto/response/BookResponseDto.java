package bookstore.bookstore.dto.response;

import bookstore.bookstore.model.BookStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.File;

@Data
@NoArgsConstructor
@SuperBuilder
public class BookResponseDto {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private Integer pages;
    private Double price;
    private String description;
    private BookStatus bookStatus;
    private Long categoryId;
    private Long imageId;

}
