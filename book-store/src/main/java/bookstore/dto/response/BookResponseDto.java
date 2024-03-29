package bookstore.dto.response;

import bookstore.model.Book;

public record BookResponseDto (
        Long id,
        String title,
        String author,
        Integer pages,
        String description,
        String publisher,
        Long categoryId,
        Long imageId
){
    public static BookResponseDto convertToBookResponse(Book book) {
        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.getDescription(),
                book.getPublisher(),
                book.getCategory().getId(),
                book.getImage() == null ? null : book.getImage().getId());
    }


}
