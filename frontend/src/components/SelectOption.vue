<template>
    <h1>기능 선택하기</h1>
    <div>
        <button @click="goToChat">채팅으로 이동</button>
        <br>
        <br>
        <button @click="goToPosts">게시글 조회</button>
    </div>
</template>
<script>
export default {
    name: "SelectOption",
    methods: {
        goToChat() {
            console.log("채팅으로 이동합니다.");
            this.$router.push({ name: 'MyChatRoom'});
        },
        goToPosts() {
            console.log("게시글을 조회합니다.");

        }
    }
};
</script>

<style scoped>

</style>