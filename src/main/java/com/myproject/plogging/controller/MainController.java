package com.myproject.plogging.controller;


import com.myproject.plogging.dto.TotalDto;
import com.myproject.plogging.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final PhotoService photoService;

    @GetMapping("/people-count")
    public TotalDto mainPage() {
        return new TotalDto(photoService.mainCount());
    }


}
