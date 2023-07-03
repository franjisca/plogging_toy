package com.myproject.plogging.service;


import com.myproject.plogging.domain.PhotoList;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.repository.photolist.PhotoListRepository;
import com.myproject.plogging.repository.user.UserRepository;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PhotoService {

    private final PhotoListRepository photoListRepository;

    private final UserRepository userRepository;


    @Transactional
    public PhotoList save(String userId,String filePath) {

        User user = userRepository.findByUserStrId(userId);


        return photoListRepository.save(new PhotoList(user, filePath));
    }

    public List<PhotoList> list() {
        return photoListRepository.findAll();
    }

    @Transactional
    public void delete(Long photoNo, String userId) {

        PhotoList photo = photoListRepository.findById(photoNo).orElseThrow(IllegalArgumentException::new);

        User user = userRepository.findByUserStrId(userId);

        System.out.println("user.getId(): " + user.getId());
        System.out.println("photo.getUser().getId(): " + photo.getUser().getId());

        if(user.getId() != photo.getUser().getId()) throw new IllegalArgumentException();

        photoListRepository.delete(photo);

    }

    @Transactional
    public void like(Long photoNo) {

        photoListRepository.addCount(photoNo);

    }

    public Long mainCount() {

        return photoListRepository.mainCount();
    }
}
