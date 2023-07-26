package bookstore.dto.response;

public record ImageUploadResponse(
        Long id,
        Long bookId,
        String name
) {

}
