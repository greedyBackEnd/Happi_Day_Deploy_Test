<template>
    <div>
        <h1>로그인</h1>
        <form @submit.prevent="login">
            <div>
                <label for="username">사용자 이메일:</label>
                <input type="username" id="username" v-model="username" required/>
            </div>
            <div>
                <label for="password">비밀번호:</label>
                <input type="password" id="password" v-model="password" required/>
            </div>
            <div>
                <button type="submit">로그인</button>
            </div>
        </form>
    </div>
</template>

<script>
import {saveToken} from "@/authentication/token";
import axios from "axios";

export default {
    data() {
        return {
            username: '',
            password: '',
        };
    },
    methods: {
        login() {
            axios.post('/api/v1/auth/login', {
                username: this.username,
                password: this.password,
            })
                .then((response) => {
                    // 로그인 성공 시 토큰을 받아옴
                    const token = response.data.token;

                    // 토큰을 저장
                    saveToken(token);

                    // 로그인 후 Option 이동
                    this.$router.push({ name: 'Option'});
                })
                .catch((error) => {
                    // 로그인 실패 처리
                    console.error('로그인 실패', error);
                });
        },
    },
};
</script>
