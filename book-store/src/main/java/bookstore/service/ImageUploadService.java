package bookstore.service;

import bookstore.dto.response.ImageUploadResponse;
import bookstore.exception.GeneralException;
import bookstore.model.Image;
import bookstore.repository.ImageRepository;
import bookstore.utils.ImageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageUploadService {

        private final ImageRepository imageRepository;
        private final BookService bookService;

        public ImageUploadResponse uploadImage(MultipartFile file, Long bookId) throws IOException {

            Image image = Image.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes()))
                    .build();

            imageRepository.save(image);
            bookService.updateBookImage(bookId, image);

            return ImageUploadResponse.builder()
                    .id(image.getId())
                    .name(image.getName())
                    .bookId(bookId)
                    .build();

        }

        public byte[] getById(Long id) {
            Image dbImage = imageRepository.findById(id).orElseThrow(
                    () -> new GeneralException("Image not found", HttpStatus.NOT_FOUND));
            return ImageUtils.decompressImage(dbImage.getImageData());
        }

        @Transactional
        public byte[] getImage(String name) {
            Image dbImage = imageRepository.findByName(name).orElseThrow(
                () -> new GeneralException("Image not found", HttpStatus.NOT_FOUND));
            return ImageUtils.decompressImage(dbImage.getImageData());
        }

        public void deleteById(Long id) {
            Image book = imageRepository.findById(id)
                    .orElseThrow(() -> new GeneralException("Book not found", HttpStatus.NOT_FOUND));

            imageRepository.delete(book);
        }
}