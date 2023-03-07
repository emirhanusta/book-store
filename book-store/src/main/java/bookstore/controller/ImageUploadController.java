package bookstore.controller;

import bookstore.dto.response.ImageUploadResponse;
import bookstore.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
public class ImageUploadController {

    private final ImageUploadService imageUploadService;

    @PostMapping
    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam("image") MultipartFile file, @RequestParam("bookId") Long bookId) throws IOException {
       ImageUploadResponse imageUploadResponse = imageUploadService.uploadImage(file, bookId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(imageUploadResponse);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?>  getImageByName(@PathVariable String name){
        byte[] image = imageUploadService.getImage(name);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
    @GetMapping("/getById/{name}")
    public ResponseEntity<?>  getImageByName(@PathVariable Long id){
        byte[] image = imageUploadService.getById(id);

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
