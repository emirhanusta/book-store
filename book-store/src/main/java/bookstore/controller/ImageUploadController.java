package bookstore.controller;

import bookstore.dto.response.ImageUploadResponse;
import bookstore.service.ImageUploadService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageUploadController {

    private final ImageUploadService imageUploadService;

    @PostMapping
    public ResponseEntity<ImageUploadResponse> uploadImage( @RequestParam("image") @NotBlank MultipartFile file,
                                                            @RequestParam("bookId") Long bookId) {
       ImageUploadResponse imageUploadResponse = imageUploadService.uploadImage(file, bookId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(imageUploadResponse);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?>  getImageByName(@PathVariable String name){
        byte[] image = imageUploadService.getImageByName(name);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<?>  getImageByName(@PathVariable Long id){
        byte[] image = imageUploadService.getImageById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteImage(@PathVariable Long id){
        imageUploadService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }
}
