<template>
    <div>
        <h1>사용자 검색</h1>
        <!-- 사용자 닉네임 입력 폼 -->
        <input type="text" id="nickname" v-model="nickname" @keyup.enter="searchNicknames"/>
        <button @click="searchNicknames">검색</button>

        <!-- 닉네임 리스트 -->
        <ul>
            <li v-for="nick in nicknameList" :key="nick.id">
                {{ nick.nickname }}
                <button @click="startChat(nick.nickname)">채팅하기</button>
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
            nickname: '',
            users: [],
            nicknameList: [],
            isSearching: false,
        };
    },
    created() {
        // 라우터에서 전달받은 토큰 추출
        const token = this.$route.query.token;
        // 토큰을 사용하여 API 요청 보내기
        axios.get("/api/v1/chat/findAllUser", {
            headers: {
                Authorization: `Bearer ${token}`, // 토큰을 Authorization 헤더에 추가
            },
        })
            .then((response) => {
                // API 응답 처리
                this.users = response.data;
                console.log('지금 있는 모든 유저 목록:', response.data);
            })
            .catch((error) => {
                console.error('API 요청 실패', error);
            });
    },
    methods: {
        searchNicknames() {
            if (this.nickname) {
                // 사용자 목록에서 nickname을 포함하는 닉네임 검색
                this.nicknameList = this.users.filter(user => user.nickname.includes(this.nickname));
            }
        },
        startChat(selected) {
            const token = this.$route.query.token;

            axios.post(`/api/v1/chat`, {},{
                headers: {
                    Authorization: `Bearer ${token}`
                },
                params: {
                    nickname: selected
                }
            })
                .then((response) => {
                    // API 응답이 올바르게 받아졌는지 확인합니다.
                    if (response.status === 201) {
                        // API 응답 처리
                        const roomId = response.data;

                        // roomId와 nickname을 로컬 스토리지에 저장합니다.
                        localStorage.setItem('roomId', roomId);
                        localStorage.setItem('receiver', selected);

                        // ChatWithUser 페이지로 이동합니다.
                        this.$router.push({ name: 'ChatWithUser', params: { roomId: roomId }, query: { token } });
                    } else {
                        // API 응답이 올바르지 않은 경우 에러 처리
                        console.error('API 응답이 올바르지 않습니다.');
                    }
                })
                .catch((error) => {
                    console.error('API 요청 실패', error);
                });
        },
    },
    components: {
        ChatWithUser,
    },
};
</script>