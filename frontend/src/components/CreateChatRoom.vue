<template>
    <div>
        <h1>사용자 검색</h1>
        <!-- 사용자 닉네임 입력 폼 -->
        <input v-model="userNickname" placeholder="사용자 닉네임" @input="searchUsers">

        <!-- 닉네임 리스트 -->
        <ul>
            <li v-for="user in filteredUsers" :key="user.id">
                <button @click="startChat(user)">{{ user.name }}</button>
                <button v-if="user === selectedUser" @click="openChat">채팅하기</button>
            </li>
        </ul>

        <!-- 채팅 화면 또는 컴포넌트 -->
        <div v-if="selectedUser">
            <ChatWithUser :user="selectedUser"/>
        </div>
    </div>
</template>

<script>
import ChatWithUser from "@/components/ChatWithUser.vue";
import axios from "axios";

export default {
    name: "CreateChatRoom",
    data() {
        return {
            userNickname: '',
            users: [],
            filteredUsers: [],
            selectedUser: null,
        };
    },
    methods: {
        async searchUsers() {
            const response = await axios.get("/api/v1/chat/find");
            this.users = response.data;
            this.filteredUsers = this.users.filter(user => user.name.includes(this.userNickname));


        },
        startChat(user) {
            // 사용자 선택 시 선택한 사용자를 selectedUser에 할당
            this.selectedUser = user;
        },
        openChat() {
            // 채팅하기 버튼을 눌렀을 때 채팅창 열기 또는 다른 채팅 화면으로 이동하는 로직을 구현
        },
    },
    components: {
        ChatWithUser,
    },
};
</script>