package bookstore.dto.converter;

import bookstore.dto.response.BookListResponseDto;
import bookstore.dto.response.BookResponseDto;
import bookstore.model.Book;

public class BookDtoConverter {

    public static BookResponseDto convertToBookResponse(Book book) {

        if (book.getImage() == null)
            return BookResponseDto.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .pages(book.getPages())
                    .description(book.getDescription())
                    .categoryId(book.getCategory().getId())
                    .build();
        return BookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .pages(book.getPages())
                .description(book.getDescription())
                .categoryId(book.getCategory().getId())
                .imageId(book.getImage().getId())
                .build();
    }

    public static BookListResponseDto convertToBookListResponse(Book book) {
        if (book.getImage() == null)
            return BookListResponseDto.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .categoryId(book.getCategory().getId())
                    .build();
        return BookListResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .categoryId(book.getCategory().getId())
                .imageId(book.getImage().getId())
                .build();
    }
}

