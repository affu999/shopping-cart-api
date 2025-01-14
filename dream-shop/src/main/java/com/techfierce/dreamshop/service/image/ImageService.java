package com.techfierce.dreamshop.service.image;

import com.techfierce.dreamshop.dto.ImageDto;
import com.techfierce.dreamshop.exceptions.ResourceNotFound;
import com.techfierce.dreamshop.model.Image;
import com.techfierce.dreamshop.model.Product;
import com.techfierce.dreamshop.repository.ImageRepository;
import com.techfierce.dreamshop.service.product.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements IImageService{
    private final ImageRepository imageRepository;
    private final ProductService productService;
    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Image Not Found!")
        );
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(
                imageRepository::delete,
                () -> {throw new ResourceNotFound("Image Not Found!");}
        );
    }

    @Override
    public List<ImageDto> saveImage(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDto> savedImages = new ArrayList<>();
        for(MultipartFile file: files){
            try{
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setProduct(product);
                image.setImage(new SerialBlob(file.getBytes()));
                image.setDownloadUrl("/api/v1/images/image/download/");
                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadUrl(savedImage.getDownloadUrl() + savedImage.getId());
                savedImages.add(new ImageDto(
                        savedImage.getId(),
                        savedImage.getFileName(),
                        savedImage.getDownloadUrl()
                ));
            }catch(IOException | SQLException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImages;
    }

    @Override
    public Image updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    public ImageService(ImageRepository imageRepository, ProductService productService) {
        this.imageRepository = imageRepository;
        this.productService = productService;
    }
}
