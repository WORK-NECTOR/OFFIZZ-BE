package com.worknector.offizz.global.s3.application.usecase;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.worknector.offizz.global.s3.exception.EmptyExtensionException;
import com.worknector.offizz.global.s3.exception.EmptyMultipartFileException;
import com.worknector.offizz.global.s3.exception.InvalidExtensionException;
import com.worknector.offizz.global.s3.exception.S3UploadFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3UploaderUseCase {

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucket;

    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "gif");
    private static final String dirName = "offizz-record";

    public String uploadMultipartFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty() || Objects.isNull(multipartFile.getOriginalFilename())) {
            throw new EmptyMultipartFileException();
        }

        validateExtension(multipartFile.getOriginalFilename());

        return uploadMultipartFileToS3(multipartFile);
    }

    private void validateExtension(String fileName) {
        int fileExtensionIndex = fileName.lastIndexOf(".");
        if (fileExtensionIndex == -1) {
            throw new EmptyExtensionException();
        }

        String extension = fileName.substring(fileExtensionIndex + 1).toLowerCase();
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new InvalidExtensionException();
        }
    }

    private String uploadMultipartFileToS3(MultipartFile multipartFile) throws IOException {
        String fileName = dirName + "/" +
                multipartFile.getOriginalFilename() + "_" +
                UUID.randomUUID().toString().substring(0, 10);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());
        metadata.setContentLength(multipartFile.getBytes().length);

        try {
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucket, fileName, multipartFile.getInputStream(), metadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new S3UploadFailedException();
        }

        return amazonS3.getUrl(bucket, fileName).toString();
    }
}
