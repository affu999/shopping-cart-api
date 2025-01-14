package com.techfierce.dreamshop.dto;

public class ImageDto {
    public  Long id;
    public  String fileName;
    public  String downloadUrl;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public ImageDto() {
    }

    public ImageDto(Long id, String imageName, String downloadUrl) {
        this.id = id;
        this.fileName = imageName;
        this.downloadUrl = downloadUrl;
    }
}
