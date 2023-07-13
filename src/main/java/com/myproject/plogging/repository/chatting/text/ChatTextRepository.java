package com.myproject.plogging.repository.chatting.text;

import com.myproject.plogging.domain.ChatText;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatTextRepository extends JpaRepository<ChatText, Long>, ChatTextCustomRepository{

}
