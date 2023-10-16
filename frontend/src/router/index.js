import Login from "@/components/Login.vue";
import MyChatRoom from "@/components/MyChatRoom.vue";
import { createRouter, createWebHistory } from 'vue-router';
import {getToken} from "@/authentication/token";
import CreateChatRoom from "@/components/CreateChatRoom.vue";
import ChatWithUser from "@/components/ChatWithUser.vue";
import SelectOption from "@/components/SelectOption.vue";

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/my-chat-room',
    name: 'MyChatRoom',
    component: MyChatRoom,
    meta: {requiresAuth : true}
  },
  {
    path: "/create-chat-room",
    name: "CreateChatRoom",
    component: CreateChatRoom,
  },
  {
    path: "/chat/:roomId",
    name: "ChatWithUser",
    component: ChatWithUser,
  },
  {
    path: "/choose-option",
    name: "Option",
    component: SelectOption,
  },
  {
    path: '/',
    redirect: '/login',
  },
];

const router = createRouter({
  history: createWebHistory(), // 브라우저 기반의 라우팅 설정
  routes,
});

router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth && !getToken()) {
    // 인증이 필요한 페이지에 접근하려고 할 때 토큰이 없으면 로그인 페이지로 리다이렉트
    next('/login');
  } else {
    next();
  }
});

export default router;