package com.myproject.plogging.service;


import com.myproject.plogging.domain.PhotoList;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.dto.photo.PhotoDto;
import com.myproject.plogging.exception.UserNotFoundException;
import com.myproject.plogging.repository.photolist.PhotoListRepository;
import com.myproject.plogging.repository.user.UserRepository;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PhotoService {

    private final PhotoListRepository photoListRepository;

    private final UserRepository userRepository;

    private final String fileDir = "/Users/hanjiyeon/Desktop/문서폴더/project_front/toy/public/upload_image/";

    @Transactional
    public void uploadFile(String userId, MultipartFile file){

        User findUser = userRepository.findByUserStrId(userId);

        if(findUser == null ) throw new UserNotFoundException("유저 정보가 존재하지 않습니다");
        if(file.isEmpty()) throw new IllegalArgumentException("파일을 등록하지 않았습니다");


        String newFileName = createStoreFileName(file.getOriginalFilename());

        try{
            Files.write(Path.of(fileDir + newFileName), file.getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }

        PhotoList savePhoto = new PhotoList
                (findUser,
                 newFileName,
                        fileDir + file.getOriginalFilename());
        photoListRepository.save(savePhoto);

    }

    public List<PhotoDto> list() {
        return photoListRepository.photoList().stream().map(
                photo -> new PhotoDto(
                        photo.getId(), photo.getUser().getUserId(),
                        photo.getImage(),
                        photo.getStoredFilename(),
                        photo.getLikes(),
                        photo.getUploadDate()
                )
        ).collect(Collectors.toList());
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

        photoListRepository.findById(photoNo).orElseThrow(IllegalArgumentException :: new);

        photoListRepository.addCount(photoNo);

    }

    public Long mainCount() {
        return photoListRepository.mainCount();
    }


    public List<PhotoDto> userPhotoList(String userId) {

        User findUser = userRepository.findByUserStrId(userId);

        if(findUser == null) {
            throw  new UserNotFoundException("유저 정보를 찾을 수 없습니다.");
        }

        return photoListRepository.userPhotoList(findUser.getId());
    }

    private String createStoreFileName(String originalFileName) {
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originalFileName);
        return uuid + "." + ext;
    }

    private String extractExt(String fileName) {
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index +1);
    }

    public Long myPloggingCount(String userId) {

        User findUser = userRepository.findByUserStrId(userId);
        return photoListRepository.myPloggingCount(findUser.getId());
    }
}
