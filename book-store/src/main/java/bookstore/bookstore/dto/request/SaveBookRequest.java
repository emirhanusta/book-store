package bookstore.bookstore.dto.request;

import bookstore.bookstore.model.BookStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveBookRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    private String isbn;
    @NotBlank
    private String publisher;
    @NotBlank
    private Integer pages;
    @NotBlank
    private Integer quantity;
    @NotBlank
    private Double price;
    @NotNull
    private String description;
    @NotNull
    private BookStatus bookStatus;
    @NotNull
    private Long categoryId;

}
