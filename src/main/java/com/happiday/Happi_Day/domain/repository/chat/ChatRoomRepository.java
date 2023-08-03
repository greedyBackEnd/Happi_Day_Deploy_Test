package com.happiday.Happi_Day.domain.repository.chat;

import com.happiday.Happi_Day.domain.entity.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
