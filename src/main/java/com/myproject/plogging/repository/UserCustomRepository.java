package com.myproject.plogging.repository;

import com.myproject.plogging.domain.User;
import com.myproject.plogging.dto.user.LoginDto;

public interface UserCustomRepository {

    public User findByUserIdAndPwd(LoginDto loginDto);

    public User findByUserid(String userId);

}
