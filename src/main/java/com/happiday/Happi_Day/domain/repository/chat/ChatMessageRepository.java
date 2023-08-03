package com.happiday.Happi_Day.domain.repository.chat;

import com.happiday.Happi_Day.domain.entity.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
