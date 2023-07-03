package com.myproject.plogging.repository.user;

import com.myproject.plogging.domain.User;
import com.myproject.plogging.dto.user.LoginDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.myproject.plogging.domain.QUser.user;


@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public User findByUserIdAndPwd(LoginDto loginDto) {

        User loginUser = jpaQueryFactory.selectFrom(user)
                .where(
                        user.userId.eq(loginDto.getUserId())
                ).fetchOne();

        return loginUser;
    }

    @Override
    public User findByUserStrId(String userId) {
        return jpaQueryFactory
                .selectFrom(user)
                .where(user.userId.eq(userId)).fetchOne();
    }
}
