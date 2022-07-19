package com.taviak.expresskassa.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class UploadPhotoService {

    private static final Logger logger = LoggerFactory.getLogger(UploadPhotoService.class);
    private static final String KEY_URL = "url";

    private final Cloudinary cloudinary;

    public UploadPhotoService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadPhoto(MultipartFile file) throws IOException {
        logger.info("received photo {}", file.getName());
        File temp = File.createTempFile(UUID.randomUUID().toString(), null, null);
        file.transferTo(temp);
        Map resp = cloudinary.uploader().upload(temp, ObjectUtils.emptyMap());
        return (String) resp.get(KEY_URL);
    }
}
