package bookstore.dto.request;

import lombok.Data;

@Data
public class UpdateCategoryRequest {

    private Long id;

    private String name;
}

