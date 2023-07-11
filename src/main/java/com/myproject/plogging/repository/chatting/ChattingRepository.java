package com.myproject.plogging.repository.chatting;

import com.myproject.plogging.domain.Chatting;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChattingRepository extends JpaRepository<Chatting, Long>, ChattingCustomRepository {
}
