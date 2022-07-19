package com.taviak.expresskassa.controller;

import com.taviak.expresskassa.config.SwaggerConfig;
import com.taviak.expresskassa.service.UploadPhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Api(tags = SwaggerConfig.TAG_PHOTO)
public class PhotoController {

    private final UploadPhotoService uploadPhotoService;

    public PhotoController(UploadPhotoService uploadPhotoService) {
        this.uploadPhotoService = uploadPhotoService;
    }

    @PostMapping("/photo")
    @ApiOperation("Загрузка фото в систему")
    public String uploadPhoto(@RequestParam MultipartFile file) throws IOException {
        return uploadPhotoService.uploadPhoto(file);
    }

}
