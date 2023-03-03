package bookstore.bookstore.service;

import bookstore.bookstore.dto.request.SaveBookRequest;
import bookstore.bookstore.dto.request.UpdateBookRequest;
import bookstore.bookstore.dto.response.BookResponseDto;
import bookstore.bookstore.exception.GeneralException;
import bookstore.bookstore.model.Book;
import bookstore.bookstore.model.Category;
import bookstore.bookstore.repository.BookRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

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

        SaveBookRequest saveBookRequest = SaveBookRequest.builder()
                .title("title")
                .categoryId(1L)
                .build();

        Book book = Book.builder()
                .title(saveBookRequest.getTitle())
                .category(category)
                .build();

        BookResponseDto bookResponseDto = BookResponseDto.builder()
                .title(saveBookRequest.getTitle())
                .categoryId(category.getId())
                .build();

        //when
        when(categoryService.findById(anyLong())).thenReturn(category);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        //then
        BookResponseDto result = bookService.saveBook(saveBookRequest);
        assertEquals(bookResponseDto, result);
        assertEquals(bookResponseDto.getTitle(), result.getTitle());
        assertEquals(bookResponseDto.getCategoryId(), result.getCategoryId());

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

        UpdateBookRequest updateBookRequest = UpdateBookRequest.builder()
                .id(1L)
                .title("updated title")
                .categoryId(1L)
                .build();

        Book book = Book.builder()
                .title(updateBookRequest.getTitle())
                .category(category)
                .build();

        BookResponseDto bookResponseDto = BookResponseDto.builder()
                .title(updateBookRequest.getTitle())
                .categoryId(category.getId())
                .build();

        //when
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(categoryService.findById(anyLong())).thenReturn(category);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        //then
        BookResponseDto result = bookService.updateBook(updateBookRequest);
        assertEquals(bookResponseDto, result);
        assertEquals(bookResponseDto.getTitle(), result.getTitle());
        assertEquals(bookResponseDto.getCategoryId(), result.getCategoryId());

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
        UpdateBookRequest updateBookRequest = UpdateBookRequest.builder()
                .id(1L)
                .title("updated title")
                .categoryId(1L)
                .build();


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

    @Disabled
    @Test
    @DisplayName("Should throw GeneralException(\"Category not found\") when given update book request and category does not exist")
    void shouldThrowGeneralException_WhenGivenUpdateBookDto_AndCategoryDoesNotExist() {
        //yanlış calışıyor
        //given

        Long categoryId = 1L;
        GeneralException generalException = new GeneralException("Category not found", HttpStatus.NOT_FOUND);

        //when
        when(categoryService.findById(categoryId)).thenThrow(generalException);

        //then
        assertThatThrownBy(() -> categoryService.findById(categoryId))
                .isInstanceOf(GeneralException.class)
                .hasMessageContaining("Category not found");

        verify(categoryService).findById(anyLong());
        verify(categoryService, times(1)).findById(anyLong());
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

        BookResponseDto bookResponseDto = BookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .build();
        //when
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        //then
        BookResponseDto result = bookService.getBookById(1L);
        assertEquals(bookResponseDto, result);
        assertEquals(bookResponseDto.getTitle(), result.getTitle());

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

    @AfterEach
    void tearDown() {
    }
}