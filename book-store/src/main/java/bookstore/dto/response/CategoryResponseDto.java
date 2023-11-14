package bookstore.dto.response;

import bookstore.model.Category;

import java.util.List;

public record CategoryResponseDto(
        Long id,
        String name,
        List<BookResponseDto> books

) {
    public static CategoryResponseDto convertToCategoryResponse(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getBooks() == null ? null : category.getBooks()
                        .stream()
                        .map(BookResponseDto::convertToBookResponse)
                        .toList());
    }
}
