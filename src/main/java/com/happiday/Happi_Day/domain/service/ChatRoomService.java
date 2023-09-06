package com.happiday.Happi_Day.domain.service;


import com.happiday.Happi_Day.domain.entity.chat.ChatRoom;
import com.happiday.Happi_Day.domain.entity.chat.ChatRoomResponse;
import com.happiday.Happi_Day.domain.entity.user.User;
import com.happiday.Happi_Day.domain.repository.UserRepository;
import com.happiday.Happi_Day.domain.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    public Long createChatRoom(User receiver, String username) {
        User sender = userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ChatRoom chatRoom1 = chatRoomRepository.findBySenderOrReceiver(sender, receiver);
        ChatRoom chatRoom2 = chatRoomRepository.findBySenderOrReceiver(receiver, sender);

        ChatRoom chatRoom = null;

        if (chatRoom1 != null) {
            return chatRoom1.getId();
        } else if (chatRoom2 != null) {
            return chatRoom2.getId();
        } else {
            chatRoom = ChatRoom.builder()
                    .sender(sender)
                    .receiver(receiver)
                    .build();
        }

        ChatRoom saveChatRoom = chatRoomRepository.save(chatRoom);
        return saveChatRoom.getId();
    }

    public List<ChatRoomResponse> findChatRooms(String username) {
        User sender = userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<ChatRoom> chatRooms = chatRoomRepository.findAllBySenderOrReceiver(sender, sender);
        return chatRooms.stream().map(ChatRoomResponse::fromEntity).collect(Collectors.toList());
    }

    public ChatRoomResponse findChatRoom(String username, Long roomId) {
        User sender = userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ChatRoomResponse.fromEntity(chatRoom);
    }

}
