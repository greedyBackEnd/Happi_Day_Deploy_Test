package com.happiday.Happi_Day.domain.controller;

import com.happiday.Happi_Day.domain.entity.chat.dto.ChatMessageDto;
import com.happiday.Happi_Day.domain.entity.user.User;
import com.happiday.Happi_Day.domain.repository.UserRepository;
import com.happiday.Happi_Day.domain.repository.chat.ChatRoomRepository;
import com.happiday.Happi_Day.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final RedisTemplate redisTemplate;
    private final ChannelTopic channelTopic;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message, @Header("token") String token) {
        String username = SecurityUtils.getCurrentUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // 로그인 회원 정보로 대화명 설정
        message.setSender(user.getNickname());

        // 채팅방 입장시에는 대화명과 메시지를 자동으로 세팅한다.
        if (ChatMessageDto.MessageType.ENTER.equals(message.getType())) {
            message.setSender("[알림]");
            message.setMessage(user.getNickname() + "님이 입장하셨습니다.");
        }
        // Websocket에 발행된 메시지를 redis로 발행(publish)
        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }
}
