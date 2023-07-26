package bookstore.dto.response;

import bookstore.model.Book;

public record BookListResponseDto (
        Long id,
        String title,
        Long categoryId,
        Long imageId
){
        public static BookListResponseDto convertToBookListResponse(Book book) {
                if (book.getImage() == null)
                        return new BookListResponseDto(
                                book.getId(),
                                book.getTitle(),
                                book.getCategory().getId(),
                                null);
                return new BookListResponseDto(
                        book.getId(),
                        book.getTitle(),
                        book.getCategory().getId(),
                        book.getImage().getId());
        }

}
