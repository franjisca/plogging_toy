package com.myproject.plogging.service;


import com.myproject.plogging.repository.marker.MarkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarkerService {

    private final MarkerRepository markerRepository;

}
