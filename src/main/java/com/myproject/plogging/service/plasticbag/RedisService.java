package com.myproject.plogging.service.plasticbag;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    private String key = "plastic_bag";
    private String value = "100";

    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
    public void setPlasticBagCount() {
        log.info("- - - plastic bag count reset - - -, {}", LocalDateTime.now());
        redisTemplate.opsForValue().set(key, value);
    }

    public Long decrease() {
        return redisTemplate.opsForValue().decrement(key);
    }

    public Integer getCount() {
        return Integer.parseInt(redisTemplate.opsForValue().get(key));
    }
}
