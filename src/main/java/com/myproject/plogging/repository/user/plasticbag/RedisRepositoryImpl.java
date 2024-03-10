package com.myproject.plogging.repository.user.plasticbag;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;


@Repository
@RequiredArgsConstructor
@Slf4j
public class RedisRepositoryImpl {

    private final RedisTemplate<String, String> redisTemplate;

    // 여기에 기본적으로 하루에 plastic bag count 100으로 맞춰주는 코드 만들기

    private String key = "plastic_bag";
    private String value = "100";

    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
    public void setPlasticBagCountCount() {
        log.info("- - - plastic bag count reset - - -, {}", LocalDateTime.now());
        redisTemplate.opsForValue().set(key, value);
    }

    public Long decrease() {
        return redisTemplate.opsForValue().decrement("plastic_bag");
    }

    public Integer getCount() {
        return Integer.parseInt(redisTemplate.opsForValue().get("plastic_bag"));
    }
}
