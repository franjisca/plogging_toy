package com.myproject.plogging.repository.chatting.text;


import com.myproject.plogging.dto.chatting.ChatTextDataDto;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.myproject.plogging.domain.QChatText.chatText1;
import static com.myproject.plogging.domain.QChatting.chatting;
import static com.myproject.plogging.domain.QUser.user;

@RequiredArgsConstructor
public class ChatTextCustomRepositoryImpl implements ChatTextCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<ChatTextDataDto> chattingData(Long chattingId) {

        List<Tuple> tupleData = jpaQueryFactory
                .select(
                        chatText1.chatting.id, chatText1.createDate,
                        chatText1.id, chatText1.chatText,
                        user.userId
                )
                .from(chatText1)
                .join(chatting).on(chatText1.chatting.id.eq(chatting.id))
                .join(user).on(chatText1.user.id.eq(user.id))
                .where(
                        chatText1.chatting.id.eq(chattingId)
                ).fetch();

        return tupleData.stream().map( tuple ->
                ChatTextDataDto.builder()
                        .chattingId(tuple.get(chatText1.chatting.id))
                        .createDate(tuple.get(chatText1.createDate))
                        .textId(tuple.get(chatText1.id))
                        .chattingText(tuple.get(chatText1.chatText))
                        .userId(tuple.get(user.userId))
                        .build()
                        ).toList();
    }
}
