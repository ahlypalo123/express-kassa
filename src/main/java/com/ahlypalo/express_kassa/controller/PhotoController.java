package com.ahlypalo.express_kassa.controller;

import com.ahlypalo.express_kassa.service.UploadPhotoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PhotoController {

    private final UploadPhotoService uploadPhotoService;

    public PhotoController(UploadPhotoService uploadPhotoService) {
        this.uploadPhotoService = uploadPhotoService;
    }

    @PostMapping("/photo")
    public String uploadPhoto(@RequestParam MultipartFile file) throws IOException {
        return uploadPhotoService.uploadPhoto(file);
    }

}
