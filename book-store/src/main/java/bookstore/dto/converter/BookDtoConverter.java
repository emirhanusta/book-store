package bookstore.dto.converter;

import bookstore.dto.response.BookListResponseDto;
import bookstore.dto.response.BookResponseDto;
import bookstore.model.Book;

public class BookDtoConverter {

    public static BookResponseDto convertToBookResponse(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .pages(book.getPages())
                .description(book.getDescription())
                .categoryId(book.getCategory().getId())
                .build();
    }

    public static BookListResponseDto convertToBookListResponse(Book book) {
        return BookListResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .categoryId(book.getCategory().getId())
                .build();
    }
}
