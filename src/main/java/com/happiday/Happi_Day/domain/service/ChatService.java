package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.entity.chat.ChatRoom;
import com.happiday.Happi_Day.domain.repository.UserRepository;
import com.happiday.Happi_Day.domain.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final RedisTemplate redisTemplate;
    private final ChannelTopic channelTopic;
    private final ChatRedisService chatRedisService;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    // 채팅방 입장
    public void enter(Long userId, Long roomId) {

        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        chatRedisService.userEnterRoomInfo(userId, roomId);
    }

    //채팅
//    @Transactional
//    public void sendMessage(ChatMessageDto dto, User user) {
//        ChatRoom chatRoom = chatRoomRepository.findById(dto.getRoomId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        //채팅 생성 및 저장
//        ChatMessage chatMessage = ChatMessage.builder()
//                .chatRoom(chatRoom)
//                .sender(user)
//                .content(dto.getMessage())
//                .build();
//
//        chatMessageRepository.save(chatMessage);
//        String topic = channelTopic.getTopic();
//
//        // ChatMessageRequest에 유저정보, 현재시간 저장
//        chatMessageRequest.setNickName(user.getNickname());
//        chatMessageRequest.setUserId(user.getId());
//
//        if (chatMessageRequest.getType() == ChatMessageRequest.MessageType.GROUP_TALK) {
//            // 그륩 채팅일 경우
//            redisTemplate.convertAndSend(topic, chatMessageRequest);
//            redisTemplate.opsForHash();
//        } else {
//            // 일대일 채팅 이면서 안읽은 메세지 업데이트
//            redisTemplate.convertAndSend(topic, chatMessageRequest);
//            updateUnReadMessageCount(chatMessageRequest);
//        }
//    }


}
