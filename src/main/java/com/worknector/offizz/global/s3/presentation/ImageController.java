package com.worknector.offizz.global.s3.presentation;

import com.worknector.offizz.global.s3.application.usecase.S3UploaderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/image")
public class ImageController {
    private final S3UploaderUseCase s3UploaderUseCase;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestPart MultipartFile multipartFile) throws IOException {
        String url = s3UploaderUseCase.uploadMultipartFile(multipartFile);
        return ResponseEntity.ok(url);
    }
}
