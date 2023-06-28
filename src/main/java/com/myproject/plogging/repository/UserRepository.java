package com.myproject.plogging.repository;

import com.myproject.plogging.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
    User findByUsername(String username);

}
