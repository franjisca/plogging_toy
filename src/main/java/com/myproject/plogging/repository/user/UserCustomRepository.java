package com.myproject.plogging.repository.user;

import com.myproject.plogging.domain.User;
import com.myproject.plogging.dto.user.LoginDto;
import com.myproject.plogging.dto.user.UserIdDto;

public interface UserCustomRepository {

    public User findByUserIdAndPwd(LoginDto loginDto);

    public User findByUserStrId(String userId);

    public UserIdDto findByUserNo(Long userNo);

}
