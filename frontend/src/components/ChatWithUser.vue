<template>
    <div>
        <h1>채팅</h1>
        <div class="chat-box">
            <!-- 채팅 메시지 표시 -->
            <div v-for="message in messages" :key="message.id" class="message">
                <span v-if="message.isSentByMe">나: </span>
                <span v-else>{{ message.sender }}: </span>
                {{ message.text }}
            </div>
        </div>

        <!-- 메시지 입력 폼 -->
        <div class="input-box">
            <input v-model="newMessage" @keyup.enter="sendMessage" placeholder="메시지를 입력하세요" />
            <button @click="sendMessage">전송</button>
        </div>
    </div>
</template>
<script>
import axios from "axios";

export default {
    name: "ChatWithUser",
    data() {
        return {
            messages: [], // 채팅 메시지 목록
            newMessage: "", // 새로운 메시지 입력
        };
    },
    created() {
        // ChatWithUser 컴포넌트가 생성될 때 채팅 메시지를 가져오는 로직을 여기에 작성
        this.fetchChatMessages();
    },
    methods: {
        fetchChatMessages() {
            // API를 사용하여 채팅 메시지를 가져오는 로직을 작성
            // roomId를 사용하여 해당 방의 채팅 내용을 가져올 수 있어야 함
        },
        sendMessage() {
            // API를 사용하여 메시지를 보내는 로직을 작성
            // roomId와 newMessage 변수를 사용하여 메시지를 보내야 함
        },
    },
};
</script>

<style scoped>

</style>