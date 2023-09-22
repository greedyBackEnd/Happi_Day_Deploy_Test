<template>
    <div>
        <h1>사용자 검색</h1>
        <!-- 사용자 닉네임 입력 폼 -->
        <input type="text" id="nickname" v-model="searchQuery" @keyup.enter="searchNicknames"/>
        <button @click="searchNicknames">검색</button>

        <!-- 닉네임 리스트 -->
        <ul>
            <li v-for="nick in users" :key="nick.id">
                {{ nick.id }}
                <button @click="startChat(nick.nickname)">Chat</button>
            </li>
        </ul>
    </div>
</template>

<script>
import ChatWithUser from "@/components/ChatWithUser.vue";
import axios from "axios";

export default {
    name: "CreateChatRoom",
    data() {
        return {
            searchQuery: '',
            users: [],
            nicknameList: [],
            isSearching: false,
        };
    },
    created() {
        // 라우터에서 전달받은 토큰 추출
        const token = this.$route.query.token;

        // 토큰을 사용하여 API 요청 보내기
        axios.get("/api/v1/chat/findAllUser")
            .then((response) => {
                // API 응답 처리
                this.roomList = response.data;
                console.log('채팅방 목록:', response.data);
            })
            .catch((error) => {
                console.error('API 요청 실패', error);
            });
    },
    methods: {
        searchNicknames() {

        },
        startChat(nickname) {
            // 채팅 시작 로직을 구현하세요.
        },
    },
    components: {
        ChatWithUser,
    },
};
</script>