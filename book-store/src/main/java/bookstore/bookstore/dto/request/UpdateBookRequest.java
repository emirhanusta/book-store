package bookstore.bookstore.dto.request;

import bookstore.bookstore.model.BookStatus;
import lombok.Data;

@Data
public class UpdateBookRequest {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private Integer pages;
    private Integer quantity;
    private Double price;
    private String description;
    private BookStatus bookStatus;
    private Long categoryId;
}
