package com.happiday.Happi_Day.domain.controller;

import com.happiday.Happi_Day.domain.entity.chat.ChatRoomResponse;
import com.happiday.Happi_Day.domain.entity.user.User;
import com.happiday.Happi_Day.domain.repository.UserRepository;
import com.happiday.Happi_Day.domain.service.ChatRoomService;
import com.happiday.Happi_Day.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatRoomController {

    private final UserRepository userRepository;
    private final ChatRoomService chatRoomService;

    @GetMapping("/room")
    public String rooms(Model model) {
        return "/chat/room";
    }

    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }

    // 채팅방 만들기
    @PostMapping
    @ResponseBody
    public ResponseEntity<Long> createChatRoom(@RequestParam String nickname) {
        String username = SecurityUtils.getCurrentUsername();
        User receiver = userRepository.findByNickname(nickname).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Long roomId = chatRoomService.createChatRoom(receiver, username);
        return new ResponseEntity<>(roomId, HttpStatus.CREATED);
    }

    // 채팅방 리스트 찾기
    @GetMapping("/rooms")
    @ResponseBody
    public ResponseEntity<List<ChatRoomResponse>> getChatRooms() {
        String username = SecurityUtils.getCurrentUsername();
        List<ChatRoomResponse> chatRoomResponses = chatRoomService.findChatRooms(username);
        return new ResponseEntity<>(chatRoomResponses, HttpStatus.OK);
    }

    // 채팅방 하나 찾기 -> 내역 가져오기
    @GetMapping("/{roomId}")
    @ResponseBody
    public ResponseEntity<ChatRoomResponse> getChatRoom(@PathVariable Long roomId) {
        String username = SecurityUtils.getCurrentUsername();
        return new ResponseEntity<>(chatRoomService.findChatRoom(username, roomId), HttpStatus.OK);
    }

}
