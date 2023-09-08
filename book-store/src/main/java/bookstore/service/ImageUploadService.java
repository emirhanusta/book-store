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
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageUploadService {

        private final ImageRepository imageRepository;
        private final BookService bookService;

        public ImageUploadResponse uploadImage(MultipartFile file, Long bookId)  {

            if (file.isEmpty()) {
                throw new GeneralException("Image can not be empty", HttpStatus.BAD_REQUEST);
            }
            if (!Objects.equals(file.getContentType(), "image/png") && !Objects.equals(file.getContentType(), "image/jpeg")) {
                throw new GeneralException("Image must be png or jpeg", HttpStatus.BAD_REQUEST);
            }
            Image image;
            try {
                image = Image.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .imageData(ImageUtils.compressImage(file.getBytes()))
                        .build();
            } catch (IOException e) {
                throw new GeneralException(e.getMessage(), HttpStatus.BAD_REQUEST);
            }

            imageRepository.save(image);
            bookService.updateBookImage(bookId, image);

            return new ImageUploadResponse(
                    image.getId(),
                    bookId,
                    image.getName());
        }

        public byte[] getImageById(Long id) {
            Image dbImage = findImageById(id);
            return ImageUtils.decompressImage(dbImage.getImageData());
        }

        @Transactional
        public byte[] getImageByName(String name) {
            Image dbImage = imageRepository.findByName(name).orElseThrow(
                () -> new GeneralException("Image not found", HttpStatus.NOT_FOUND));
            return ImageUtils.decompressImage(dbImage.getImageData());
        }

        public void deleteById(Long id) {
            imageRepository.delete(findImageById(id));
        }

        private Image findImageById(Long id) {
            return imageRepository.findById(id).orElseThrow(
                    () -> new GeneralException("Image not found", HttpStatus.NOT_FOUND));
        }
}
