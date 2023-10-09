package com.happiday.Happi_Day.domain.service;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRedisService {
    private static final String ENTER_INFO = "ENTER_INFO";
    private static final String USER_INFO = "USER_INFO";

    /**
     * "ENTER_INFO", roomId, userId (유저가 입장한 채팅방 정보)
     */
    @Resource(name = "redisTemplate")
    private HashOperations<String, Long, Long> chatRoomInfo;

    /**
     * 상대 정보는 receiverId 로 저장, 나의 정보는 senderId에 저장
     * "USER_INFO", receiverId, senderId
     */
    @Resource(name = "redisTemplate")
    private HashOperations<String, Long, Long> userInfo;

    // 유저가 입장한 채팅방ID와 유저 세션ID 맵핑 정보 저장
    public void userEnterRoomInfo(Long senderId, Long chatRoomId) {
        chatRoomInfo.put(ENTER_INFO, senderId, chatRoomId);
    }

    // 사용자가 채팅방에 입장해 있는지 확인
    public boolean existChatRoomUserInfo(Long senderId) {
        return chatRoomInfo.hasKey(ENTER_INFO, senderId);
    }

    // 사용자가 특정 채팅방에 입장해 있는지 확인
    public boolean existUserRoomInfo(Long chatRoomId, Long senderId) {
        return getUserEnterRoomId(senderId).equals(chatRoomId);
    }

    // 사용자가 입장해 있는 채팅방 ID 조회
    public Long getUserEnterRoomId(Long senderId) {
        return chatRoomInfo.get(ENTER_INFO, senderId);
    }

    // 사용자가 입장해 있는 채팅방 ID 조회
    public void exitUserEnterRoomId(Long senderId) {
        chatRoomInfo.delete(ENTER_INFO, senderId);
    }

    // 나의 대화상대 정보 저장
    public void saveMyInfo(Long receiverId, Long senderId) {
        userInfo.put(USER_INFO, receiverId, senderId);
    }

    public boolean existMyInfo(Long receiverId) {
        return userInfo.hasKey(USER_INFO, receiverId);
    }

    public Long getMyInfo(Long receiverId) {
        return userInfo.get(USER_INFO, receiverId);
    }

    // 나의 대화상대 정보 삭제
    public void deleteMyInfo(Long receiverId) {
        userInfo.delete(USER_INFO, receiverId);
    }
}
