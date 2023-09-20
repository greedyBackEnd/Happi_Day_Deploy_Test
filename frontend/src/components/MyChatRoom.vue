<template>
    <div>
        <h1>내 채팅방 목록</h1>
        <!-- 채팅방 만들기 버튼 -->
        <button @click="goToCreateRoom">채팅방 만들기</button>

        <!-- 채팅방 목록 표시 -->
        <ul>
            <li v-for="room in roomList" :key="room.id">
                {{ room.receiver }}
                <button @click="goToChatRoom(room.id)">채팅하기</button>
                <!-- 방 삭제하기 버튼 -->
                <button @click="deleteRoom(room.id)">방 삭제하기</button>
            </li>
        </ul>
    </div>
</template>

<script>
import axios from "axios";

export default {
    name: "MyChatRoom",
    data() {
        return {
            roomList : []
        }
    },
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
                this.roomList = response.data;
                console.log('채팅방 목록:', response.data);
            })
            .catch((error) => {
                console.error('API 요청 실패', error);
            });
    },
    methods: {
        goToCreateRoom() {
            const token = this.$route.query.token;
            this.$router.push({ name: 'CreateChatRoom', query: { token }});
        },
        goToChatRoom(roomId) {
            // 채팅방 컴포넌트로 이동하고 roomId 전달
            //this.$router.push({ name: 'ChatWithUser', params: { roomId } });
        },

        deleteRoom(roomId) {
            // 방 삭제 로직을 구현하고 API 호출 등을 수행
            // 삭제 후 목록에서도 제거해야 할 수 있습니다.
            // this.roomList에서 roomId에 해당하는 방을 삭제하는 로직 추가
        }
    },
};
</script>