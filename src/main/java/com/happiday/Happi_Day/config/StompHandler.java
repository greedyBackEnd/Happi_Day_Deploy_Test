package com.happiday.Happi_Day.config;

import com.happiday.Happi_Day.domain.entity.user.User;
import com.happiday.Happi_Day.domain.repository.UserRepository;
import com.happiday.Happi_Day.domain.service.ChatRedisService;
import com.happiday.Happi_Day.jwt.JwtTokenUtils;
import com.happiday.Happi_Day.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Component
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {

    private final UserRepository userRepository;
    private final ChatRedisService chatRedisService;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        // websocket 연결시 헤더의 jwt token 검증
        if (StompCommand.CONNECT == accessor.getCommand()) {
            String jwt = accessor.getFirstNativeHeader("Authorization");
            if (StringUtils.hasText(jwt) && jwt.startsWith("Bearer")) {
                String username = SecurityUtils.getCurrentUsername();
                User sender = userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                Long receiverId = (Long) message.getHeaders().get("receiverId");
                chatRedisService.saveMyInfo(receiverId, sender.getId());
            } else if (StompCommand.DISCONNECT == accessor.getCommand()) {
                Long receiverId = (Long) message.getHeaders().get("receiverId");

                if (chatRedisService.existMyInfo(receiverId)) {
                    Long receiver = chatRedisService.getMyInfo(receiverId);
                    if (chatRedisService.existChatRoomUserInfo(receiver))
                        chatRedisService.exitUserEnterRoomId(receiver);

                    chatRedisService.deleteMyInfo(receiverId);
                }
            }
        }
        return message;
    }
}
