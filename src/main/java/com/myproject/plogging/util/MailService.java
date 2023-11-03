package com.myproject.plogging.util;


import com.myproject.plogging.domain.Meeting;
import com.myproject.plogging.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    public void whenEnjoyMeeting(User user, Meeting meeting){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(user.getUsername()+"님 플로깅 모임에 참여하셨습니다!");
        message.setTo(user.getEmail());
        message.setText(user.getUsername()+ "님 " +meeting.getTitle()+" 모임에 참여하셨습니다. " +
                "즐거운 모임과 더 나은 지구를 위한 실천을 응원합니다!");

        javaMailSender.send(message);

    }

}
