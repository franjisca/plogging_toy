package com.myproject.plogging.controller;


import com.myproject.plogging.domain.PhotoList;
import com.myproject.plogging.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/photo")
@Slf4j
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    private final String fileDir = "upload-storage";

    @PostMapping("/upload/{userId}")
    public PhotoList uploadFile(@PathVariable("userId") String userId,
                                @RequestBody MultipartFile file) throws IOException {
        log.info("multipartFile={}", file);

        if(file.isEmpty()) throw new IllegalArgumentException();
            String fullPath = fileDir +file.getOriginalFilename();
            log.info("파일 저장 fullPath ={}", fullPath);
            file.transferTo(new File(fullPath));

        return photoService.save(userId, fullPath);
    }

    @GetMapping("/list")
    public List<PhotoList> photoList(){
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
