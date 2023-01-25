package bookstore.bookstore.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Book extends BaseEntity{

    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private Integer pages;
    private Integer quantity;
    private Double price;
    private String description;

    @Enumerated(EnumType.STRING)
    BookStatus bookStatus;

    @OneToOne
    private Image image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
