package bookstore.dto.response;

import bookstore.model.Book;

public record BookListResponseDto (
        Long id,
        String title,
        Long categoryId,
        Long imageId
){
        public static BookListResponseDto convertToBookListResponse(Book book) {
                return new BookListResponseDto(
                        book.getId(),
                        book.getTitle(),
                        book.getCategory().getId(),
                        book.getImage() == null ? null : book.getImage().getId());
        }

}
