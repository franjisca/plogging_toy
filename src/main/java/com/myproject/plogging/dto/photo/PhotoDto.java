package com.myproject.plogging.dto.photo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PhotoDto {

    private Long id;

    private String userId;

    private String image;


    private String storedFileName;
    private Integer likes;

    private LocalDateTime uploadDate;

    public PhotoDto(Long id, String userId, String image, String storedFileName,Integer likes, LocalDateTime uploadDate) {
        this.id = id;
        this.userId = userId;
        this.image = image;
        this.storedFileName = storedFileName;
        this.likes = likes;
        this.uploadDate = uploadDate;
    }
}
