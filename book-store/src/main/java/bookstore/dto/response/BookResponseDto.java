package bookstore.dto.response;

import bookstore.model.Book;

public record BookResponseDto (
        Long id,
        String title,
        String author,
        Integer pages,
        String description,
        Long categoryId,
        Long imageId
){
    public static BookResponseDto convertToBookResponse(Book book) {

        if (book.getImage() == null)
            return new BookResponseDto(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPages(),
                    book.getDescription(),
                    book.getCategory().getId(),
                    null);

            return new BookResponseDto(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPages(),
                    book.getDescription(),
                    book.getCategory().getId(),
                    book.getImage().getId());

    }


}
