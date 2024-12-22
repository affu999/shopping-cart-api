package com.techfierce.dreamshop.dto;

public class ImageDto {
    private String imageType;
    private String imageName;
    private String downloadUrl;

    public ImageDto(String imageType, String imageName, String downloadUrl) {
        this.imageType = imageType;
        this.imageName = imageName;
        this.downloadUrl = downloadUrl;
    }
}
