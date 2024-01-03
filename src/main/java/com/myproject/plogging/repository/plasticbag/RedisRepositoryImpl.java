package com.myproject.plogging.repository.plasticbag;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisRepositoryImpl {

    private final RedisTemplate<String, String> redisTemplate;


    public Long decrease() {
        return redisTemplate.opsForValue().decrement("plastic_bag");

    }
}
