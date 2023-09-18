<template>
    <div>
        <h1>사용자 검색</h1>
        <input type="text" v-model="searchedNickname" @input="searchUsers" placeholder="닉네임을 입력하세요" />

        <!-- 검색 결과 표시 -->
        <div v-if="filteredUsers.length > 0">
            <h2>검색 결과:</h2>
            <ul>
                <li v-for="user in filteredUsers" :key="user.id">
                    <span @click="startChat(user)">{{ user.name }}</span>
                    <button v-if="user.showChatButton" @click="openChat(user)">채팅하기</button>
                </li>
            </ul>
        </div>

        <!-- 검색 결과가 없을 때 메시지 표시 -->
        <div v-else>
            <p>해당 닉네임을 찾을 수 없습니다.</p>
        </div>
    </div>
</template>

<script>
export default {
    name: "ChatRoom",
    data() {
        return {
            searchedNickname: '',
            users: [],
        };
    },
    computed: {
        filteredUsers() {
            const searchTerm = this.searchedNickname.toLowerCase();
            return this.users.filter(user => user.name.toLowerCase().includes(searchTerm));
        },
    },
    methods: {
        async searchUsers() {
            // 사용자 검색 로직을 수행합니다.
            // 이 부분에서 백엔드 API를 호출하여 검색하거나,
            // 서버에서 데이터를 가져와야 합니다.

            // 여기에서는 간단한 예시로 사용자 목록을 로컬 데이터로 사용합니다.
            // 실제로는 백엔드 API를 호출하여 검색해야 합니다.
        },
        startChat(user) {
            // 해당 사용자와의 채팅을 시작하는 로직을 수행합니다.
            // 여기에서는 사용자를 클릭하면 채팅 버튼이 나타나도록 설정합니다.
            user.showChatButton = true;
        },
        openChat(user) {
            // 채팅을 열기 위한 로직을 수행합니다.
            // 여기에서는 사용자를 클릭하면 채팅을 열 수 있도록 설정합니다.
            console.log(`채팅을 시작합니다: ${user.name}`);
        },
    },
};
</script>