package bookstore.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageUploadResponse {

    private Long id;
    private Long bookId;
    private String name;
}
