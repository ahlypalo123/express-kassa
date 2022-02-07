package com.ahlypalo.express_kassa.service;

import com.ahlypalo.express_kassa.constants.Central;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class UploadPhotoService {

    public String uploadPhoto(MultipartFile file) throws IOException {
        String extension = "";
        String fn = file.getOriginalFilename();
        int index = fn.indexOf(".");
        if (index != -1) {
            extension = fn.substring(fn.indexOf("."));
        }
        String name = UUID.randomUUID() + extension;
        Path path = saveFile(Central.DIR_PATRON_PHOTOS, name, file);
        return path.toString();
    }

    private Path saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath;
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

}
