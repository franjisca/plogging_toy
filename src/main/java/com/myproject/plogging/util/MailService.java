package com.myproject.plogging.util;


import com.myproject.plogging.domain.Meeting;
import com.myproject.plogging.domain.User;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

@RequiredArgsConstructor
@Slf4j
@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    private final SpringTemplateEngine springTemplateEngine;

    @Async
    public void whenEnjoyMeeting(User user, Meeting meeting, boolean flag){

        // 메시지 객체 생성
        SimpleMailMessage message = new SimpleMailMessage();

        // 이메일 받을 대상
        message.setTo(user.getEmail());

        if(flag) {
                // 이메일 제목
                message.setSubject(user.getUsername()+"님 플로깅 모임이 생성 되었습니다!");
                message.setText(user.getUsername()+"님 플로깅 모임을 생성해주셔서 감사합니다. 즐거운 모임과 더 나은 지구를 위한 실천을 응원합니다!");
        }

        // flag = false
        message.setSubject(user.getUsername()+"님 플로깅 모임에 참여하셨습니다!");
        message.setText(user.getUsername()+ "님 " +meeting.getTitle()+" 모임에 참여하셨습니다. " +
                "즐거운 모임과 더 나은 지구를 위한 실천을 응원합니다!");

        javaMailSender.send(message);

    }



    // send
    @Async
    public void whenLeaveMeeting(Meeting meeting, User user){


    }

}
