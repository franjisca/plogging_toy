package com.myproject.plogging.repository.user;

import com.myproject.plogging.domain.User;
import com.myproject.plogging.dto.user.LoginDto;
import com.myproject.plogging.dto.user.UserIdDto;

public interface UserCustomRepository {

    User findByUserIdAndPwd(LoginDto loginDto);

    User findByUserStrId(String userId);

    UserIdDto findByUserNo(Long userNo);

}
