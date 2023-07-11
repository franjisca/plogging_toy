package com.myproject.plogging.controller;


import com.myproject.plogging.dto.photo.PhotoDto;
import com.myproject.plogging.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/photo")
@Slf4j
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoService photoService;

    @PostMapping("/upload/{userId}")
    public void uploadFile(@PathVariable("userId") String userId,
                                @RequestPart MultipartFile file) throws IOException {
        log.info("multipartFile={}", file);

        photoService.uploadFile(userId, file);
    }

    @GetMapping("/list")
    public List<PhotoDto> photoList(){
        return photoService.list();
    }

    @DeleteMapping("/delete/{photoNo}")
    public void deletePhoto(@PathVariable("photoNo") Long photoNo,
                            @RequestParam("userId") String userId) {
        photoService.delete(photoNo, userId);
    }

    @GetMapping("/like/{photoNo}")
    public void likePhoto(@PathVariable("photoNo") Long photoNo){
        photoService.like(photoNo);
    }


}
