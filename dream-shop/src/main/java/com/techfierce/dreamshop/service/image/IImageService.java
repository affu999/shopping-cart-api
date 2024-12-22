package com.techfierce.dreamshop.service.image;

import com.techfierce.dreamshop.dto.ImageDto;
import com.techfierce.dreamshop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImage(List<MultipartFile> file, Long productId);
    Image updateImage(MultipartFile file, Long imageId);
}
