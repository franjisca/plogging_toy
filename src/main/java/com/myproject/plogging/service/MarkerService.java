package com.myproject.plogging.service;


import com.myproject.plogging.domain.User;
import com.myproject.plogging.dto.marker.MarkerListDto;
import com.myproject.plogging.exception.UserNotFoundException;
import com.myproject.plogging.repository.marker.MarkerRepository;
import com.myproject.plogging.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MarkerService {

    private final MarkerRepository markerRepository;

    private final UserRepository userRepository;


    public List<MarkerListDto> getMyMarkerList(String userId) {
        User user = userRepository.findByUserStrId(userId);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", userId));
        }

        return markerRepository.userMarkerList(user.getId());
    }
}
