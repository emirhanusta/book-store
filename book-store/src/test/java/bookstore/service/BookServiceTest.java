package bookstore.service;

import bookstore.dto.request.SaveBookRequest;
import bookstore.dto.request.UpdateBookRequest;
import bookstore.dto.response.BookResponseDto;
import bookstore.exception.GeneralException;
import bookstore.model.Book;
import bookstore.model.Category;
import bookstore.repository.BookRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {


    private BookService bookService;

    private BookRepository bookRepository;
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        bookRepository = Mockito.mock(BookRepository.class);
        categoryService = Mockito.mock(CategoryService.class);
        bookService = new BookService(bookRepository, categoryService);
    }

    @Test
    @DisplayName("Should return BookResponseDto when given save book request")
    void shouldReturnBookResponseDto_WhenGivenSaveBookRequest() {

        //given
        Category category = Category.builder()
                .name("name")
                .build();

        SaveBookRequest saveBookRequest = new SaveBookRequest(
                "title",
                "author",
                718,
                "description",
                1L);

        Book book = Book.builder()
                .title(saveBookRequest.title())
                .category(category)
                .build();

        BookResponseDto bookResponseDto = new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.getDescription(),
                book.getCategory().getId(),
                null);

        //when
        when(categoryService.findById(anyLong())).thenReturn(category);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        //then
        BookResponseDto result = bookService.saveBook(saveBookRequest);
        assertEquals(bookResponseDto, result);
        assertEquals(bookResponseDto.title(), result.title());
        assertEquals(bookResponseDto.categoryId(), result.categoryId());

        verify(categoryService).findById(anyLong());
        verify(bookRepository).save(any(Book.class));
        verify(bookRepository, times(1)).save(any(Book.class));
        verify(categoryService, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Should return BookResponseDto when given update book request and book exists")
    void shouldReturnBookResponseDto_WhenGivenUpdateBookDtoAndBookExists() {

        //given
        Category category = Category.builder()
                .name("name")
                .build();

        UpdateBookRequest updateBookRequest = new UpdateBookRequest(
                1L,
                "updated title",
                "updated author",
                1234,
                "updated description",
                1L
        );

        Book book = Book.builder()
                .title(updateBookRequest.title())
                .category(category)
                .build();

        BookResponseDto bookResponseDto = new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.getDescription(),
                book.getCategory().getId(),
                null);

        //when
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(categoryService.findById(anyLong())).thenReturn(category);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        //then
        BookResponseDto result = bookService.updateBook(updateBookRequest);
        assertEquals(bookResponseDto, result);
        assertEquals(bookResponseDto.title(), result.title());
        assertEquals(bookResponseDto.categoryId(), result.categoryId());

        verify(categoryService).findById(anyLong());
        verify(bookRepository).save(any(Book.class));
        verify(bookRepository).findById(anyLong());
        verify(bookRepository, times(1)).save(any(Book.class));
        verify(categoryService, times(1)).findById(anyLong());
    }


    @Test
    @DisplayName("Should throw GeneralException(\"Book not found\") when given update book request and book does not exist")
    void shouldThrowGeneralException_WhenGivenUpdateBookDtoAndBookDoesNotExist() {

        //given
        UpdateBookRequest updateBookRequest = new UpdateBookRequest(
                1L,
                "updated title",
                "updated author",
                1234,
                "updated description",
                1L
        );


        //when
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> bookService.updateBook(updateBookRequest))
                .isInstanceOf(GeneralException.class)
                .hasMessageContaining("Book not found");

        verify(bookRepository).findById(anyLong());
        verify(bookRepository, times(1)).findById(anyLong());
        verifyNoInteractions(categoryService);
    }

    @Test
    @DisplayName("Should return BookResponseDto when given id and book exists")
    void shouldReturnBookResponseDto_WhenGivenIdAndBookExists() {

        //given
        Category category = Category.builder()
                .name("name")
                .build();

        Book book = Book.builder()
                .title("title")
                .category(category)
                .build();

        BookResponseDto bookResponseDto = new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.getDescription(),
                book.getCategory().getId(),
                null);
        //when
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        //then
        BookResponseDto result = bookService.getBookById(1L);
        assertEquals(bookResponseDto, result);
        assertEquals(bookResponseDto.title(), result.title());

        verify(bookRepository).findById(anyLong());
        verify(bookRepository, times(1)).findById(anyLong());
    }
    @Test
    @DisplayName("Should throw GeneralException(\"Book not found\") when given id and book does not exist")
    void shouldThrowGeneralException_WhenGivenIdAndBookDoesNotExists() {

        //given

        Long bookId = 1L;

        //when
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        //then
         assertThatThrownBy(() -> bookService.getBookById(bookId))
                .isInstanceOf(GeneralException.class)
                .hasMessageContaining("Book not found");

        verify(bookRepository).findById(anyLong());
        verify(bookRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Should delete book when given id and book exists")
    void shouldDeleteBook_WhenGivenIdAndBookExists() {

        //given

        Book book = Book.builder()
                .title("title")
                //.category(category)
                .build();

        //when
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        bookService.deleteBookById(1L);

        //then
        verify(bookRepository).findById(anyLong());
        verify(bookRepository).delete(any(Book.class));
        verify(bookRepository, times(1)).findById(anyLong());
        verify(bookRepository, times(1)).delete(any(Book.class));

    }
    @AfterEach
    void tearDown() {
    }
}