package com.myproject.plogging.repository.user.plasticbag;

import com.myproject.plogging.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedisRepository extends JpaRepository<User, Long> {
}
