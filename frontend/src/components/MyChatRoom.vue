<template>
    <div>
        <h1>내 채팅방 목록</h1>

        <!-- 채팅방 목록 표시 -->
        <ul>
            <li v-for="room in chatRooms" :key="room.id">
                <router-link :to="{ name: 'ChatRoom', params: { roomId: room.id } }">
                    {{ room.name }}
                </router-link>
            </li>
        </ul>

        <!-- 채팅방 만들기 버튼 -->
        <button @click="goToCreateRoom">채팅방 만들기</button>
    </div>
</template>

<script>
import axios from "axios";

export default {
    name: "MyChatRoom",
    created() {
        // 라우터에서 전달받은 토큰 추출
        const token = this.$route.query.token;

        // 토큰을 사용하여 API 요청 보내기
        axios.get('/api/v1/chat/rooms', {
            headers: {
                Authorization: `Bearer ${token}`, // 토큰을 Authorization 헤더에 추가
            },
        })
            .then((response) => {
                // API 응답 처리
                console.log('채팅방 목록:', response.data);
            })
            .catch((error) => {
                console.error('API 요청 실패', error);
            });
    },
    methods: {
        goToCreateRoom() {
            const token = this.$route.query.token;
            // ChatRoom 컴포넌트로 이동
            this.$router.push({ name: 'ChatRoom', query: {token}});
        },
    },
};
</script>