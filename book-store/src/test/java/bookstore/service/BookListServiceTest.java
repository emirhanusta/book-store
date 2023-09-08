package bookstore.service;

import bookstore.dto.request.SaveBookRequest;
import bookstore.dto.response.BookListResponseDto;
import bookstore.model.Book;
import bookstore.model.Category;
import bookstore.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.when;

class BookListServiceTest {

    private BookListService bookListService;
    private BookRepository bookRepository;
    private CategoryService categoryService;


    @BeforeEach
    void setUp() {
        bookRepository = Mockito.mock(BookRepository.class);
        categoryService = Mockito.mock(CategoryService.class);
        bookListService = new BookListService(bookRepository, categoryService);
    }

    @Test
    void shouldReturnBookListResponseDtoList() {
        //given

        Category category = Category.builder()
                .name("name")
                .build();

        SaveBookRequest saveBookRequest = new SaveBookRequest(
                "title1",
                "author",
                718,
                "description",
                "publisher",
                1L);

        SaveBookRequest saveBookRequest2 = new SaveBookRequest(
                "title2",
                "author2",
                718,
                "description2",
                "publisher2",
                1L);

        Book book1 = Book.builder()
                .category(category)
                .pages(saveBookRequest.pages())
                .author(saveBookRequest.author())
                .title(saveBookRequest.title())
                .category(category)
                .publisher(saveBookRequest.publisher())
                .description(saveBookRequest.description())
                .build();
        book1.setId(1L);

        Book book2 = Book.builder()
                .category(category)
                .pages(saveBookRequest2.pages())
                .author(saveBookRequest2.author())
                .title(saveBookRequest2.title())
                .category(category)
                .publisher(saveBookRequest2.publisher())
                .description(saveBookRequest2.description())
                .build();
        book2.setId(2L);

        BookListResponseDto bookListResponseDto = new BookListResponseDto(
                1L,
                "title1",
                1L,
                null
        );
        BookListResponseDto bookListResponseDto2 = new BookListResponseDto(
                1L,
                "title2",
                1L,
                null
        );

        List<BookListResponseDto> bookListResponseDtoList = List.of(bookListResponseDto, bookListResponseDto2);

        //when
        when(bookRepository.findAll( any(Pageable.class))).thenReturn(new PageImpl<>(Arrays.asList(book1, book2)));

        //then
        List<BookListResponseDto> bookListResponseResult = bookListService.getAllBooks(2,1);
        assertEquals(bookListResponseDtoList.get(0).title(), bookListResponseResult.get(0).title());
        assertEquals(bookListResponseDtoList.get(1).title(), bookListResponseResult.get(1).title());
    }

    @Test
    void shouldReturnBookListResponseDtoList_whenCategoryNameExist() {
        //given
        String categoryName = "categoryName";

        Category category = Category.builder()
                .name(categoryName)
                .build();
        category.setId(1L);

        Book book1 = Book.builder()
                .title("title1")
                .category(category)
                .build();

        book1.setId(1L);

        Book book2 = Book.builder()
                .title("title2")
                .category(category)
                .build();

        book2.setId(2L);

        BookListResponseDto bookListResponseDto = new BookListResponseDto(
                1L,
                "title1",
                1L,
                null
        );
        BookListResponseDto bookListResponseDto2 = new BookListResponseDto(
                1L,
                "title2",
                1L,
                null
        );

        List<BookListResponseDto> bookListResponseDtoList = List.of(bookListResponseDto, bookListResponseDto2);

        //when
        when(categoryService.findByName(categoryName)).thenReturn(category);
        when(bookRepository.findAllByCategory(category)).thenReturn(Arrays.asList(book1, book2));
        //then
        List<BookListResponseDto> bookListResponseResult = bookListService.getBooksByCategoryName(categoryName);
        assertEquals(bookListResponseDtoList.get(0).categoryId(), bookListResponseResult.get(0).categoryId());
        assertEquals(bookListResponseDtoList.get(1).title(), bookListResponseResult.get(1).title());


    }

    @Test
    void shouldReturnBookResponseDtoList_whenGivenTitleExist() {
        //given
        String title = "title";

        Category category = Category.builder()
                .name("name")
                .build();

        Book book1 = Book.builder()
                .title(title)
                .category(category)
                .build();
        book1.setId(1L);

        Book book2 = Book.builder()
                .title(title)
                .category(category)
                .build();
        book1.setId(2L);

        BookListResponseDto bookListResponseDto1 = new BookListResponseDto(
                1L,
                "title",
                1L,
                null
        );
        BookListResponseDto bookListResponseDto2 = new BookListResponseDto(
                2L,
                "title",
                1L,
                null
        );
        List<BookListResponseDto> bookListResponseDtoList = Arrays.asList(bookListResponseDto1, bookListResponseDto2);
        //when
        when(bookRepository.findByTitle(title)).thenReturn(Arrays.asList(book1, book2));
        //then
        List<BookListResponseDto> bookListResponseResult = bookListService.getAllBooksByTitle(title);

        assertEquals(bookListResponseDtoList.get(0).title(), bookListResponseResult.get(0).title());
        assertEquals(bookListResponseDtoList.get(1).title(), bookListResponseResult.get(1).title());
    }

    @AfterEach
    void tearDown() {
    }

}