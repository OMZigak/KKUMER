package org.kkumulkkum.server.external;

import org.kkumulkkum.server.config.AwsConfig;
import org.kkumulkkum.server.exception.AwsException;
import org.kkumulkkum.server.exception.code.AwsErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class S3Service {

    private final String bucketName;
    private final AwsConfig awsConfig;
    private static final List<String> IMAGE_EXTENSIONS = Arrays.asList("image/jpeg", "image/png", "image/jpg", "image/webp");

    public S3Service(@Value("${aws-property.s3-bucket-name}") final String bucketName, AwsConfig awsConfig) {
        this.bucketName = bucketName;
        this.awsConfig = awsConfig;
    }

    public String uploadImage(String directoryPath, MultipartFile image) throws IOException {
        final String extension = getFileExtension(image.getOriginalFilename());
        final String key = directoryPath + generateImageFileName(extension);
        final S3Client s3Client = awsConfig.getS3Client();

        validateExtension(image);
        validateFileSize(image);

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(image.getContentType())
                .contentDisposition("inline")
                .build();

        RequestBody requestBody = RequestBody.fromBytes(image.getBytes());
        s3Client.putObject(request, requestBody);
        return s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(key)).toExternalForm();
    }

    public void deleteImage(String imageUrl) throws IOException {
        String key = extractKeyFromUrl(imageUrl); // URL에서 키 추출
        final S3Client s3Client = awsConfig.getS3Client();
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.deleteObject(deleteRequest);
    }

    public String extractKeyFromUrl(String url) throws IOException {
        URL s3Url = new URL(url);
        String path = s3Url.getPath();
        return path.substring(1);
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private String generateImageFileName(String extension) {
        return UUID.randomUUID() + "." + extension;
    }

    private void validateExtension(MultipartFile image) {
        String contentType = image.getContentType();
        if (!IMAGE_EXTENSIONS.contains(contentType)) {
            throw new AwsException(AwsErrorCode.INVALID_IMAGE_EXTENSION);
        }
    }

    private static final Long MAX_FILE_SIZE = 5 * 1024 * 1024L;

    private void validateFileSize(MultipartFile image) {
        if (image.getSize() > MAX_FILE_SIZE) {
            throw new AwsException(AwsErrorCode.IMAGE_SIZE_EXCEEDED);
        }
    }
}