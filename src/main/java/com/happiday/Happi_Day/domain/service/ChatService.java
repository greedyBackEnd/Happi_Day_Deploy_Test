//package com.happiday.Happi_Day.domain.service;
//
//import com.happiday.Happi_Day.domain.entity.chat.dto.ChatMessageDto;
//import com.happiday.Happi_Day.domain.entity.chat.ChatRoom;
//import com.happiday.Happi_Day.domain.entity.chat.ChatRoomResponse;
//import com.happiday.Happi_Day.domain.entity.user.User;
//import com.happiday.Happi_Day.domain.repository.UserRepository;
//import com.happiday.Happi_Day.domain.repository.chat.ChatMessageRepository;
//import com.happiday.Happi_Day.domain.repository.chat.ChatRoomRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class ChatService {
//
//    private final UserRepository userRepository;
//    private final ChatRoomRepository chatRoomRepository;
//    private final ChatMessageRepository chatMessageRepository;
//    private final RedisTemplate redisTemplate;
//    private final RedisMessageListenerContainer redisMessageListener;
//    private final RedisSubscriber redisSubscriber;
//
//    public void sendMessage(ChatMessageDto chatMessageDto) {
//        redisTemplate.convertAndSend(chatMessageDto.getRoomId(), chatMessageDto);
//    }
//
//    public Long createChatRoom(User receiver, String username) {
//        User sender = userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        ChatRoom chatRoom = ChatRoom.builder()
//                            .sender(sender)
//                            .receiver(receiver)
//                            .build();
//        chatRoomRepository.save( chatRoom);
//        ChannelTopic topic = new ChannelTopic(Long.toString(chatRoom.getId()));
//        redisMessageListener.addMessageListener(redisSubscriber, topic);
//        return chatRoom.getId();
//    }
//
//}
