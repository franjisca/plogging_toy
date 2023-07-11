package com.myproject.plogging.service;

import com.myproject.plogging.domain.Chatting;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.dto.chatting.ChattingInfoDto;
import com.myproject.plogging.exception.UserNotFoundException;
import com.myproject.plogging.repository.chatting.ChattingRepository;
import com.myproject.plogging.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChattingService {


    private final ChattingRepository chattingRepository;

    private final UserRepository userRepository;

    public List<ChattingInfoDto> myChattingList(@PathVariable("userId") String userId){

        User findUser = userRepository.findByUserStrId(userId);

        if(findUser == null) throw new UserNotFoundException("유저 정보를 찾을 수 없습니다.");


        return chattingRepository.myMeetingList(findUser.getId());
    }


    public void isValid(Long meetingId) {

         chattingRepository.findById(meetingId).orElseThrow(IllegalArgumentException :: new);
    }
}
