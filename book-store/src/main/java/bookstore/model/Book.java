package bookstore.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
public class Book extends BaseEntity{

    private String title;
    private String author;
    private Integer pages;
    private String description;
    private String publisher;

    @OneToOne
    @JoinTable(name = "book_image",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Image image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
