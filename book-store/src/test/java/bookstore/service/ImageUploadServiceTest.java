package bookstore.service;

import bookstore.dto.response.ImageUploadResponse;
import bookstore.exception.GeneralException;
import bookstore.model.Image;
import bookstore.repository.ImageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ImageUploadServiceTest {

    private ImageRepository imageRepository;
    private BookService bookService;
    private ImageUploadService imageUploadService;
    @BeforeEach
    void setUp() {
        imageRepository = mock(ImageRepository.class);
        bookService = mock(BookService.class);
        imageUploadService = new ImageUploadService(imageRepository, bookService);
    }


    @Test
    void shouldReturnImageUploadResponse_whenGivenFileAndBookId() throws IOException {

        MultipartFile mockFile = mock(MultipartFile.class);

        when(mockFile.isEmpty()).thenReturn(false);
        when(mockFile.getContentType()).thenReturn("image/png");
        when(mockFile.getOriginalFilename()).thenReturn("test.png");
        when(mockFile.getBytes()).thenReturn(new byte[0]);

        Image savedImage = Image.builder()
                .name("test.png")
                .type("image/png")
                .imageData(new byte[0])
                .build();
        when(imageRepository.save(any(Image.class))).thenReturn(savedImage);

        ImageUploadResponse response = imageUploadService.uploadImage(mockFile, 1L);
        assertEquals("test.png", response.name());

        verify(imageRepository, times(1)).save(any(Image.class));
        verify(bookService, times(1)).updateBookImage(eq(1L), any(Image.class));
    }

    @Test
    void shouldThrowGeneralException_whenGivenFileIsEmpty() {
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.isEmpty()).thenReturn(true);

        assertThatThrownBy(() -> imageUploadService.uploadImage(mockFile, 1L))
                .isInstanceOf(GeneralException.class)
                .hasMessage("Image can not be empty");
    }

    @Test
    void shouldThrowGeneralException_whenGivenFileIsNotImage() {
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.isEmpty()).thenReturn(false);
        when(mockFile.getContentType()).thenReturn("text/plain");

        assertThatThrownBy(() -> imageUploadService.uploadImage(mockFile, 1L))
                .isInstanceOf(GeneralException.class)
                .hasMessage("Image must be png or jpeg");
    }

    @Test
    void shouldReturnByteArray_whenGivenIdExist() {
        Long id = 1L;
        Image image = Image.builder()
                .imageData(new byte[0])
                .build();
        image.setId(id);
        when(imageRepository.findById(anyLong())).thenReturn(Optional.of(image));

        var result = imageUploadService.getImageById(id);

        assertNotNull(result);
    }

    @Test
    void shouldThrowGeneralException_whenGivenIdDoesNotExist() {
        Long id = 1L;
        when(imageRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> imageUploadService.getImageById(id))
                .isInstanceOf(GeneralException.class)
                .hasMessage("Image not found");
    }

    @Test
    void shouldReturnByteArray_whenGivenNameExist() {
        String name = "test.png";
        Image image = Image.builder()
                .imageData(new byte[0])
                .build();
        image.setName(name);
        when(imageRepository.findByName(anyString())).thenReturn(Optional.of(image));

        var result = imageUploadService.getImageByName(name);

        assertNotNull(result);
    }
    @Test
    void shouldThrowGeneralException_whenGivenNameDoesNotExist() {
        String name = "test.png";
        when(imageRepository.findByName(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> imageUploadService.getImageByName(name))
                .isInstanceOf(GeneralException.class)
                .hasMessage("Image not found");
    }



    @Test
    void shouldDeleteImage_whenGivenIdExist() {
        Long id = 1L;
        Image image = Image.builder()
                .imageData(new byte[0])
                .build();
        image.setId(id);
        when(imageRepository.findById(anyLong())).thenReturn(Optional.of(image));

        imageUploadService.deleteById(id);

        verify(imageRepository, times(1)).delete(any(Image.class));

    }


    @AfterEach
    void tearDown() {
    }
}