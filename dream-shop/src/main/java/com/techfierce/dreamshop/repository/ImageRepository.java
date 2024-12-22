package com.techfierce.dreamshop.repository;

import com.techfierce.dreamshop.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
