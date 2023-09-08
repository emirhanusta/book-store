package bookstore.dto.request;

public record UpdateBookRequest (
        Long id,
        String title,
        String author,
        Integer pages,
        String description,
        String publisher,
        Long categoryId
){
}
