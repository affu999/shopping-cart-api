package com.techfierce.dreamshop.dto;

import org.springframework.stereotype.Component;

public class ImageDto {
    private Long id;
    private String imageName;
    private String downloadUrl;

    public void setId(Long id) {
        this.id = id;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Long getId() {
        return id;
    }

    public String getImageName() {
        return imageName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public ImageDto(Long id, String imageName, String downloadUrl) {
        this.id = id;
        this.imageName = imageName;
        this.downloadUrl = downloadUrl;
    }
}
