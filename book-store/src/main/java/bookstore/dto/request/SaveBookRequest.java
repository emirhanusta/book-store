package bookstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveBookRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotNull
    private Integer pages;
    @NotNull
    private String description;
    @NotNull
    private Long categoryId;

}
