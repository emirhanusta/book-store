package bookstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
public class Image extends BaseEntity{

        private String name;

        private String type;

        @Lob
        @Column(length = 1000)
        private byte[] imageData;

}
