package bookstore.bookstore.dto.request;

import lombok.Data;

@Data
public class UpdateBookRequest {

    private Long id;
    private String title;
    private String author;
    private Integer pages;
    private String description;
    private Long categoryId;
}
